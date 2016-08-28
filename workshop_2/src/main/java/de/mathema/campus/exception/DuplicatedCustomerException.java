package de.mathema.campus.exception;

@SuppressWarnings("serial")
public class DuplicatedCustomerException extends RuntimeException {
	public DuplicatedCustomerException(String message) {
		super(message);
	}
}
