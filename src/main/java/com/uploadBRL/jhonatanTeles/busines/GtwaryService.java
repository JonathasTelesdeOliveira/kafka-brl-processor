package com.uploadBRL.jhonatanTeles.busines;

import com.uploadBRL.jhonatanTeles.convert.KafkaConvert;
import com.uploadBRL.jhonatanTeles.dto.out.TransacaoKafkaDTO;
import com.uploadBRL.jhonatanTeles.exeptions.ConflictExeption;
import com.uploadBRL.jhonatanTeles.infraestructure.TransactionEntity;
import com.uploadBRL.jhonatanTeles.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class GtwaryService {
    private final TransactionRepository tRepository;
    private final KafkaConvert convert;

    public GtwaryService(TransactionRepository tRepository, KafkaConvert convert){
        this.tRepository = tRepository;
        this.convert = convert;
    }

    public List<TransacaoKafkaDTO> listartransasoes(){
        List<TransactionEntity> transactionEntityList = tRepository.findAll();
        if(transactionEntityList.isEmpty()){
            throw new ConflictExeption("Nenhuma transações encontrada.");
        }
        return convert.transactionEntityList_P_TransacaoKafkaDTOList(transactionEntityList);
    }
}



