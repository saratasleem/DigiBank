package com.example.DigitalBankingApp.Service;

import com.example.DigitalBankingApp.DTO.BankAccountDTO;
import com.example.DigitalBankingApp.Exception.DigitalBankingException;

public interface BankAccountService {
	
	public String createAccount(BankAccountDTO bankAccountDTO) throws DigitalBankingException;
	public Double checkBalance(Long accountNumber, Long mobileNumber)throws DigitalBankingException;
	public String removeAccount(Long accountNumber) throws DigitalBankingException;

}
