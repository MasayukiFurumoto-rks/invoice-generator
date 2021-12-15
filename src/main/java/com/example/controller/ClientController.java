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

import com.example.entity.Client;
import com.example.entity.User;
import com.example.form.ClientEditForm;
import com.example.form.ClientInsertForm;
import com.example.service.ClientService;
import com.example.service.UserService;

@Controller
@RequestMapping("/client")
public class ClientController {

	@Autowired
	private HttpSession session;

	@Autowired
	private UserService userService;

	@Autowired
	private ClientService clientService;

	@ModelAttribute
	private ClientEditForm setUpClientEditForm() {
		ClientEditForm clientEditForm = new ClientEditForm();
		if (session.getAttribute("editingClient") != null) {
			Client sessionClient = (Client) session.getAttribute("editingClient");
			BeanUtils.copyProperties(sessionClient, clientEditForm);
			clientEditForm.setCreditLimit(String.valueOf(sessionClient.getCreditLimit()));
			session.removeAttribute("editingClient");
		}
		return clientEditForm;
	}
	
	@ModelAttribute
	private ClientInsertForm setUpClientInsertForm() {
		ClientInsertForm clientInsertForm = new ClientInsertForm();
		if (session.getAttribute("insertingClient") != null) {
			Client sessionClient = (Client) session.getAttribute("insertingClient");
			BeanUtils.copyProperties(sessionClient, clientInsertForm);
			clientInsertForm.setCreditLimit(String.valueOf(sessionClient.getCreditLimit()));
			session.removeAttribute("insertingClient");
		}
		return clientInsertForm;
	}
	

	@RequestMapping("/showList")
	public String showList(Model model) {
		User signInUser = (User) session.getAttribute("user");
		Integer comIdOfUser = signInUser.getDepartment().getCompanyId();

		List<Client> clientList = clientService.findByCompanyId(comIdOfUser);
		model.addAttribute("clientList", clientList);
		return "client/client-list.html";
	}

	@RequestMapping("/showDetail")
	public String showDetail(Model model, Integer id) {
		User signInUser = (User) session.getAttribute("user");
		Integer comIdOfUser = signInUser.getDepartment().getCompanyId();

		Client client = clientService.findById(id, comIdOfUser);
		if(client.getTradeList() == null) {
			model.addAttribute("untradeMessage", "この企業との取引はまだありません");
		}else {
			if(client.getTradeList().size() ==0) {
			model.addAttribute("untradeMessage", "この企業との取引はまだありません");
			}
		}
	
		model.addAttribute("client", client);
		return "client/client-detail.html";
	}

	@RequestMapping("/edit")
	public String edit(Model model, Integer id) {
		User signInUser = (User) session.getAttribute("user");
		Integer comIdOfUser = signInUser.getDepartment().getCompanyId();

		// 編集しようとしている商品の情報を一緒に表示するためにロード
		Client client = clientService.findById(id, comIdOfUser);
		model.addAttribute("client", client);

		// 同じ企業の人を選ぶために
		List<User> userList = userService.findByCompanyId(comIdOfUser);
		model.addAttribute("userList", userList);

		return "client/client-edit.html";
	}

	@RequestMapping("/edit/confirm")
	public String editConfirm(@Validated ClientEditForm form, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return edit(model, form.getId());
		}
		User signInUser = (User) session.getAttribute("user");
		Integer comIdOfUser = signInUser.getDepartment().getCompanyId();

		Client client = clientService.findById(form.getId(), comIdOfUser);
		
		Client editedClient = clientService.findById(form.getId(), comIdOfUser);
		BeanUtils.copyProperties(form, editedClient);
		editedClient.setCreditLimit(form.getIntCreditLimit());
		editedClient.setOwner(userService.findById(form.getOwnerId()));
		

		System.out.println("editedClient:" + editedClient);

		model.addAttribute("client", client);
		model.addAttribute("editedClient", editedClient);
		session.setAttribute("editingClient", editedClient);
		return "client/client-edit-confirm.html";
	}

	@RequestMapping("/edit/finish")
	public String editFinish(ClientEditForm form, Model model) {
		Client client = new Client();
		BeanUtils.copyProperties(form, client);
		client.setCreditLimit(form.getIntCreditLimit());
		System.out.println("client:" + client);
		clientService.updateClient(client);
		if (session.getAttribute("editingClient") != null) {
			session.removeAttribute("editingClient");
		}
		return "redirect:/client/showDetail?id=" + form.getId();
	}

	@RequestMapping("/delete/confirm")
	public String deleteConfirm(Integer id, Model model) {
		User signInUser = (User) session.getAttribute("user");
		Integer comIdOfUser = signInUser.getDepartment().getCompanyId();

		Client client = clientService.findById(id, comIdOfUser);
		model.addAttribute("client", client);
		return "client/client-delete-confirm.html";

	}

	@RequestMapping("/delete/finish")
	public String deleteFinish(Integer id, Model model) {
		User signInUser = (User) session.getAttribute("user");
		Integer comIdOfUser = signInUser.getDepartment().getCompanyId();

		Client client = clientService.findById(id, comIdOfUser);
		client.setDeleted(true);
		clientService.updateClient(client);
		return "redirect:/client/showList";

	}

	@RequestMapping("/insert")
	public String insert(Model model) {
		User signInUser = (User) session.getAttribute("user");
		Integer comIdOfUser = signInUser.getDepartment().getCompanyId();

		// 所有者を決めるときに、同じ企業の人を選ぶために
		List<User> userList = userService.findByCompanyId(comIdOfUser);
		model.addAttribute("userList", userList);

		return "client/client-insert.html";

	}

	@RequestMapping("/insert/confirm")
	public String insertConfirm(@Validated ClientInsertForm form, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return insert(model);
		}
		
		Client insertingClient = new Client();
		BeanUtils.copyProperties(form, insertingClient);
		insertingClient.setOwner(userService.findById(form.getOwnerId()));
		insertingClient.setCreditLimit(form.getIntCreditLimit());

		System.out.println("insertingClient:" + insertingClient);

		model.addAttribute("insertingClient", insertingClient);
		session.setAttribute("insertingClient", insertingClient);
		return "client/client-insert-confirm.html";
	}

	@RequestMapping("/insert/finish")
	public String insertFinish(ClientInsertForm form, Model model) {
		System.out.println("form:"+form);
		Client client = new Client();
		BeanUtils.copyProperties(form, client);
		client.setCreditLimit(form.getIntCreditLimit());
		System.out.println("client:" + client);
		clientService.insertClient(client);

		if (session.getAttribute("insertingClient") != null) {
			session.removeAttribute("insertingClient");
		}
		
		return "redirect:/client/showDetail?id=" + client.getId();
		
	}

}
