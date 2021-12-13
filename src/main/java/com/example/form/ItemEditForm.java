package com.example.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * @author cyjoh 商品データを編集するためにデータを受け取るフォームクラスです。
 */
/**
 * @author cyjoh
 *
 */
public class ItemEditForm {

	private Integer id;

	@NotBlank(message = "商品名は必ず入力してください。")
	private String name;

	@Pattern(regexp = "^[0-9]|[1-9][0-9]{1,7}$", message = "価格は数字で入力してください。")
	private String price;

	private String description;

	private Integer ownerId;

	/**
	 * Integer型で単価を返すメソッドです。
	 * 
	 * @return Integer型の単価
	 */
	public Integer getIntPrice() {
		return Integer.parseInt(price);
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

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(Integer ownerId) {
		this.ownerId = ownerId;
	}

	@Override
	public String toString() {
		return "ItemEditForm [id=" + id + ", name=" + name + ", price=" + price + ", description=" + description
				+ ", ownerId=" + ownerId + "]";
	}

}
