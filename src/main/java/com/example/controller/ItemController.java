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
		if(session.getAttribute("editingItem") != null) {
			Item sessionItem = (Item)session.getAttribute("editingItem");
			BeanUtils.copyProperties(sessionItem, itemEditForm);
			itemEditForm.setPrice(String.valueOf(sessionItem.getPrice()));
			session.removeAttribute("editingItem");
		}
		return itemEditForm; 
	}
	
	@RequestMapping("/showList")
	public String showList(Model model) {
		User signInUser = (User)session.getAttribute("user");
		Integer comIdOfUser = signInUser.getDepartment().getCompanyId();

		List<Item> itemList = itemService.findByCompanyId(comIdOfUser);
		model.addAttribute("itemList", itemList);
		return "item/item-list.html";
	}
	
	@RequestMapping("/showDetail")
	public String showDetail(Model model,Integer id) {
		User signInUser = (User)session.getAttribute("user");
		Integer comIdOfUser = signInUser.getDepartment().getCompanyId();

		Item item = itemService.findById(id,comIdOfUser);
		model.addAttribute("item", item);
		return "item/item-detail.html";
	}

	@RequestMapping("/edit")
	public String edit(Model model,Integer id) {
		User signInUser = (User)session.getAttribute("user");
		Integer comIdOfUser = signInUser.getDepartment().getCompanyId();

		// 編集しようとしている商品の情報を一緒に表示するためにロード
		Item item = itemService.findById(id,comIdOfUser);
		model.addAttribute("item", item);
		
		//　同じ企業の人を選ぶために
		Integer companyId = ((User)session.getAttribute("user")).getDepartment().getCompanyId();
		List<User> userList = userService.findByCompanyId(companyId);
		model.addAttribute("userList", userList);
		
		return "item/item-edit.html";
	}
	
	@RequestMapping("/editConfirm")
	public String editConfirm(@Validated ItemEditForm form,BindingResult result,Model model) {
		if(result.hasErrors()) {
			return edit(model,form.getId());
		}
		User signInUser = (User)session.getAttribute("user");
		Integer comIdOfUser = signInUser.getDepartment().getCompanyId();

		
		Item item = itemService.findById(form.getId(),comIdOfUser);
		
		Item editedItem = itemService.findById(form.getId(),comIdOfUser);
		BeanUtils.copyProperties(form, editedItem);
		editedItem.setCreatedBy(userService.findById(form.getOwnerId()));
		
		editedItem.setPrice(form.getIntPrice());

		System.out.println("editedItem:"+editedItem);

		model.addAttribute("item", item);
		model.addAttribute("editedItem", editedItem);
		session.setAttribute("editingItem", editedItem);
		return "item/item-edit-confirm.html";
	}
	
	@RequestMapping("/editFinish")
	public String editFinish(ItemEditForm form,Model model) {
		Item item = new Item();
		BeanUtils.copyProperties(form, item);
		item.setPrice(form.getIntPrice());
		System.out.println("item:"+item);
		itemService.updateItem(item);
		return "redirect:/item/showDetail?id=" +form.getId();
	}
	
	@RequestMapping("/delete/confirm")
	public String deleteConfirm(Integer id,Model model){
		User signInUser = (User)session.getAttribute("user");
		Integer comIdOfUser = signInUser.getDepartment().getCompanyId();

		Item item = itemService.findById(id,comIdOfUser);
		model.addAttribute("item", item);
		return "item/item-delete-confirm.html";
		
	}
	@RequestMapping("/delete/finish")
	public String deleteFinish(Integer id,Model model){
		User signInUser = (User)session.getAttribute("user");
		Integer comIdOfUser = signInUser.getDepartment().getCompanyId();

		Item item = itemService.findById(id,comIdOfUser);
		item.setDeleted(true);
		itemService.updateItem(item);
		return "redirect:/item/showList";
		
	}
}
