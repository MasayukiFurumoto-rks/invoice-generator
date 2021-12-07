package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.form.SignUpForm;

@Controller
@RequestMapping("")
public class UserEmployeeController {

	@ModelAttribute
	public SignUpForm setUpSignUpForm() {
		return new 	SignUpForm();
	}
	
	@RequestMapping("/to-sign-up")
	public String toSignUp() {
		return "sign-up.html";
	}

	@RequestMapping("/sign-up-confirm")
	public String signUpConfirm(SignUpForm form,Model model) {
		return "sign-up-confirm.html";
		
	}
	
	@RequestMapping("/sign-up")
	public String signUp(SignUpForm form,Model model) {
		return "redirect:/to-sign-in";
	}

	@RequestMapping("/to-sign-in")
	public String toSignIn() {
		return "sign-in.html";

	}

	@RequestMapping("/sign-in")
	public String signIn() {
		return "redirect:/home";
	}
}