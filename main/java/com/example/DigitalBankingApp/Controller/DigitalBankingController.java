package com.example.DigitalBankingApp.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.DigitalBankingApp.DTO.BankAccountDTO;
import com.example.DigitalBankingApp.DTO.TransactionDTO;
import com.example.DigitalBankingApp.Exception.DigitalBankingException;
import com.example.DigitalBankingApp.Service.BankAccountService;
import com.example.DigitalBankingApp.Service.DigitalBankAccountService;
import com.example.DigitalBankingApp.Service.TransactionService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value="digibank")
@Validated
public class DigitalBankingController {
	@Autowired
	private BankAccountService bankAccountService;
	@Autowired
	private DigitalBankAccountService digibankService;
	@Autowired
	private TransactionService transSer;
	@Autowired
	private Environment env;
	@PostMapping(value="/accounts")
	public ResponseEntity<String> createAccount(@Valid @RequestBody BankAccountDTO bankAccountDTO) throws DigitalBankingException{
		String account=bankAccountService.createAccount(bankAccountDTO);
		return new ResponseEntity<>(env.getProperty("API.Insert_Success")+": "+account,HttpStatus.CREATED);
	}
	@GetMapping(value="/checkbal/{accountNumber}/{mobileNumber}")
	public ResponseEntity<Double> checkBalance(@PathVariable Long accountNumber,@PathVariable Long mobileNumber) throws DigitalBankingException{
		Double d=bankAccountService.checkBalance(accountNumber, mobileNumber);
		return new ResponseEntity<>(d,HttpStatus.OK);
	}
	@PostMapping(value="/linkacc")
	public ResponseEntity<String>linkAccount(@RequestParam Long mobileNumber,@RequestParam Long accountNumber)throws DigitalBankingException{
		String link=digibankService.linkAccount(mobileNumber, accountNumber);
		return new ResponseEntity<>(env.getProperty("API.Link_Success")+link,HttpStatus.OK);
	}
	@GetMapping(value="/accountstatement")
	public ResponseEntity<List<TransactionDTO>> accountStatement(@RequestParam Long mobileNumber) throws DigitalBankingException{
		List<TransactionDTO> ld=transSer.accountStatement(mobileNumber);
		return new ResponseEntity<>(ld,HttpStatus.OK);
	}
	@PostMapping(value="/fundtrans")
	public ResponseEntity<String> fundTransfer(@RequestBody TransactionDTO transactionDTO) throws DigitalBankingException{
		String f=transSer.fundTransfer(transactionDTO);
		return new ResponseEntity<>(env.getProperty("API.Fund_Transfer_Success")+f,HttpStatus.OK);
	}
	
	
	
	

}
