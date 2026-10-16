package com.uploadBRL.jhonatanTeles.busines;

import com.uploadBRL.jhonatanTeles.dto.out.TransacaoKafkaDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class DisparadortemplateAgrupamento {

    private final KafkaTemplate<String, List<TransacaoKafkaDTO>> kafkaTemplate;
    @Autowired
    public DisparadortemplateAgrupamento(KafkaTemplate<String, List<TransacaoKafkaDTO>> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }
    @Transactional
    public void updateTransaction(List<TransacaoKafkaDTO> tList) {

        log.info("========== ENVIANDO PARA KAFKA AGRUPAMENTO==========");
        log.info("Quantidade de transações no lote: {}", tList.size());

        kafkaTemplate.send("transacoes-processadas", tList);
    }

}

