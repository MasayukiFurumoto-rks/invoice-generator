package com.example.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.entity.Item;
import com.example.entity.TradingItem;
import com.example.entity.User;
import com.example.form.TradingItemEditForm;
import com.example.form.TradingItemInsertForm;
import com.example.service.ItemService;
import com.example.service.TradeService;
import com.example.service.TradingItemService;
import com.example.service.UserService;

@Controller
@RequestMapping("/tradingItem")
public class TradingItemController {

	@Autowired
	private HttpSession session;

	@Autowired
	private UserService userService;

	@Autowired
	private ItemService itemService;
	
	@Autowired
	private TradeService tradeService;

	@Autowired
	private TradingItemService tradingItemService;

	@ModelAttribute
	private TradingItemEditForm setUpTradingItemEditForm() {
		TradingItemEditForm tradingItemEditForm = new TradingItemEditForm();
		if (session.getAttribute("editingTradingItem") != null) {
			TradingItem sessionTradingItem = (TradingItem) session.getAttribute("editingTradingItem");
			BeanUtils.copyProperties(sessionTradingItem, tradingItemEditForm);
			tradingItemEditForm.setQuantity(String.valueOf(sessionTradingItem.getQuantity()));
			session.removeAttribute("editingTradingItem");
		}
		return tradingItemEditForm;
	}
	
	@ModelAttribute
	private TradingItemInsertForm setUpTradingItemInsertForm() {
		TradingItemInsertForm tradingItemInsertForm = new TradingItemInsertForm();
		if (session.getAttribute("insertingTradingItem") != null) {
			TradingItem sessionTradingItem = (TradingItem) session.getAttribute("insertingTradingItem");
			tradingItemInsertForm.setQuantity(String.valueOf(sessionTradingItem.getQuantity()));
			BeanUtils.copyProperties(sessionTradingItem, tradingItemInsertForm);
			session.removeAttribute("insertingTradingItem");
		}
		return tradingItemInsertForm;
	}

	@RequestMapping("/edit")
	public String edit(Model model, Integer id) {
		User signInUser = (User) session.getAttribute("user");
		Integer comIdOfUser = signInUser.getDepartment().getCompanyId();

		// 編集しようとしている商品の情報を一緒に表示するためにロード
		TradingItem tradingItem = tradingItemService.findById(id, comIdOfUser);
		model.addAttribute("tradingItem", tradingItem);

		// 同じ企業の人が持っている担当者リストを選ぶために
		List<Item> itemList = itemService.findByCompanyId(comIdOfUser);
		model.addAttribute("itemList", itemList);

		return "tradingItem/tradingItem-edit.html";
	}

	@RequestMapping("/edit/confirm")
	public String editConfirm(@Validated TradingItemEditForm form, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return edit(model, form.getId());
		}
		User signInUser = (User) session.getAttribute("user");
		Integer comIdOfUser = signInUser.getDepartment().getCompanyId();

		TradingItem tradingItem = tradingItemService.findById(form.getId(), comIdOfUser);
		
		TradingItem editedTradingItem = tradingItemService.findById(form.getId(), comIdOfUser);
		
		BeanUtils.copyProperties(form, editedTradingItem);
		editedTradingItem.setOwner(userService.findById(form.getOwnerId()));
		editedTradingItem.setItem(itemService.findById(form.getItemId(), comIdOfUser));
		editedTradingItem.setQuantity(form.getIntQuantity());

		System.out.println("editedTradingItem:" + editedTradingItem);

		model.addAttribute("tradingItem", tradingItem);
		model.addAttribute("editedTradingItem", editedTradingItem);
		session.setAttribute("editingTradingItem", editedTradingItem);
		return "tradingItem/tradingItem-edit-confirm.html";
	}

	@RequestMapping("/edit/finish")
	public String editFinish(TradingItemEditForm form, Model model) {
		TradingItem tradingItem = new TradingItem();
		BeanUtils.copyProperties(form, tradingItem);
		tradingItem.setQuantity(form.getIntQuantity());

		tradingItemService.updateTradingItem(tradingItem);
		if (session.getAttribute("editingTradingItem") != null) {
			session.removeAttribute("editingTradingItem");
		}
		return "redirect:/trade/showDetail?id=" + form.getTradeId();
	}

//	@RequestMapping("/delete/confirm")
//	public String deleteConfirm(Integer id, Model model) {
//		User signInUser = (User) session.getAttribute("user");
//		Integer comIdOfUser = signInUser.getDepartment().getCompanyId();
//
//		TradingItem tradingItem = tradingItemService.findById(id, comIdOfUser);
//		model.addAttribute("tradingItem", tradingItem);
//		return "tradingItem/tradingItem-delete-confirm.html";
//
//	}
//
//	@RequestMapping("/delete/finish")
//	public String deleteFinish(Integer id, Model model) {
//		User signInUser = (User) session.getAttribute("user");
//		Integer comIdOfUser = signInUser.getDepartment().getCompanyId();
//
//		TradingItem tradingItem = tradingItemService.findById(id, comIdOfUser);
//		tradingItem.setDeleted(true);
//		tradingItemService.updateTradingItem(tradingItem);
//		return "redirect:/tradingItem/showList";
//
//	}

	@RequestMapping("/insert")
	public String insert(Model model,Integer tradeId) {
		User signInUser = (User) session.getAttribute("user");
		Integer comIdOfUser = signInUser.getDepartment().getCompanyId();

		// 同じ企業の人が持っている担当者リストを選ぶために
		List<Item> itemList = itemService.findByCompanyId(comIdOfUser);
		model.addAttribute("itemList", itemList);
		
		model.addAttribute("tradeId",tradeId);
		
		return "tradingItem/tradingItem-insert.html";
		
	}

	@RequestMapping("/insert/confirm")
	public String insertConfirm(@Validated TradingItemInsertForm form, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return insert(model,form.getTradeId());
		}
		
		User signInUser = (User) session.getAttribute("user");
		Integer comIdOfUser = signInUser.getDepartment().getCompanyId();
		
		TradingItem insertingTradingItem = new TradingItem();
		BeanUtils.copyProperties(form, insertingTradingItem);
		insertingTradingItem.setItem(itemService.findById(form.getItemId(), comIdOfUser));
		insertingTradingItem.setQuantity(form.getIntQuantity());

		System.out.println("insertingTradingItem:" + insertingTradingItem);

		model.addAttribute("insertingTradingItem", insertingTradingItem);
		session.setAttribute("insertingTradingItem", insertingTradingItem);
		return "tradingItem/tradingItem-insert-confirm.html";
	}

	@RequestMapping("/insert/finish")
	public String insertFinish(TradingItemInsertForm form, Model model) {
		User signInUser = (User) session.getAttribute("user");
		Integer comIdOfUser = signInUser.getDepartment().getCompanyId();
		
		System.out.println("form:"+form);
		TradingItem tradingItem = new TradingItem();
		BeanUtils.copyProperties(form, tradingItem);
		tradingItem.setQuantity(form.getIntQuantity());
		tradingItem.setOwner((tradeService.findById(form.getTradeId(), comIdOfUser)).getOwner());
		tradingItem.setOwnerId(tradingItem.getOwner().getId());
		
		System.out.println("tradingItem:" + tradingItem);
		tradingItemService.insertTradingItem(tradingItem);

		if (session.getAttribute("insertingTradingItem") != null) {
			session.removeAttribute("insertingTradingItem");
		}
		
		return "redirect:/trade/showDetail?id=" + form.getTradeId();
		
	}

}
