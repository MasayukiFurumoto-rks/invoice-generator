package com.example.controller;

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
import com.example.form.SignInForm;
import com.example.form.SignUpForm;
import com.example.service.UserService;

@Controller
@RequestMapping("")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private HttpSession session;

	@ModelAttribute
	public SignUpForm setUpSignUpForm() {
		return new SignUpForm();
	}

	@RequestMapping("/to-sign-up")
	public String toSignUp() {
		return "sign-up.html";
	}

	/**
	 * 
	 * @param form
	 * @param model
	 * @return
	 */
	@RequestMapping("/sign-up-confirm")
	public String signUpConfirm(SignUpForm form, Model model) {

		return "sign-up-confirm.html";

	}

	/**
	 * 
	 * @param form
	 * @param model
	 * @return
	 */
	@RequestMapping("/sign-up")
	public String signUp(@Validated SignUpForm form,BindingResult result, Model model) {
		
		if(result.hasErrors()) {
			return toSignUp();
		}
		
		System.out.println("Emailsearchresultsize:"+userService.findByEmail(form.getEmail()).size());
		if(userService.findByEmail(form.getEmail()).size() != 0) {
			model.addAttribute("dupricateMail", "このメールアドレスはすでに使用されています。");
			return toSignUp();
		}
		
		User user = new User();
		BeanUtils.copyProperties(form, user);
		user.setName(form.getLastName() + " " +form.getFirstName());
		
		// 本当は認証コードで付与したいけどまだ難しいのでやらない
		user.setUserCompaniesId(1);
		
		userService.insert(user);
		
		return "redirect:/to-sign-in";
	}

	@RequestMapping("/to-sign-in")
	public String toSignIn() {
		return "sign-in.html";

	}

	@RequestMapping("/sign-in")
	public String signIn(@Validated SignInForm form,BindingResult result, Model model) {
		
		if(result.hasErrors()) {
			return toSignIn();
		}
		
		User signInResult = userService.signIn(form.getEmail(), form.getPassword());
		
		if(Objects.isNull(signInResult) ) {
			model.addAttribute("signInError","メールアドレスかパスワードが不正です");
			return "sign-in.html";
		}
			
		session.setAttribute("user", signInResult);
		return "redirect:/home";
	}
	
	@RequestMapping("/sign-out")
	public String signOut() {
		session.invalidate();
		return "redirect:/to-sign-in";
	}
	
}