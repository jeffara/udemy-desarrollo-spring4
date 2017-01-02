package com.udemy.service;

import java.util.List;

import com.udemy.model.ContactModel;

public interface ContactService {
	
	public ContactModel addContact(ContactModel contactModel);
	public List<ContactModel> getAllContacts();
	public ContactModel findContactById(int id);
	public void removeContact(int id);
}
