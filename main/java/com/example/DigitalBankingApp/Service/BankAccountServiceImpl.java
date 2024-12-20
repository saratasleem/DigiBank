package com.example.DigitalBankingApp.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.DigitalBankingApp.DTO.BankAccountDTO;
import com.example.DigitalBankingApp.Entity.BankAccount;
import com.example.DigitalBankingApp.Exception.DigitalBankingException;
import com.example.DigitalBankingApp.Repository.BankAccountRepository;
import com.example.DigitalBankingApp.Utility.ExceptionConstants;
@Service
public class BankAccountServiceImpl implements BankAccountService{
	@Autowired
	private BankAccountRepository bankAccountRepo;

	@Override
	public String createAccount(BankAccountDTO bankAccountDTO) throws DigitalBankingException {
		Optional<BankAccount> ba=bankAccountRepo.findById(bankAccountDTO.getAccountNumber());
		//BankAccount bc=ba.orElseThrow(()-> new DigitalBankingException("Your account already exists"));
		if(ba.isEmpty()) {
		BankAccount bacc= new BankAccount();
		bacc.setAccountNumber(bankAccountDTO.getAccountNumber());
		bacc.setAccountType(bankAccountDTO.getAccountType());
		bacc.setBalance(bankAccountDTO.getBalance());
		bacc.setBankName(bankAccountDTO.getBankName());
		bacc.setIfscCode(bankAccountDTO.getIfscCode());
		bacc.setMobileNumber(bankAccountDTO.getMobileNumber());
		bacc.setOpeningDate(bankAccountDTO.getOpeningDate());
		//bacc.setPin(bankAccountDTO.getPin()); //will use encoder later for safety
		bankAccountRepo.save(bacc);
		return bacc.getAccountNumber().toString(); //bcz id is in long so conversion
		} else {
			throw new DigitalBankingException(ExceptionConstants.ACCOUNT_ALREADY_EXISTS.toString());
		}
	}

	@Override
	public Double checkBalance(Long accountNumber, Long mobileNumber) throws DigitalBankingException {
		BankAccount ba=bankAccountRepo.findByAccountNumberAndMobileNumber(accountNumber, mobileNumber);
		if(ba==null) {
			throw new DigitalBankingException(ExceptionConstants.NO_ACCOUNT_IS_LINKED.toString());
		} 
		BankAccountDTO badto=new BankAccountDTO();
		badto.setBalance(ba.getBalance());
		return badto.getBalance();
	}

	@Override
	public String removeAccount(Long accountNumber) throws DigitalBankingException {
		
		return null;
	}

}
