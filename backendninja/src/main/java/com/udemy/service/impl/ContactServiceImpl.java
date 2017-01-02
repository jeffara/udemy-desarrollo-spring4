package com.udemy.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.udemy.component.converter.ContactConverter;
import com.udemy.entity.ContactEntity;
import com.udemy.model.ContactModel;
import com.udemy.repository.ContactRepository;
import com.udemy.service.ContactService;

@Service("contactService")
public class ContactServiceImpl implements ContactService {

	@Autowired
	@Qualifier("contactRepository")
	private ContactRepository contactRepository;
	
	@Autowired
	@Qualifier("contactConverter")
	private ContactConverter contactConverter;
	
	@Override
	public ContactModel addContact(ContactModel contactModel) {
		ContactEntity contactEntity = contactRepository.save(contactConverter.modelToEntity(contactModel));
		
		return contactConverter.entityToModel(contactEntity);
	}
	
	@Override
	public List<ContactModel> getAllContacts() {
		return contactRepository.findAll().stream()
			     						  .map(ce -> contactConverter.entityToModel(ce))
			     						  .collect(Collectors.toList());
	}
	
	@Override
	public ContactModel findContactById(int id) {
		return contactConverter.entityToModel(contactRepository.findById(id));
	}
	
	@Override
	public void removeContact(int id) {
		ContactEntity contactEntity = contactRepository.findById(id);
		
		if(contactEntity != null) {
			contactRepository.delete(contactEntity);
		}
	}
}
