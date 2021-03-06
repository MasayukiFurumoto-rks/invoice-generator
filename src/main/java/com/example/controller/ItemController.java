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
import com.example.entity.User;
import com.example.form.ItemEditForm;
import com.example.form.ItemInsertForm;
import com.example.service.ItemService;
import com.example.service.UserService;

@Controller
@RequestMapping("/item")
public class ItemController {

	@Autowired
	private HttpSession session;

	@Autowired
	private UserService userService;

	@Autowired
	private ItemService itemService;

	@ModelAttribute
	private ItemEditForm setUpItemEditForm() {
		ItemEditForm itemEditForm = new ItemEditForm();
		if (session.getAttribute("editingItem") != null) {
			Item sessionItem = (Item) session.getAttribute("editingItem");
			BeanUtils.copyProperties(sessionItem, itemEditForm);
			itemEditForm.setPrice(String.valueOf(sessionItem.getPrice()));
			session.removeAttribute("editingItem");
		}
		return itemEditForm;
	}
	
	@ModelAttribute
	private ItemInsertForm setUpItemInsertForm() {
		ItemInsertForm itemInsertForm = new ItemInsertForm();
		if (session.getAttribute("insertingItem") != null) {
			Item sessionItem = (Item) session.getAttribute("insertingItem");
			BeanUtils.copyProperties(sessionItem, itemInsertForm);
			itemInsertForm.setPrice(String.valueOf(sessionItem.getPrice()));
			session.removeAttribute("insertingItem");
		}
		return itemInsertForm;
	}
	

	@RequestMapping("/showList")
	public String showList(Model model) {
		User signInUser = (User) session.getAttribute("user");
		Integer comIdOfUser = signInUser.getDepartment().getCompanyId();

		List<Item> itemList = itemService.findByCompanyId(comIdOfUser);
		model.addAttribute("itemList", itemList);
		return "item/item-list.html";
	}

	@RequestMapping("/showDetail")
	public String showDetail(Model model, Integer id) {
		User signInUser = (User) session.getAttribute("user");
		Integer comIdOfUser = signInUser.getDepartment().getCompanyId();

		Item item = itemService.findById(id, comIdOfUser);
		model.addAttribute("item", item);
		return "item/item-detail.html";
	}

	@RequestMapping("/edit")
	public String edit(Model model, Integer id) {
		User signInUser = (User) session.getAttribute("user");
		Integer comIdOfUser = signInUser.getDepartment().getCompanyId();

		// ???????????????????????????????????????????????????????????????????????????????????????
		Item item = itemService.findById(id, comIdOfUser);
		model.addAttribute("item", item);

		// ????????????????????????????????????
		List<User> userList = userService.findByCompanyId(comIdOfUser);
		model.addAttribute("userList", userList);

		return "item/item-edit.html";
	}

	@RequestMapping("/edit/confirm")
	public String editConfirm(@Validated ItemEditForm form, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return edit(model, form.getId());
		}
		User signInUser = (User) session.getAttribute("user");
		Integer comIdOfUser = signInUser.getDepartment().getCompanyId();

		Item item = itemService.findById(form.getId(), comIdOfUser);

		Item editedItem = itemService.findById(form.getId(), comIdOfUser);
		BeanUtils.copyProperties(form, editedItem);
		editedItem.setCreatedBy(userService.findById(form.getOwnerId()));

		editedItem.setPrice(form.getIntPrice());

		System.out.println("editedItem:" + editedItem);

		model.addAttribute("item", item);
		model.addAttribute("editedItem", editedItem);
		session.setAttribute("editingItem", editedItem);
		return "item/item-edit-confirm.html";
	}

	@RequestMapping("/edit/finish")
	public String editFinish(ItemEditForm form, Model model) {
		Item item = new Item();
		BeanUtils.copyProperties(form, item);
		item.setPrice(form.getIntPrice());
		System.out.println("item:" + item);
		itemService.updateItem(item);
		if (session.getAttribute("editingItem") != null) {
			session.removeAttribute("editingItem");
		}
		return "redirect:/item/showDetail?id=" + form.getId();
	}

	@RequestMapping("/delete/confirm")
	public String deleteConfirm(Integer id, Model model) {
		User signInUser = (User) session.getAttribute("user");
		Integer comIdOfUser = signInUser.getDepartment().getCompanyId();

		Item item = itemService.findById(id, comIdOfUser);
		model.addAttribute("item", item);
		return "item/item-delete-confirm.html";

	}

	@RequestMapping("/delete/finish")
	public String deleteFinish(Integer id, Model model) {
		User signInUser = (User) session.getAttribute("user");
		Integer comIdOfUser = signInUser.getDepartment().getCompanyId();

		Item item = itemService.findById(id, comIdOfUser);
		item.setDeleted(true);
		itemService.updateItem(item);
		return "redirect:/item/showList";

	}

	@RequestMapping("/insert")
	public String insert(Model model) {
		User signInUser = (User) session.getAttribute("user");
		Integer comIdOfUser = signInUser.getDepartment().getCompanyId();

		// ?????????????????????????????????????????????????????????????????????
		List<User> userList = userService.findByCompanyId(comIdOfUser);
		model.addAttribute("userList", userList);

		return "item/item-insert.html";

	}

	@RequestMapping("/insert/confirm")
	public String insertConfirm(@Validated ItemInsertForm form, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return insert(model);
		}
		
		Item insertingItem = new Item();
		BeanUtils.copyProperties(form, insertingItem);
		insertingItem.setCreatedBy(userService.findById(form.getOwnerId()));
		insertingItem.setPrice(form.getIntPrice());

		System.out.println("insertingItem:" + insertingItem);

		model.addAttribute("insertingItem", insertingItem);
		session.setAttribute("insertingItem", insertingItem);
		return "item/item-insert-confirm.html";
	}

	@RequestMapping("/insert/finish")
	public String insertFinish(ItemInsertForm form, Model model) {
		System.out.println("form:"+form);
		Item item = new Item();
		BeanUtils.copyProperties(form, item);
		item.setPrice(form.getIntPrice());
		System.out.println("item:" + item);
		itemService.insertItem(item);

		if (session.getAttribute("insertingItem") != null) {
			session.removeAttribute("insertingItem");
		}
		
		return "redirect:/item/showDetail?id=" + item.getId();
		
	}

}
