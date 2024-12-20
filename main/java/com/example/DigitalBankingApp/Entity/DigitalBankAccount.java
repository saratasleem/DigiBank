package com.example.DigitalBankingApp.Entity;

//import javax.validation.Valid;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class DigitalBankAccount {
	
	@Id
	//@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String digitalBankingId;
	private Long mobileNumber;
	private String accountType;
	//@Valid
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="account_number",unique=true)
	private BankAccount bankAccount;
	public String getDigitalBankingId() {
		return digitalBankingId;
	}
	public void setDigitalBankingId(String digitalBankingId) {
		this.digitalBankingId = digitalBankingId;
	}
	public Long getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(Long mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public BankAccount getBankAccount() {
		return bankAccount;
	}
	public void setBankAccount(BankAccount bankAccount) {
		this.bankAccount = bankAccount;
	}

}
