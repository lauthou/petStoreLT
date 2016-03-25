package com.petstorelt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.petstorelt.model.Pet;
import com.petstorelt.persistence.dao.PetStoreDao;

@Service
public class PetService implements IPetService {
	
	@Autowired
	private PetStoreDao petStoreDao; 
	
	public List<Pet> findPetById(String petId) {
        return petStoreDao.findPetById(petId);
    }

	public void addPet(Pet pet) {
		petStoreDao.addPet(pet);		
	}

	public void deletePet(String petId) {
		petStoreDao.deletePet(petId);
	}
	
	public List<Pet> getAll() {
        return petStoreDao.getAll();
    }
}
