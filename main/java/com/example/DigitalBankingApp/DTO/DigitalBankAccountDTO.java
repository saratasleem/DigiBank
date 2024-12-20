package com.example.DigitalBankingApp.DTO;

import java.util.Objects;

import com.example.DigitalBankingApp.Entity.BankAccount;

public class DigitalBankAccountDTO {
	public DigitalBankAccountDTO() {
		super();
		
	}
	public DigitalBankAccountDTO(String digitalBankingId, Long mobileNumber, BankAccount bankAccount, String accountType) {
		super();
		this.digitalBankingId = digitalBankingId;
		this.mobileNumber = mobileNumber;
		this.setBankAccount(bankAccount);
		this.accountType = accountType;
	}
	private String digitalBankingId;
	private Long mobileNumber;
	private BankAccount bankAccount;
	private String accountType;
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
	@Override
	public int hashCode() {
		return Objects.hash(bankAccount, accountType, digitalBankingId, mobileNumber);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DigitalBankAccountDTO other = (DigitalBankAccountDTO) obj;
		return Objects.equals(bankAccount, other.bankAccount) && Objects.equals(accountType, other.accountType)
				&& Objects.equals(digitalBankingId, other.digitalBankingId)
				&& Objects.equals(mobileNumber, other.mobileNumber);
	}
	@Override
	public String toString() {
		return "DigitalBankAccountDTO [digitalBankingId=" + digitalBankingId + ", mobileNumber=" + mobileNumber
				+ ", bankAccount=" + bankAccount + ", accountType=" + accountType + "]";
	}
	public BankAccount getBankAccount() {
		return bankAccount;
	}
	public void setBankAccount(BankAccount bankAccount) {
		this.bankAccount = bankAccount;
	} 

}
