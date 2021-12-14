package com.example.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import lombok.Data;

/**
 * @author cyjoh 商品データを編集するためにデータを受け取るフォームクラスです。
 *
 */
@Data
public class ClientEditForm {

	private Integer id;

	/** 企業名 */
	@NotBlank(message = "商品名は必ず入力してください。")
	private String name;

	/** 郵便番号 */
	@Pattern(regexp = "^[0-9]{3}-[0-9]{4}$", message = "郵便番号はハイフンありの形式で入力してください。")
	private String zipcode;

	/** 住所 */
	@NotBlank(message = "住所は必ず入力してください。")
	private String address;

	/** 建物 */
	private String building;

	/** 電話番号 */
	@Pattern(regexp = "^0[-0-9]{11,12}$", message = "電話番号はハイフンありの形式で入力してください。")
	private String telephone;

	/** 与信限度額 */
	@Pattern(regexp = "^[0-9]|[1-9][0-9]{1,8}|1[0-9]{9}|2000000000$", message = "与信限度額は0~20億の間の整数で入力してください。。")
	private String creditLimit;

	/** 所有者ID */
	private Integer ownerId;

	/** 備考 */
	private String remarks;

	/** 削除済みフラグ */
	private boolean deleted;

	public Integer getIntCreditLimit() {
		return Integer.parseInt(creditLimit);
	}

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

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getBuilding() {
		return building;
	}

	public void setBuilding(String building) {
		this.building = building;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getCreditLimit() {
		return creditLimit;
	}

	public void setCreditLimit(String creditLimit) {
		this.creditLimit = creditLimit;
	}

	public Integer getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(Integer ownerId) {
		this.ownerId = ownerId;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	@Override
	public String toString() {
		return "ClientEditForm [id=" + id + ", name=" + name + ", zipcode=" + zipcode + ", address=" + address
				+ ", building=" + building + ", telephone=" + telephone + ", creditLimit=" + creditLimit + ", ownerId="
				+ ownerId + ", remarks=" + remarks + ", deleted=" + deleted + "]";
	}

}
