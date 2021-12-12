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
import com.example.form.ItemEditForm;
import com.example.service.ItemService;

@Controller
@RequestMapping("/item")
public class ItemController {

	@Autowired
	private HttpSession session;
	
	
	@Autowired
	private ItemService itemService;

	@ModelAttribute
	private ItemEditForm setUpItemEditForm() {
		ItemEditForm itemEditForm = new ItemEditForm();
		if(session.getAttribute("editingItem") != null) {
			Item sessionItem = (Item)session.getAttribute("editingItem");
			BeanUtils.copyProperties(sessionItem, itemEditForm);
			session.removeAttribute("editingItem");
		}
		return itemEditForm; 
	}
	
	@RequestMapping("/showList")
	public String showList(Model model) {
		List<Item> itemList = itemService.findAll();
		model.addAttribute("itemList", itemList);
		return "item/item-list.html";
	}
	
	@RequestMapping("/showDetail")
	public String showDetail(Model model,Integer id) {
		Item item = itemService.findById(id);
		model.addAttribute("item", item);
		return "item/item-detail.html";
	}

	@RequestMapping("/edit")
	public String edit(Model model,Integer id) {
		Item item = itemService.findById(id);
		model.addAttribute("item", item);
		return "item/item-edit.html";
	}
	
	@RequestMapping("/editConfirm")
	public String editConfirm(Model model,@Validated ItemEditForm form,BindingResult result) {
		Item editedItem = new Item();
		BeanUtils.copyProperties(form, editedItem);
		editedItem.setPrice(form.getIntPrice());
		model.addAttribute("editedItem", editedItem);
		return "item/item-edit-confirm.html";
	}
}
