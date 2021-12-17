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
public class TradeInsertForm {

	/** 商談名 */
	@NotBlank(message = "商談名は必ず入力してください。")
	private String name;

	/** 商談ステータス */
	@Min(value = 1, message = "商談ステータスは必ず指定してください。")
	private Integer statusKey;

	/** 最終更新日 */
	@NotBlank(message = "最終更新日は必ず入力してください。")
	private String date;

	/** 取引先担当者ID */
	@Min(value = 1, message = "取引先担当者は必ず指定してください。")
	private Integer contactId;

	/** 所有者ID */
	private Integer ownerId;

	/** 備考 */
	private String remarks;

	public Date getDateAsSqlDate() {
		return Date.valueOf(date);
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
		return "TradeInsertForm [name=" + name + ", statusKey=" + statusKey + ", date=" + date + ", contactId="
				+ contactId + ", ownerId=" + ownerId + ", remarks=" + remarks + "]";
	}

}
