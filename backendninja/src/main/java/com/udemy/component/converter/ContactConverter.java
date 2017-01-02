package com.udemy.component.converter;

import org.springframework.stereotype.Component;

import com.udemy.entity.ContactEntity;
import com.udemy.model.ContactModel;

@Component("contactConverter")
public class ContactConverter {
	
	//Transforma de uma Entity para um Model
	public ContactModel entityToModel(ContactEntity contactEntity) {
		return new ContactModel(contactEntity.getId(), 
								contactEntity.getFirstName(), 
								contactEntity.getLastName(), 
								contactEntity.getTelephone(),
								contactEntity.getCity());
	}
	
	//Transforma de um Model para uma Entity
	public ContactEntity modelToEntity(ContactModel contactModel) {
		return new ContactEntity(contactModel.getId(),
								 contactModel.getFirstName(),
								 contactModel.getLastName(),
								 contactModel.getTelephone(),
								 contactModel.getCity());
	}

}
