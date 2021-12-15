package com.example.entity;

import java.sql.Date;
import java.util.List;

import lombok.Data;

/**
 * @author cyjoh 商品を表すドメインです。
 *
 */
@Data
public class Trade {
	private Integer id;
	private Integer statusId;
	private String status;
	private Date date;
	private Contact contact;
	private boolean deleted;
	private Integer ownerId;
	private User owner;
	private List<TradingItem> tradingItemList;
	private Integer invoiceId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getStatusId() {
		return statusId;
	}

	public void setStatusId(Integer statusId) {
		this.statusId = statusId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Contact getContact() {
		return contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public Integer getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(Integer ownerId) {
		this.ownerId = ownerId;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public List<TradingItem> getTradingItemList() {
		return tradingItemList;
	}

	public void setTradingItemList(List<TradingItem> tradingItemList) {
		this.tradingItemList = tradingItemList;
	}

	public Integer getInvoiceId() {
		return invoiceId;
	}

	public void setInvoiceId(Integer invoiceId) {
		this.invoiceId = invoiceId;
	}

	@Override
	public String toString() {
		return "Trade [id=" + id + ", statusId=" + statusId + ", status=" + status + ", date=" + date + ", contact="
				+ contact + ", deleted=" + deleted + ", ownerId=" + ownerId + ", owner=" + owner + ", tradingItemList="
				+ tradingItemList + ", invoiceId=" + invoiceId + "]";
	}

}
