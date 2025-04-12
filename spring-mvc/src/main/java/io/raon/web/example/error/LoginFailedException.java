package io.raon.web.example.error;

public class LoginFailedException extends RuntimeException {
	public LoginFailedException(String message) {
		super(message);
	}
}