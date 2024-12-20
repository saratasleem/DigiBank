package com.example.DigitalBankingApp.Utility;

public enum ExceptionConstants {
	SERVER_ERROR("server.error"),
	AUTHENTICATION_FAILED("authentication.failed"),
	USER_NOT_FOUND("user.not.found"),
	USER_FOUND("user.found"),
	USERID_NOT_FOUND("user.id.not.found"),
	NO_USERS_FOUND("no.users.found"),
	NO_ACCOUNT_FOUND("no.account.found"),
	ACCOUNT_ALREADY_EXISTS("account.already.exists"),
	NO_ACCOUNT_IS_LINKED("no.account.is.linked"),
	INSUFFICIENT_FUNDS("insufficient.funds"),
	ACCOUNT_ALREADY_LINKED("account.linked.already"),
	NO_ACTIVE_TRANSACTIONS("no.active.transactions");
	
	private final String type;
	private ExceptionConstants(String type) {
		this.type=type;
	}
	@Override
	public String toString() {
		return this.type;
	}
	

}
