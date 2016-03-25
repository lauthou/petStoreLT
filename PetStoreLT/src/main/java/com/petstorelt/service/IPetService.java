package com.petstorelt.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.petstorelt.model.Pet;

public interface IPetService {

	public List<Pet> findPetById(String petId);	

//	@Secured("authenticated")
	@Transactional
	public void addPet(Pet pet);
	
	@Transactional
	public void deletePet(String petId);
	
	public List<Pet> getAll();
}
