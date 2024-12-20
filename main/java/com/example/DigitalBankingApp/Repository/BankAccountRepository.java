package com.example.DigitalBankingApp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.DigitalBankingApp.Entity.BankAccount;
@Repository
public interface BankAccountRepository extends JpaRepository<BankAccount,Long>{
	@Query("Select b from BankAccount b where b.accountNumber=?1 and b.mobileNumber=?2")
	public BankAccount findByAccountNumberAndMobileNumber(Long accountNumber,Long mobileNumber);

}
