package com.uploadBRL.jhonatanTeles.repository;

import com.uploadBRL.jhonatanTeles.infraestructure.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionEntity, Long> {
    List<TransactionEntity>findBydataSpedBetween(LocalDateTime dataSpedInicial, LocalDateTime dataSpedFinal);
}
