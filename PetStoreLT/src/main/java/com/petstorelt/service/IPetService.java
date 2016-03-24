package com.petstorelt.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.petstorelt.model.Pet;
import com.petstorelt.persisitence.dao.PetDao;

public interface IPetService {

	public List<PetDao> findPetById(String petId);	

//	@Secured("authenticated")
	@Transactional
	public void addPet(Pet pet);
	
	@Transactional
	public void removePet(String petId);
	
	public List<PetDao> getAll();
}
