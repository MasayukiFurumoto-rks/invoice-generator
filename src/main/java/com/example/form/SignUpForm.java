package com.example.form;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class SignUpForm {

	@NotBlank(message = "苗字は必ず入力してください。")
	private String lastName;

	@NotBlank(message = "名前は必ず入力してください。")
	private String firstName;

	@NotBlank(message = "メールアドレスは必ず入力してください。")
	@Email(message = "メールアドレスの形式が不正です。")
	private String email;

	@NotBlank(message = "パスワードは必ず入力してください。")
	private String password;

	@NotBlank(message = "確認用パスワードは必ず入力してください。")
	private String confirmationPassword;
	private String authenticationCode;

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
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
		return "SignUpForm [lastName=" + lastName + ", firstName=" + firstName + ", email=" + email + ", password="
				+ password + ", confirmationPassword=" + confirmationPassword + ", authenticationCode="
				+ authenticationCode + "]";
	}

}