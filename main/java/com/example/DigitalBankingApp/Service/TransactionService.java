package com.example.DigitalBankingApp.Service;

import java.util.List;

import com.example.DigitalBankingApp.DTO.TransactionDTO;
import com.example.DigitalBankingApp.Exception.DigitalBankingException;

public interface TransactionService {
	
	public List<TransactionDTO> accountStatement(Long mobileNumber) throws DigitalBankingException;
	public String fundTransfer(TransactionDTO transactionDTO) throws DigitalBankingException;

}
