package com.example.DigitalBankingApp.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.DigitalBankingApp.Entity.BankAccount;
import com.example.DigitalBankingApp.Entity.DigitalBankAccount;
import com.example.DigitalBankingApp.Exception.DigitalBankingException;
import com.example.DigitalBankingApp.Repository.BankAccountRepository;
import com.example.DigitalBankingApp.Repository.DigitalBankAccountRepository;
import com.example.DigitalBankingApp.Utility.ExceptionConstants;
@Service
public class DigitalBankAccountServiceImpl implements DigitalBankAccountService {
	@Autowired
	private DigitalBankAccountRepository digibankRepo;
	@Autowired
	private BankAccountRepository bankaccRepo;
	static int count=1003;

	@Override
	public String linkAccount(Long mobileNumber, Long accountNumber) throws DigitalBankingException {
		 DigitalBankAccount digi=digibankRepo.findByMobileNumberAndBankAccountAccountNumber(mobileNumber, accountNumber);
		 if(digi!=null) {
			 throw new DigitalBankingException(ExceptionConstants.ACCOUNT_ALREADY_LINKED.toString());
		 }
		 Optional<BankAccount> ba=bankaccRepo.findById(accountNumber);
		 BankAccount bankac=ba.orElseThrow(()-> new DigitalBankingException(ExceptionConstants.NO_ACCOUNT_FOUND.toString()));
		 String id="w-"+(++count);
		 DigitalBankAccount digiacc = new DigitalBankAccount();
		 digiacc.setDigitalBankingId(id);
		 digiacc.setMobileNumber(mobileNumber); //have to check this
		 digiacc.setAccountType(bankac.getAccountType());
		 digiacc.setBankAccount(bankac);
		 digibankRepo.save(digiacc);
		return digiacc.getDigitalBankingId();
	}

}
