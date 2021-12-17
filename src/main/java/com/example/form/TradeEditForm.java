package com.example.form;

import java.sql.Date;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import lombok.Data;

/**
 * @author cyjoh 商談データを編集するためにデータを受け取るフォームクラスです。
 *
 */
@Data
public class TradeEditForm {

	private Integer id;

	/** 商談名 */
	@NotBlank(message = "商談名は必ず入力してください。")
	private String name;

	/** 商談ステータス */
	@Min(value = 0, message = "商談ステータスは必ず指定してください。")
	private Integer statusKey;

	/** 最終更新日 */
	@NotBlank(message = "最終更新日は必ず入力してください。")
	private String date;

	/** 取引先担当者ID */
	private Integer contactId;

	/** 所有者ID */
	private Integer ownerId;

	/** 備考 */
	private String remarks;
	
	
	public Date getDateAsSqlDate() {
		return Date.valueOf(date);
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

	public Integer getStatusKey() {
		return statusKey;
	}

	public void setStatusKey(Integer statusKey) {
		this.statusKey = statusKey;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Integer getContactId() {
		return contactId;
	}

	public void setContactId(Integer contactId) {
		this.contactId = contactId;
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

	@Override
	public String toString() {
		return "TradeEditForm [id=" + id + ", name=" + name + ", statusKey=" + statusKey + ", date=" + date
				+ ", contactId=" + contactId + ", ownerId=" + ownerId + ", remarks=" + remarks + "]";
	}

}
