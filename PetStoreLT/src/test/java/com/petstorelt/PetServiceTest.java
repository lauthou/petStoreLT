package com.petstorelt;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.petstorelt.model.Pet;
import com.petstorelt.persisitence.dao.PetDao;
import com.petstorelt.service.PetService;

@ContextConfiguration(locations = { "classpath:context.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class PetServiceTest {
	
	@Autowired
	private PetService petService;
	
	@Test
	public void testAdd() {
		petService.addPet(new Pet("1", "Vickie", "600€"));
		
		List<PetDao> pets = petService.getAll();
		
		Assert.assertNotNull(pets);
		Assert.assertEquals("1 pet found", 1, pets.size());
		Assert.assertEquals("Vickie", pets.get(0).getName());
	}	

	@Test
	public void testFindById() {
		List<PetDao> pets = petService.findPetById("1");
		
		Assert.assertNotNull(pets);
		Assert.assertEquals("1 pet found", 1, pets.size());
		Assert.assertEquals("Vickie", pets.get(0).getName());
	}
	
	@Test
	public void testRemove() {
		petService.removePet("1");
		
		List<PetDao> pets = petService.getAll();
		
		Assert.assertNull(pets);
	}
}
