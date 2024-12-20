package com.example.DigitalBankingApp.DTO;

import java.time.LocalDate;
import java.util.Objects;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;


public class BankAccountDTO {
	
	public BankAccountDTO() {
		super();
	}
	public BankAccountDTO(Long accountNumber, String bankName, Double balance, String accountType, String ifscCode,
			LocalDate openingDate, Long mobileNumber) {
		super();
		this.accountNumber = accountNumber;
		this.bankName = bankName;
		this.balance = balance;
		this.accountType = accountType;
		this.ifscCode = ifscCode;
		this.openingDate = openingDate;
		this.mobileNumber = mobileNumber;
	}
	@NotNull(message="bankaccount.accountnumber.absent")
	private Long accountNumber;
	//private String pin;
	@NotNull(message="bankaccount.bankname.absent")
	@Size(min=3,max=20,message="bankaccount.bankname.invalid")
	private String bankName;
	private Double balance;
	@NotNull(message="bankaccount.accounttype.absent")
	@Pattern(regexp="Salary|Savings|Current",message="bankaccount.accounttype.invalid")
	private String accountType;
	@NotNull(message="bankaccount.ifsccode.absent")
	private String ifscCode;
	@PastOrPresent(message="bankaccount.openingdate.invalid")
	private LocalDate openingDate;
	@NotNull(message="bankaccount.mobilenumber.absent")
	private Long mobileNumber;
	@Override
	public int hashCode() {
		return Objects.hash(accountNumber, accountType, balance, bankName, ifscCode, mobileNumber, openingDate);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BankAccountDTO other = (BankAccountDTO) obj;
		return Objects.equals(accountNumber, other.accountNumber) && Objects.equals(accountType, other.accountType)
				&& Objects.equals(balance, other.balance) && Objects.equals(bankName, other.bankName)
				&& Objects.equals(ifscCode, other.ifscCode) && Objects.equals(mobileNumber, other.mobileNumber)
				&& Objects.equals(openingDate, other.openingDate);
	}
	@Override
	public String toString() {
		return "BankAccountDTO [accountNumber=" + accountNumber + ", bankName=" + bankName + ", balance=" + balance
				+ ", accountType=" + accountType + ", ifscCode=" + ifscCode + ", openingDate=" + openingDate
				+ ", mobileNumber=" + mobileNumber + "]";
	}
	public Long getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(Long accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public Double getBalance() {
		return balance;
	}
	public void setBalance(Double balance) {
		this.balance = balance;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public String getIfscCode() {
		return ifscCode;
	}
	public void setIfscCode(String ifscCode) {
		this.ifscCode = ifscCode;
	}
	public LocalDate getOpeningDate() {
		return openingDate;
	}
	public void setOpeningDate(LocalDate openingDate) {
		this.openingDate = openingDate;
	}
	public Long getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(Long mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	
	

}
