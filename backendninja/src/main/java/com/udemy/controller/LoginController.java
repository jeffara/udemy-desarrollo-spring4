package com.udemy.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.udemy.constants.ViewConstant;

@Controller
public class LoginController {

	private static final Log LOGGER = LogFactory.getLog(LoginController.class);

	@GetMapping("/login")
	public String showLoginForm(Model model, @RequestParam(name = "error", required = false) String error,
			@RequestParam(name = "logout", required = false) String logout) {

		LOGGER.info("METHOD: showLoginForm() -- PARAMS: error = " + error + ", logout = " + logout);

		model.addAttribute("error", error);
		model.addAttribute("logout", logout);

		LOGGER.info("Returning to Login View");

		return ViewConstant.LOGIN_VIEW;
	}

	//Permite que com a inclusao das chaves, esse metodo possa atender mais de um path
	@GetMapping({"/loginsuccess", "/"})
	public String loginCheck() {

		LOGGER.info("METHOD: loginCheck()");
		LOGGER.info("Returning to Contacts View");

		return "redirect:/contacts/listcontacts";
	}
}
