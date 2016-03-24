package com.petstorelt.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.petstorelt.model.Pet;
import com.petstorelt.persisitence.dao.PetDao;
import com.petstorelt.service.IPetService;

/**
 * Controller managing pets
 */
@RestController
@RequestMapping("petstore")
public class PetStoreController {
	
	@Autowired
	IPetService petService;

		@RequestMapping(value = "pet", method = RequestMethod.POST, produces = MediaType.TEXT_PLAIN_VALUE)
		@ResponseBody
		public String addPet(@RequestBody Pet pet, UsernamePasswordAuthenticationToken user, HttpServletResponse httpResponse) {
				 
			 if (!hasRole(user, "ROLE_ADMIN")) {
				 return "Access forbidden !";
			 }
			
			System.out.println("Adding pet " + pet.getId());
			try {
				petService.addPet(pet);
			}
			catch (Exception e) {
				return "Adding pet exception : " + e.getMessage();
			}
			return "OK";
		}
		
		@RequestMapping(value = "pet/{petId}", method = RequestMethod.DELETE, produces = MediaType.TEXT_PLAIN_VALUE)
		@ResponseBody
		public String removePet(@PathVariable String petId, UsernamePasswordAuthenticationToken user, HttpServletResponse httpResponse) {
			
			 if (!hasRole(user, "ROLE_ADMIN")) {
				 return "Access forbidden !";
			 }
			
			try {
				petService.removePet(petId);
			}
			catch (Exception e) {
				return "Removing pet exception : " + e.getMessage();
			}
			return "OK";
		}
		
		@RequestMapping(value = "pet/{petId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
		@ResponseBody
		public Pet findPetById(@PathVariable String petId, HttpServletResponse httpResponse) {
			
			List<PetDao> pets = petService.findPetById(petId);
			
			Pet pet = null;
			
			if (!CollectionUtils.isEmpty(pets)) {
				PetDao petDao = pets.get(0);
				pet = new Pet(petDao.getId(), petDao.getName(), petDao.getPrice());
			}
			
			System.out.println("Pet found by id " + petId + " = " + pet);
			
			return pet;
		}
		
		@RequestMapping(value = "pet/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
		@ResponseBody
		public List<Pet> getAll( HttpServletResponse httpResponse) {
			
			List<Pet> allPets = new ArrayList<Pet>();
			List<PetDao> pets = petService.getAll();
			
			Pet pet = null;
			
			if (!CollectionUtils.isEmpty(pets)) {
				for (PetDao petDao : pets) {
					pet = new Pet(petDao.getId(), petDao.getName(), petDao.getPrice());
					allPets.add(pet);
				}				
			}
			
			System.out.println("Pets found=" + allPets);
			
			return allPets;
		}
		
		private boolean hasRole( UsernamePasswordAuthenticationToken user, String role) {
			boolean granted = false;
			Collection<GrantedAuthority> auth = user.getAuthorities();
			
			 if (!CollectionUtils.isEmpty(auth)) {
				 
				 for (GrantedAuthority grantedAuthority : auth) {
					 					 					 
					if (role.equals(grantedAuthority.getAuthority())) {
						granted = true;
						break;
					}
				}
			 }
			 
			 return granted;
		}
}

