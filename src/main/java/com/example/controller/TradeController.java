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

import com.example.entity.Contact;
import com.example.entity.Trade;
import com.example.entity.TradeStatus;
import com.example.entity.User;
import com.example.form.TradeEditForm;
import com.example.form.TradeInsertForm;
import com.example.service.ContactService;
import com.example.service.TradeService;
import com.example.service.UserService;

@Controller
@RequestMapping("/trade")
public class TradeController {

	@Autowired
	private HttpSession session;

	@Autowired
	private UserService userService;

	@Autowired
	private ContactService contactService;

	@Autowired
	private TradeService tradeService;

	@ModelAttribute
	private TradeEditForm setUpTradeEditForm() {
		TradeEditForm tradeEditForm = new TradeEditForm();
		if (session.getAttribute("editingTrade") != null) {
			Trade sessionTrade = (Trade) session.getAttribute("editingTrade");
			BeanUtils.copyProperties(sessionTrade, tradeEditForm);
			tradeEditForm.setDate(String.valueOf(sessionTrade.getDate()));
			session.removeAttribute("editingTrade");
		}
		return tradeEditForm;
	}
	
	@ModelAttribute
	private TradeInsertForm setUpTradeInsertForm() {
		TradeInsertForm tradeInsertForm = new TradeInsertForm();
		if (session.getAttribute("insertingTrade") != null) {
			Trade sessionTrade = (Trade) session.getAttribute("insertingTrade");
			BeanUtils.copyProperties(sessionTrade, tradeInsertForm);
			tradeInsertForm.setDate(String.valueOf(sessionTrade.getDate()));
			session.removeAttribute("insertingTrade");
		}
		return tradeInsertForm;
	}
	

	@RequestMapping("/showList")
	public String showList(Model model) {
		User signInUser = (User) session.getAttribute("user");
		Integer comIdOfUser = signInUser.getDepartment().getCompanyId();

		List<Trade> tradeList = tradeService.findByCompanyId(comIdOfUser);
		model.addAttribute("tradeList", tradeList);
		return "trade/trade-list.html";
	}

	@RequestMapping("/showDetail")
	public String showDetail(Model model, Integer id) {
		User signInUser = (User) session.getAttribute("user");
		Integer comIdOfUser = signInUser.getDepartment().getCompanyId();

		Trade trade = tradeService.findById(id, comIdOfUser);
		System.out.println("trade:"+trade);
		
		model.addAttribute("trade", trade);
		return "trade/trade-detail.html";
	}

	@RequestMapping("/edit")
	public String edit(Model model, Integer id) {
		User signInUser = (User) session.getAttribute("user");
		Integer comIdOfUser = signInUser.getDepartment().getCompanyId();

		// ???????????????????????????????????????????????????????????????????????????????????????
		Trade trade = tradeService.findById(id, comIdOfUser);
		model.addAttribute("trade", trade);

		// ????????????????????????????????????
		List<User> userList = userService.findByCompanyId(comIdOfUser);
		model.addAttribute("userList", userList);
		
		// ????????????????????????????????????????????????????????????????????????
		List<Contact> contactList = contactService.findByCompanyId(comIdOfUser);
		model.addAttribute("contactList", contactList);

		// ?????????????????????????????????
		List<TradeStatus> statusList = tradeService.getTradeStatusList();
		model.addAttribute("statusList", statusList);

		return "trade/trade-edit.html";
	}

	@RequestMapping("/edit/confirm")
	public String editConfirm(@Validated TradeEditForm form, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return edit(model, form.getId());
		}
		User signInUser = (User) session.getAttribute("user");
		Integer comIdOfUser = signInUser.getDepartment().getCompanyId();

		Trade trade = tradeService.findById(form.getId(), comIdOfUser);
		
		Trade editedTrade = tradeService.findById(form.getId(), comIdOfUser);
		BeanUtils.copyProperties(form, editedTrade);
		editedTrade.setOwner(userService.findById(form.getOwnerId()));
		editedTrade.setContact(contactService.findById(form.getContactId(), comIdOfUser));
		editedTrade.setDate(form.getDateAsSqlDate());
		editedTrade.setStatusValue(tradeService.getTradeStatus(form.getStatusKey()).getValue());

