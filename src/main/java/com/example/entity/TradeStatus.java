package com.example.entity;

import lombok.Data;

/**
 * @author cyjoh 取引される商品を表すドメインです。
 *
 */
@Data
public class TradeStatus {

	private Integer id;
	private Integer key;
	private String value;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getKey() {
		return key;
	}

	public void setKey(Integer key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "TradeStatus [id=" + id + ", key=" + key + ", value=" + value + "]";
	}

}
