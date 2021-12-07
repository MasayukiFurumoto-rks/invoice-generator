package com.example.form;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class SignUpForm {
	
	@NotBlank(message="氏名は必ず入力してください。")
	private String name;
	
	@NotBlank(message="メールアドレスは必ず入力してください。")
	@Email(message="メールアドレスの形式が不正です。")
	private String email;
	
	@NotBlank(message="パスワードは必ず入力してください。")
	private String password;

	@NotBlank(message="確認用パスワードは必ず入力してください。")
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
