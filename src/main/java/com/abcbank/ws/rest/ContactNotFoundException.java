package com.abcbank.ws.rest;

public class ContactNotFoundException extends RuntimeException {

	public ContactNotFoundException(Long id) {
		super("Could not find employee " + id);
	}
}
