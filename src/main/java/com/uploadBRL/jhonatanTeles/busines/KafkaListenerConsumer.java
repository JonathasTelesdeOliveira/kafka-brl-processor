package com.uploadBRL.jhonatanTeles.busines;


import com.uploadBRL.jhonatanTeles.convert.KafkaConvert;
import com.uploadBRL.jhonatanTeles.dto.out.TransacaoKafkaDTO;
import com.uploadBRL.jhonatanTeles.exeptions.ConflictExeption;
import com.uploadBRL.jhonatanTeles.dto.in.AggregateInDTO;
import com.uploadBRL.jhonatanTeles.dto.in.USDTBRL;
import com.uploadBRL.jhonatanTeles.infraestructure.TransactionEntity;
import com.uploadBRL.jhonatanTeles.repository.TransactionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

@Slf4j
@Component
public class KafkaListenerConsumer {
    private final RestClient restClient;
    private final TransactionRepository transRepository;
    private final KafkaConvert convert;
    private final DisparadortemplateAgrupamento template;

    public KafkaListenerConsumer(RestClient restClient, TransactionRepository transactionRepository,
                                 KafkaConvert convert, DisparadortemplateAgrupamento template) {
        this.restClient = restClient;
        this.transRepository = transactionRepository;
        this.convert = convert;
        this.template = template;
    }

    public String formatarParCoinBRL(String simboloMoeda){
        if(simboloMoeda == null || simboloMoeda.isBlank()){
            throw new IllegalArgumentException("Símbolo da moeda não pode ser nulo ou vazio.");
        }

        String moedaLimpa = simboloMoeda.trim().toUpperCase();

        if (moedaLimpa.endsWith("BRL")){
            return moedaLimpa;
        }
        return moedaLimpa+"BRL";
    }


    public List<AggregateInDTO> buscarUrl(String myCoin, LocalDateTime dataCompra){
        if(dataCompra == null){
            System.out.println(dataCompra);
            throw new ConflictExeption("Data da compra buscarUrl nulla"+ dataCompra);
        }

        long startTime = dataCompra
                .atZone(ZoneId.of("America/Sao_Paulo"))
                .toInstant()
                .toEpochMilli();

        long endTime = startTime + 180000;
        List<AggregateInDTO> list = restClient
                .get()
                .uri("api/v3/aggTrades?symbol="+myCoin+"&startTime="+startTime+"&endTime="+endTime)
                .retrieve()

                .body(new ParameterizedTypeReference<List<AggregateInDTO>>() {});
        System.out.println(myCoin+">>>>>>"+ startTime +">>>>>" + endTime);
        return list;
    }

    public USDTBRL buscarBRLmaisProximo(String myusdtbrl, LocalDateTime data){

        if(data == null){
            throw new IllegalArgumentException("Data do USDTBRL não informado. buscarBRLmaisProximo");
        }
        long dataLong = data
                .atZone(ZoneId.of("America/Sao_Paulo"))
                .toInstant()
                .toEpochMilli();
        System.out.println("Data do USDTBRL: "+dataLong);
        List<AggregateInDTO> listUDTBRL = buscarUrl(myusdtbrl, data);

        if(listUDTBRL.isEmpty()){
            throw new ConflictExeption("Data da compra nulla" + data);
        }

        AggregateInDTO dustMaisProximo = listUDTBRL.stream()
                .filter(l -> l.getTradeId() != null)
                .filter(l -> l.getDataSped() != null)
                .min(Comparator.comparingLong(li -> Math.abs(li.getDataSped() - dataLong))).orElseThrow(() ->
                        new ConflictExeption("Não foi encontrada cotação USDTBRL próxima ao horário da operação."));

        return USDTBRL.builder()
                .price(dustMaisProximo.getPrice())
                .dataSpedUSDTBRL(dustMaisProximo.getDataSped())
                .build();
    }



    @KafkaListener(topics = "transacoes-pendentes", groupId = "uploadBRL-t9")
    public void transactionPendetes(List<TransacaoKafkaDTO> transacaoKafkaDTOS) {
        log.info("Mensagem recebida do Kafka tamanho: {}", transacaoKafkaDTOS.size());
        System.out.println(">>> Recebido lote no Kafka com tamanho: " + transacaoKafkaDTOS.size());
        for (TransacaoKafkaDTO kafkaDTO : transacaoKafkaDTOS) {
            log.info("Mensagem recebida do Kafka: {}", kafkaDTO);
            String myusdtbrl = formatarParCoinBRL(kafkaDTO.getSymbol_quantity_Dol());
            // Buscar o Valor BRL na hora e Data da compra BRL na api da Binance
            USDTBRL usdtbrl = buscarBRLmaisProximo(myusdtbrl, kafkaDTO.getDataSped());

            kafkaDTO.setDataSpedUSDTBRL(new Timestamp(usdtbrl.getDataSpedUSDTBRL()).toLocalDateTime());
            kafkaDTO.setValorBrl(usdtbrl.getPrice());
        }
        List<TransactionEntity> entites = transRepository.saveAll(convert.transacaoKafkaDTOList_P_TransactionEntityList(transacaoKafkaDTOS));
        if (!entites.isEmpty()){
            enviarAgrupamento(convert.transactionEntityList_P_TransacaoKafkaDTOList(entites));
        }else{log.warn("Lista de entidades salvas está vazia!");}

    }

    public void enviarAgrupamento(List<TransacaoKafkaDTO> tList){
            template.updateTransaction(tList);
    }

}












