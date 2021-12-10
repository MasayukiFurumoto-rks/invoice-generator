package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.entity.Item;
import com.example.service.ItemService;

@Controller
@RequestMapping("/item")
public class ItemController {

	@Autowired
	private ItemService itemService;

	@RequestMapping("/showList")
	public String showList(Model model) {
		List<Item> itemList = itemService.findAll();
		model.addAttribute("itemList", itemList);
		return "item-list.html";
	}
	
	@RequestMapping("/showDetail")
	public String showDetail(Model model,Integer id) {
		Item item = itemService.findById(id);
		model.addAttribute("item", item);
		return "item-detail.html";
	}
}
