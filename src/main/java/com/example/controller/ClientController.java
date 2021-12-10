package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.entity.Client;
import com.example.service.ClientService;

@Controller
@RequestMapping("/client")
public class ClientController {

	@Autowired
	private ClientService clientService;
	
	@RequestMapping("/showList")
	public String showList(Model model) {
		List<Client> clientList = clientService.findAll();
		model.addAttribute("clientList", clientList);
		return "client/client-list.html";
	}
}
