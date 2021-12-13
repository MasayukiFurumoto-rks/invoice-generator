package com.example.form;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class SignUpForm {

	@NotBlank(message = "苗字は必ず入力してください。")
	private String lastName;

	@NotBlank(message = "名前は必ず入力してください。")
	private String firstName;

	@NotNull(message = "部署を選択してください。")
	private Integer departmentId;
	
	@Size(max=30,message="30字以内で入力してください。")
	private String position;

	@NotBlank(message = "メールアドレスは必ず入力してください。")
	@Email(message = "メールアドレスの形式が不正です。")
	private String email;

	@NotBlank(message = "パスワードは必ず入力してください。")
	private String password;

	@NotBlank(message = "確認用パスワードは必ず入力してください。")
	private String confirmationPassword;
	private String authenticationCode;

//	public Integer getIntDepartmentId() {
//		return Integer.parseInt(departmentId);
//	}

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

	public Integer getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
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
		return "SignUpForm [lastName=" + lastName + ", firstName=" + firstName + ", departmentId=" + departmentId
				+ ", position=" + position + ", email=" + email + ", password=" + password + ", confirmationPassword="
				+ confirmationPassword + ", authenticationCode=" + authenticationCode + "]";
	}

}