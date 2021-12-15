package com.example.entity;

import lombok.Data;

/**
 * @author cyjoh
 *
 */
@Data
public class Contact {

	private Integer id;
	private String name;
	private Integer clientId;
	private Client belongs;
	private String department;
	private String position;
	private String telephone;
	private String email;
	private Integer ownerId;
	private User owner;
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

	public Client getBelongs() {
		return belongs;
	}

	public void setBelongs(Client belongs) {
		this.belongs = belongs;
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

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	@Override
	public String toString() {
		return "Contact [id=" + id + ", name=" + name + ", clientId=" + clientId + ", belongs=" + belongs
				+ ", department=" + department + ", position=" + position + ", telephone=" + telephone + ", email="
				+ email + ", ownerId=" + ownerId + ", owner=" + owner + ", deleted=" + deleted + "]";
	}

}
