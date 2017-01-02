package com.udemy.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.udemy.constants.ViewConstant;
import com.udemy.model.ContactModel;
import com.udemy.service.ContactService;

@Controller
@RequestMapping("/contacts")
public class ContactController {
	
	private static final Log LOGGER = LogFactory.getLog(ContactController.class);
	
	private int result = 9;
	
	@Autowired
	@Qualifier("contactService")
	private ContactService contactService;
	
	@GetMapping("/cancel")
	public String cancel() {
		return "redirect:/contacts/listcontacts";
	}
	
	@GetMapping("/removecontact")
	public ModelAndView removeContact(@RequestParam(name="id", required=true) int id) {
		contactService.removeContact(id);
		
		result = 3;
		
		return listContacts();
	}
	
	@PostMapping("/addcontact")
	public String addContact(@ModelAttribute(name="contactModel") ContactModel contactModel,
							 Model model) {
		LOGGER.info("METHOD: addContact() -- PARAMS: " + contactModel);

		if(contactService.addContact(contactModel) != null) {
			if(contactModel.getId() == 0) 
				result = 1;
			else 
				result = 2;
		} else {
			result = 0;
		}

		return "redirect:/contacts/listcontacts";
	}

	@GetMapping("/listcontacts")
	public ModelAndView listContacts() {
		ModelAndView mav = new ModelAndView(ViewConstant.CONTACTS_VIEW);
		
		//Obtem usuario autenticado via Spring Security
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		//Apresenta as ROLES associadas ao usuario em questao, cadastradas no banco de dados users
		//user.getAuthorities().forEach(a -> System.out.println("AUTHORITY -> " + a.getAuthority()));
		
		mav.addObject("username", user.getUsername());
		mav.addObject("contacts", contactService.getAllContacts());
		mav.addObject("result", result);
		
		result = 9;
		
		return mav;
	}
	
	//Permite que apenas os usuarios com as roles declaradas acessem esse recurso
	//Pode ser adicionado tambem a nivel de classe, pode ser aplicado em metodos e classes de servico tambem
	@PreAuthorize("hasRole('ROLE_USER')") 
	@GetMapping("/contactform")
	public String redirectContactForm(@RequestParam(name="id", defaultValue="0", required=true) int id, 
									  Model model) {
		ContactModel contactModel;
		
		if(id != 0) {
			contactModel = contactService.findContactById(id);
		} else {
			contactModel = new ContactModel();
		}
		
		//Disponibiliza na View um objeto do tipo ContactModel para insercao de um contato
		model.addAttribute("contactModel", contactModel);
		
		return ViewConstant.CONTACT_FORM_VIEW;
	}
}
