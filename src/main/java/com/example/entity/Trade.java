package com.example.entity;

import java.sql.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

import org.springframework.util.ObjectUtils;

import lombok.Data;

/**
 * @author cyjoh 商品を表すドメインです。
 *
 */
@Data
public class Trade {
	private Integer id;
	private String name;
	private Integer statusKey;
	private String statusValue;
	private Date date;
	private Integer contactId;
	private Contact contact;
	private Integer ownerId;
	private User owner;
	private String remarks;
	private List<TradingItem> tradingItemList;
	private boolean deleted;
	private Integer invoiceId;

	public int getCalcTotalPrice() {
		int totalPrice = 0;
		if(Objects.nonNull(tradingItemList)) {
			if(tradingItemList.size()!=0) {
				for (TradingItem item : tradingItemList) {
					totalPrice += item.getSubTotal();
				}
			}
		}

		
		
		return totalPrice;
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

	public String getStatusValue() {
		return statusValue;
	}

	public void setStatusValue(String statusValue) {
		this.statusValue = statusValue;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Integer getContactId() {
		return contactId;
	}

	public void setContactId(Integer contactId) {
		this.contactId = contactId;
	}

	public Contact getContact() {
		return contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
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

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public List<TradingItem> getTradingItemList() {
		return tradingItemList;
	}

	public void setTradingItemList(List<TradingItem> tradingItemList) {
		this.tradingItemList = tradingItemList;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public Integer getInvoiceId() {
		return invoiceId;
	}

	public void setInvoiceId(Integer invoiceId) {
		this.invoiceId = invoiceId;
	}

	@Override
	public String toString() {
		return "Trade [id=" + id + ", name=" + name + ", statusKey=" + statusKey + ", statusValue=" + statusValue
				+ ", date=" + date + ", contactId=" + contactId + ", contact=" + contact + ", ownerId=" + ownerId
				+ ", owner=" + owner + ", remarks=" + remarks + ", tradingItemList=" + tradingItemList + ", deleted="
				+ deleted + ", invoiceId=" + invoiceId + "]";
	}

}
