package com.example.controller;

import java.util.List;
import java.util.Objects;

import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.entity.User;
import com.example.entity.UserDepartment;
import com.example.form.SignInForm;
import com.example.form.SignUpForm;
import com.example.service.UserDepartmentService;
import com.example.service.UserService;

@Controller
@RequestMapping("")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private UserDepartmentService userDepartmentService;
	
	@Autowired
	private HttpSession session;

	@ModelAttribute
	public SignUpForm setUpSignUpForm() {
		return new SignUpForm();
	}

	@RequestMapping("/to-sign-up")
	public String toSignUp(Model model) {
		// 本当は登録ページごとに企業ごとのリストをロードするようにしたい難しいのでまだやらない
		
		List<UserDepartment> departmentList = userDepartmentService.findByCompanyId(1);
		model.addAttribute("departmentList", departmentList);
		return "user/sign-up.html";
	}

	/**
	 * 
	 * @param form
	 * @param model
	 * @return
	 */
	@RequestMapping("/sign-up-confirm")
	public String signUpConfirm(SignUpForm form, Model model) {

		return "user/sign-up-confirm.html";

	}

	/**
	 * 
	 * @param form
	 * @param model
	 * @return
	 */
	@RequestMapping("/sign-up")
	public String signUp(@Validated SignUpForm form,BindingResult result, Model model) {
		System.out.println(form);
		
		if(result.hasErrors()) {
			return toSignUp(model);
		}
		
		System.out.println("Emailsearchresultsize:"+userService.findByEmail(form.getEmail()).size());
		if(userService.findByEmail(form.getEmail()).size() != 0) {
			model.addAttribute("dupricateMail", "このメールアドレスはすでに使用されています。");
			return toSignUp(model);
		}
		
		User user = new User();
		BeanUtils.copyProperties(form, user);
		user.setName(form.getLastName() + " " +form.getFirstName());
		
		userService.insert(user);
		
		return "redirect:/to-sign-in";
	}

	@RequestMapping("/to-sign-in")
	public String toSignIn() {
		return "user/sign-in.html";

	}

	@RequestMapping("/sign-in")
	public String signIn(@Validated SignInForm form,BindingResult result, Model model) {
		
		if(result.hasErrors()) {
			return toSignIn();
		}
		
		User signInResult = userService.signIn(form.getEmail(), form.getPassword());
		
		if(Objects.isNull(signInResult) ) {
			model.addAttribute("signInError","メールアドレスかパスワードが不正です");
			return "user/sign-in.html";
		}
			
		System.out.println("user:"+signInResult);
		session.setAttribute("user", signInResult);
		return "redirect:/home";
	}
	
	@RequestMapping("/sign-out")
	public String signOut() {
		session.invalidate();
		return "redirect:/to-sign-in";
	}
	
}