		System.out.println("editedTrade:" + editedTrade);

		model.addAttribute("trade", trade);
		model.addAttribute("editedTrade", editedTrade);
		session.setAttribute("editingTrade", editedTrade);
		return "trade/trade-edit-confirm.html";
	}

	@RequestMapping("/edit/finish")
	public String editFinish(TradeEditForm form, Model model) {
		Trade trade = new Trade();
		BeanUtils.copyProperties(form, trade);
		trade.setDate(form.getDateAsSqlDate());

		tradeService.updateTrade(trade);
		if (session.getAttribute("editingTrade") != null) {
			session.removeAttribute("editingTrade");
		}
		return "redirect:/trade/showDetail?id=" + form.getId();
	}

	@RequestMapping("/delete/confirm")
	public String deleteConfirm(Integer id, Model model) {
		User signInUser = (User) session.getAttribute("user");
		Integer comIdOfUser = signInUser.getDepartment().getCompanyId();

		Trade trade = tradeService.findById(id, comIdOfUser);
		model.addAttribute("trade", trade);
		return "trade/trade-delete-confirm.html";

	}

	@RequestMapping("/delete/finish")
	public String deleteFinish(Integer id, Model model) {
		User signInUser = (User) session.getAttribute("user");
		Integer comIdOfUser = signInUser.getDepartment().getCompanyId();

		Trade trade = tradeService.findById(id, comIdOfUser);
		trade.setDeleted(true);
		tradeService.updateTrade(trade);
		return "redirect:/trade/showList";
	}

	@RequestMapping("/insert")
	public String insert(Model model) {
		User signInUser = (User) session.getAttribute("user");
		Integer comIdOfUser = signInUser.getDepartment().getCompanyId();

		// ?????????????????????????????????????????????????????????????????????
		List<User> userList = userService.findByCompanyId(comIdOfUser);
		model.addAttribute("userList", userList);

		// ????????????????????????????????????????????????????????????????????????
		List<Contact> contactList = contactService.findByCompanyId(comIdOfUser);
		model.addAttribute("contactList", contactList);

		// ?????????????????????????????????
		List<TradeStatus> statusList = tradeService.getTradeStatusList();
		model.addAttribute("statusList", statusList);

		
		return "trade/trade-insert.html";

	}

	@RequestMapping("/insert/confirm")
	public String insertConfirm(@Validated TradeInsertForm form, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return insert(model);
		}
		
		User signInUser = (User) session.getAttribute("user");
		Integer comIdOfUser = signInUser.getDepartment().getCompanyId();
		
		Trade insertingTrade = new Trade();
		BeanUtils.copyProperties(form, insertingTrade);
		insertingTrade.setOwner(userService.findById(form.getOwnerId()));
		insertingTrade.setContact(contactService.findById(form.getContactId(), comIdOfUser));
		insertingTrade.setDate(form.getDateAsSqlDate());
		insertingTrade.setStatusValue(tradeService.getTradeStatus(form.getStatusKey()).getValue());

		System.out.println("insertingTrade:" + insertingTrade);

		model.addAttribute("insertingTrade", insertingTrade);
		session.setAttribute("insertingTrade", insertingTrade);
		return "trade/trade-insert-confirm.html";
	}

	@RequestMapping("/insert/finish")
	public String insertFinish(TradeInsertForm form, Model model) {
		System.out.println("form:"+form);
		Trade trade = new Trade();
		BeanUtils.copyProperties(form, trade);
		trade.setDate(form.getDateAsSqlDate());
		System.out.println("trade:" + trade);
		tradeService.insertTrade(trade);

		if (session.getAttribute("insertingTrade") != null) {
			session.removeAttribute("insertingTrade");
		}
		
		return "redirect:/trade/showDetail?id=" + trade.getId();
		
	}

}
