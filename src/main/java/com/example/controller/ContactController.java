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
import com.example.entity.Contact;
import com.example.entity.User;
import com.example.form.ContactEditForm;
import com.example.form.ContactInsertForm;
import com.example.service.ClientService;
import com.example.service.ContactService;
import com.example.service.UserService;

@Controller
@RequestMapping("/contact")
public class ContactController {

	@Autowired
	private HttpSession session;

	@Autowired
	private UserService userService;

	@Autowired
	private ClientService clientService;
	
	@Autowired
	private ContactService contactService;
	
	

	@ModelAttribute
	private ContactEditForm setUpContactEditForm() {
		ContactEditForm contactEditForm = new ContactEditForm();
		if (session.getAttribute("editingContact") != null) {
			Contact sessionContact = (Contact) session.getAttribute("editingContact");
			BeanUtils.copyProperties(sessionContact, contactEditForm);
			session.removeAttribute("editingContact");
		}
		return contactEditForm;
	}
	
	@ModelAttribute
	private ContactInsertForm setUpContactInsertForm() {
		ContactInsertForm contactInsertForm = new ContactInsertForm();
		if (session.getAttribute("insertingContact") != null) {
			Contact sessionContact = (Contact) session.getAttribute("insertingContact");
			BeanUtils.copyProperties(sessionContact, contactInsertForm);
			session.removeAttribute("insertingContact");
		}
		return contactInsertForm;
	}
	

	@RequestMapping("/showList")
	public String showList(Model model) {
		User signInUser = (User) session.getAttribute("user");
		Integer comIdOfUser = signInUser.getDepartment().getCompanyId();

		List<Contact> contactList = contactService.findByCompanyId(comIdOfUser);
		model.addAttribute("contactList", contactList);
		return "contact/contact-list.html";
	}

	@RequestMapping("/showDetail")
	public String showDetail(Model model, Integer id) {
		User signInUser = (User) session.getAttribute("user");
		Integer comIdOfUser = signInUser.getDepartment().getCompanyId();

		Contact contact = contactService.findById(id, comIdOfUser);
	
		model.addAttribute("contact", contact);
		return "contact/contact-detail.html";
	}

	@RequestMapping("/edit")
	public String edit(Model model, Integer id) {
		User signInUser = (User) session.getAttribute("user");
		Integer comIdOfUser = signInUser.getDepartment().getCompanyId();

		// 編集しようとしている商品の情報を一緒に表示するためにロード
		Contact contact = contactService.findById(id, comIdOfUser);
		model.addAttribute("contact", contact);

		// 同じ企業の人を選ぶために
		List<User> userList = userService.findByCompanyId(comIdOfUser);
		model.addAttribute("userList", userList);

		return "contact/contact-edit.html";
	}

	@RequestMapping("/edit/confirm")
	public String editConfirm(@Validated ContactEditForm form, BindingResult result, Model model) {
		if (result.hasErrors()) {
			System.out.println("編集中にエラー："+ form );
			return edit(model, form.getId());
		}
		
		User signInUser = (User) session.getAttribute("user");
		Integer comIdOfUser = signInUser.getDepartment().getCompanyId();

		Contact contact = contactService.findById(form.getId(), comIdOfUser);
		
		Contact editedContact = contactService.findById(form.getId(), comIdOfUser);
		System.out.println(form);
		BeanUtils.copyProperties(form, editedContact);
		editedContact.setOwner(userService.findById(form.getOwnerId()));
		

		System.out.println("editedContact:" + editedContact);

		model.addAttribute("contact", contact);
		model.addAttribute("editedContact", editedContact);
		session.setAttribute("editingContact", editedContact);
		return "contact/contact-edit-confirm.html";
	}

	@RequestMapping("/edit/finish")
	public String editFinish(ContactEditForm form, Model model) {
		Contact contact = new Contact();
		BeanUtils.copyProperties(form, contact);
		System.out.println("contact:" + contact);
		contactService.updateContact(contact);
		if (session.getAttribute("editingContact") != null) {
			session.removeAttribute("editingContact");
		}
		return "redirect:/contact/showDetail?id=" + form.getId();
	}

	@RequestMapping("/delete/confirm")
	public String deleteConfirm(Integer id, Model model) {
		User signInUser = (User) session.getAttribute("user");
		Integer comIdOfUser = signInUser.getDepartment().getCompanyId();

		Contact contact = contactService.findById(id, comIdOfUser);
		model.addAttribute("contact", contact);
		return "contact/contact-delete-confirm.html";

	}

	@RequestMapping("/delete/finish")
	public String deleteFinish(Integer id, Model model) {
		User signInUser = (User) session.getAttribute("user");
		Integer comIdOfUser = signInUser.getDepartment().getCompanyId();

		Contact contact = contactService.findById(id, comIdOfUser);
		contact.setDeleted(true);
		contactService.updateContact(contact);
		return "redirect:/contact/showList";

	}

	@RequestMapping("/insert")
	public String insert(Model model) {
		User signInUser = (User) session.getAttribute("user");
		Integer comIdOfUser = signInUser.getDepartment().getCompanyId();

		// 企業担当者の企業を決めるときに、その企業の取引先から人を選ぶために
		List<Client> clientList = clientService.findByCompanyId(comIdOfUser);
		model.addAttribute("clientList", clientList);
		
		// 所有者を決めるときに、同じ企業の人を選ぶために
		List<User> userList = userService.findByCompanyId(comIdOfUser);
		model.addAttribute("userList", userList);

		return "contact/contact-insert.html";

	}

	@RequestMapping("/insert/confirm")
	public String insertConfirm(@Validated ContactInsertForm form, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return insert(model);
		}
		
		User signInUser = (User) session.getAttribute("user");
		Integer comIdOfUser = signInUser.getDepartment().getCompanyId();
		
		Contact insertingContact = new Contact();
		BeanUtils.copyProperties(form, insertingContact);
		insertingContact.setOwner(userService.findById(form.getOwnerId()));
		insertingContact.setBelongs(clientService.findById(form.getClientId(),comIdOfUser));
		
		System.out.println("insertingContact:" + insertingContact);

		model.addAttribute("insertingContact", insertingContact);
		session.setAttribute("insertingContact", insertingContact);
		return "contact/contact-insert-confirm.html";
	}

	@RequestMapping("/insert/finish")
	public String insertFinish(ContactInsertForm form, Model model) {
		System.out.println("form:"+form);
		Contact contact = new Contact();
		BeanUtils.copyProperties(form, contact);
		System.out.println("contact:" + contact);
		contactService.insertContact(contact);

		if (session.getAttribute("insertingContact") != null) {
			session.removeAttribute("insertingContact");
		}
		
		return "redirect:/contact/showDetail?id=" + contact.getId();
		
	}

}
