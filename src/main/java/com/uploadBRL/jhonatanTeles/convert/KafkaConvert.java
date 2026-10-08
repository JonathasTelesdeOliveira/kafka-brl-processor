package com.uploadBRL.jhonatanTeles.convert;

import com.uploadBRL.jhonatanTeles.dto.out.TransacaoKafkaDTO;
import com.uploadBRL.jhonatanTeles.enums.Lado;
import com.uploadBRL.jhonatanTeles.exeptions.ConflictExeption;
import com.uploadBRL.jhonatanTeles.infraestructure.TransactionEntity;
import org.springframework.stereotype.Component;


import java.util.List;


@Component
public class KafkaConvert {

    private String parsetexto(String entrada){
        if(entrada == null || entrada.trim().isEmpty()) return "";
        return entrada.replaceAll("[^A-Z]", "");
    }

    public List<TransactionEntity> transacaoKafkaDTOList_P_TransactionEntityList(List<TransacaoKafkaDTO> dtoList){
        if (dtoList == null || dtoList.isEmpty()) {
            throw new ConflictExeption("Impossivel realizar conversão da lista: List<TransactionEntity> entityeslist");
        }
        return dtoList.stream()
                .map(this::transacaoKafkaDTO_P_transactionEntity)
                .toList();
    }

    public TransactionEntity transacaoKafkaDTO_P_transactionEntity(TransacaoKafkaDTO dto){
        return TransactionEntity.builder()
                .id(dto.getId())
                .tradeId(dto.getTradeId() != null ? dto.getTradeId() : null)
                .dataSped(dto.getDataSped())
                .symbol(dto.getSymbol())
                .price(dto.getPrice())
                .lado(dto.getLado())
                .quantycoinExe(dto.getQuantycoinExe())
                .quantityDol(dto.getQuantityDol())
                .symbolquantity(dto.getSymbol_quantity_Dol())
                .taxa(dto.getTaxa())
                .symboltaxa(dto.getSymbol_taxa())
                .valorBrl(dto.getValorBrl() != null ? dto.getValorBrl() : null)
                .dataSpedUSDTBRL(dto.getDataSpedUSDTBRL() != null ? dto.getDataSpedUSDTBRL() : null)
                .build();
    }


    public TransacaoKafkaDTO transactionEntityPara_transacaoKafkaDTO(TransactionEntity e){
        return TransacaoKafkaDTO.builder()
                .id(e.getId())
                .tradeId(e.getTradeId())
                .dataSped(e.getDataSped())
                .symbol(e.getSymbol())
                .price(e.getPrice())
                .lado(e.getLado())
                .quantycoinExe(e.getQuantycoinExe())
                .quantityDol(e.getQuantityDol())
                .symbol_quantity_Dol(e.getSymbolquantity())
                .taxa(e.getTaxa())
                .valorBrl(e.getValorBrl() != null ? e.getValorBrl() : null)
                .dataSpedUSDTBRL(e.getDataSpedUSDTBRL() != null ? e.getDataSpedUSDTBRL() : null)
                .build();
    }

    public List<TransacaoKafkaDTO> transactionEntityList_P_TransacaoKafkaDTOList(List<TransactionEntity> entityeslist){
        if (entityeslist == null || entityeslist.isEmpty()) {
            throw new ConflictExeption("Impossivel realizar conversão da lista: List<TransactionEntity> entityeslist");
        }
        return entityeslist.stream()
                .map(this::transactionEntityPara_transacaoKafkaDTO)
                .toList();
    }




}
