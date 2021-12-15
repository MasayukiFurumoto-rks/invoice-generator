package com.example.form;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.Data;

/**
 * @author cyjoh 取引先担当者データを編集するためにデータを受け取るフォームクラスです。
 *
 */
@Data
public class ContactEditForm {

	private Integer id;

	/** 取引先担当者名 */
	@NotBlank(message = "取引先担当者名は必ず入力してください。")
	private String name;

	/** 取引先ID */
	@NotNull(message = "取引先は必ず指定してください。")
	private Integer clientId;

	/** 所属部署 */
	@NotBlank(message = "所属部署は必ず入力してください。")
	private String department;

	/** 役職 */
	private String position;

	/** 電話番号 */
	@Pattern(regexp = "^0\\d{1,3}-\\d{2,4}-\\d{4}$", message = "電話番号はハイフンありの形式(000-0000-0000)で入力してください。")
	private String telephone;

	/** メールアドレス */
	@NotBlank(message = "メールアドレスは必ず入力してください。")
	@Email(message = "メールアドレスの形式が不正です。")
	private String email;

	/** 所有者ID */
	private Integer ownerId;

	/** 削除済みフラグ */
	private boolean deleted;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getClientId() {
		return clientId;
	}

	public void setClientId(Integer clientId) {
		this.clientId = clientId;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(Integer ownerId) {
		this.ownerId = ownerId;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	@Override
	public String toString() {
		return "ContactEditForm [id=" + id + ", name=" + name + ", clientId=" + clientId + ", department=" + department
				+ ", position=" + position + ", telephone=" + telephone + ", email=" + email + ", ownerId=" + ownerId
				+ ", deleted=" + deleted + "]";
	}

}
