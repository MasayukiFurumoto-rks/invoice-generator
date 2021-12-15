package com.example.entity;

import lombok.Data;

/**
 * @author cyjoh 取引される商品を表すドメインです。
 *
 */
@Data
public class TradingItem {

	private Integer id;
	private Integer itemId;
	private Item item;
	private Integer quantity;
	private Integer tradeId;
	private String remarks;
	private Integer ownerId;
	private User owner;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getItemId() {
		return itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Integer getTradeId() {
		return tradeId;
	}

	public void setTradeId(Integer tradeId) {
		this.tradeId = tradeId;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
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

	@Override
	public String toString() {
		return "TradingItem [id=" + id + ", itemId=" + itemId + ", item=" + item + ", quantity=" + quantity
				+ ", tradeId=" + tradeId + ", remarks=" + remarks + ", ownerId=" + ownerId + ", owner=" + owner + "]";
	}

}
