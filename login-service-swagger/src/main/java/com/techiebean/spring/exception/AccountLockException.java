package com.techiebean.spring.exception;

public class AccountLockException extends RuntimeException{

	public AccountLockException (String id) {
		super("Your account with id " + id +" is locked. Please contact customer support.");
	}
}
