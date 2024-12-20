package com.example.DigitalBankingApp.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.DigitalBankingApp.DTO.TransactionDTO;
import com.example.DigitalBankingApp.Entity.BankAccount;
import com.example.DigitalBankingApp.Entity.Transaction;
import com.example.DigitalBankingApp.Exception.DigitalBankingException;
import com.example.DigitalBankingApp.Repository.BankAccountRepository;
import com.example.DigitalBankingApp.Repository.TransactionRepository;
import com.example.DigitalBankingApp.Utility.ExceptionConstants;

import jakarta.transaction.Transactional;
@Service
@Transactional
public class TransactionServiceImpl implements TransactionService{
	@Autowired
	private TransactionRepository transactionRepo;
	@Autowired
	private BankAccountRepository bankrepo;
	
	private static int count=123456790;

	@Override
	public List<TransactionDTO> accountStatement(Long mobileNumber) throws DigitalBankingException {
		List<Transaction> li=transactionRepo.findByPaidFrom(mobileNumber);
		if(li.isEmpty()) {
			throw new DigitalBankingException(ExceptionConstants.NO_ACTIVE_TRANSACTIONS.toString());
		}
		List<TransactionDTO> transdto=new ArrayList<>();
		for(Transaction trans:li) {
			//dala dto mein uthaya entity se -> entity to dto conversion
			TransactionDTO transactiondto= new TransactionDTO();
			transactiondto.setModeOfTransaction(trans.getModeOfTransaction());
			transactiondto.setAmount(trans.getAmount());
			transactiondto.setPaidFrom(trans.getPaidFrom());
			transactiondto.setPaidTo(trans.getPaidTo());
			transactiondto.setReceiverAccountNumber(trans.getReceiverAccountNumber());
			transactiondto.setRemarks(trans.getRemarks());
			transactiondto.setSenderAccountNumber(trans.getSenderAccountNumber());
			transactiondto.setTransactionDateTime(trans.getTransactionDateTime());
			transactiondto.setTransactionId(trans.getTransactionId());
			transdto.add(transactiondto);
		}
		
		return transdto;
	}

	@Override
	public String fundTransfer(TransactionDTO transactionDTO) throws DigitalBankingException {
		//sender id->sid
		Optional<BankAccount> sid= bankrepo.findById(transactionDTO.getSenderAccountNumber());
		BankAccount ba=sid.orElseThrow(()->new DigitalBankingException(ExceptionConstants.NO_ACCOUNT_FOUND.toString()));
		//receiver id->rid
		Optional<BankAccount> rid= bankrepo.findById(transactionDTO.getReceiverAccountNumber());
		BankAccount baa=rid.orElseThrow(()->new DigitalBankingException(ExceptionConstants.NO_ACCOUNT_FOUND.toString()));
		//checking balance
		if(transactionDTO.getAmount()>ba.getBalance()) {
			throw new DigitalBankingException(ExceptionConstants.INSUFFICIENT_FUNDS.toString());
		}
		//deducting the amount from sender entity
		ba.setBalance(ba.getBalance()-transactionDTO.getAmount());
		//adding amount in the receiver entity
		baa.setBalance(baa.getBalance()+transactionDTO.getAmount());
		//because saving something into db so entity and not dto
		Transaction trans = new Transaction();
		trans.setTransactionId(++count);
		trans.setModeOfTransaction(transactionDTO.getModeOfTransaction());
		trans.setPaidFrom(transactionDTO.getPaidFrom());
		trans.setAmount(transactionDTO.getAmount());
		trans.setPaidTo(transactionDTO.getPaidTo());
		trans.setReceiverAccountNumber(transactionDTO.getReceiverAccountNumber());
		trans.setSenderAccountNumber(transactionDTO.getSenderAccountNumber());
		trans.setRemarks(transactionDTO.getRemarks());
		trans.setTransactionDateTime(transactionDTO.getTransactionDateTime());
		transactionRepo.save(trans);
		return trans.getTransactionId().toString();
	}

}
