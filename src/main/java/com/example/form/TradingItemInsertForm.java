package com.example.form;

import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

import lombok.Data;

/**
 * @author cyjoh 商談商品データを編集するためにデータを受け取るフォームクラスです。
 *
 */
@Data
public class TradingItemInsertForm {

	private Integer tradeId;

	@Min(value = 1, message = "商品は必ず指定してください。")
	private Integer itemId;

	@Pattern(regexp = "^[0-9０-９]{1,}$", message = "数量は数値の形式で必ず指定してください。")
	private String quantity;

	private String remarks;

	/**
	 * Integer型で単価を返すメソッドです。
	 * 
	 * @return Integer型の単価
	 */
	public Integer getIntQuantity() {
		return Integer.parseInt(quantity);
	}

	public Integer getTradeId() {
		return tradeId;
	}

	public void setTradeId(Integer tradeId) {
		this.tradeId = tradeId;
	}

	public Integer getItemId() {
		return itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	@Override
	public String toString() {
		return "TradingItemInsertForm [tradeId=" + tradeId + ", itemId=" + itemId + ", quantity=" + quantity
				+ ", remarks=" + remarks + "]";
	}

}