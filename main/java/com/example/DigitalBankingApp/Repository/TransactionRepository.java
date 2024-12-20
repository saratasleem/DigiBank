package com.example.DigitalBankingApp.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.DigitalBankingApp.Entity.Transaction;
@Repository
public interface TransactionRepository extends JpaRepository<Transaction,Integer> {
	List<Transaction> findByPaidFrom(Long mobileNumber);

}
