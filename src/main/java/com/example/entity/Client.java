package com.example.entity;

import java.util.List;

import lombok.Data;

/**
 * @author cyjoh
 *
 */
@Data
public class Client {
	private Integer id;
	private String name;
	private String zipcode;
	private String address;
	private String building;
	private String telephone;
	private Integer creditLimit;
	private Integer ownerId;
	private User owner;
	private String remarks;
	private boolean deleted;
	private List<Trade> tradeList;

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

	public Integer getCreditLimit() {
		return creditLimit;
	}

	public void setCreditLimit(Integer creditLimit) {
		this.creditLimit = creditLimit;
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

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public List<Trade> getTradeList() {
		return tradeList;
	}

	public void setTradeList(List<Trade> tradeList) {
		this.tradeList = tradeList;
	}

	@Override
	public String toString() {
		return "Client [id=" + id + ", name=" + name + ", zipcode=" + zipcode + ", address=" + address + ", building="
				+ building + ", telephone=" + telephone + ", creditLimit=" + creditLimit + ", ownerId=" + ownerId
				+ ", owner=" + owner + ", remarks=" + remarks + ", deleted=" + deleted + ", tradeList=" + tradeList
				+ "]";
	}

}
