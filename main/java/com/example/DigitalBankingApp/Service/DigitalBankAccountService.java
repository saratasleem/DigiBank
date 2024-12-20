package com.example.DigitalBankingApp.Service;

import com.example.DigitalBankingApp.Exception.DigitalBankingException;

public interface DigitalBankAccountService {
	
	public String linkAccount(Long mobileNumber,Long accountNumber)throws DigitalBankingException;
    
}
