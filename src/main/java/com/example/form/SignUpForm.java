package com.example.form;

public class SignUpForm {

	private String name;
	private String email;
	private String password;
	private String confirmationPassword;
	private String authenticationCode;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmationPassword() {
		return confirmationPassword;
	}

	public void setConfirmationPassword(String confirmationPassword) {
		this.confirmationPassword = confirmationPassword;
	}

	public String getAuthenticationCode() {
		return authenticationCode;
	}

	public void setAuthenticationCode(String authenticationCode) {
		this.authenticationCode = authenticationCode;
	}

	@Override
	public String toString() {
		return "SignUpForm [name=" + name + ", email=" + email + ", password=" + password + ", confirmationPassword="
				+ confirmationPassword + ", authenticationCode=" + authenticationCode + "]";
	}

}
