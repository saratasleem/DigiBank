package com.example.DigitalBankingApp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.DigitalBankingApp.Entity.DigitalBankAccount;
@Repository
public interface DigitalBankAccountRepository extends JpaRepository<DigitalBankAccount,String> {
	//@Query("select d from DigitalBankAccount d where d.mobileNumber=?1 and d.accountNumber=?2")
    public DigitalBankAccount findByMobileNumberAndBankAccountAccountNumber(Long mobileNumber,Long accountNumber);
}
