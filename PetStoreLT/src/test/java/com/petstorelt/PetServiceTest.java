package com.petstorelt;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.petstorelt.model.Pet;
import com.petstorelt.service.PetService;

@ContextConfiguration(locations = { "classpath:context.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class PetServiceTest {
	
	@Autowired
	private PetService petService;
	
	private final static String ID = "B1";
	private final static String NAME = "Bambi";
	private final static String PRICE = "600€";
	
	@Test
	public void testAdd() {
		petService.addPet(new Pet(ID, NAME, PRICE));
		
		List<Pet> pets = petService.getAll();
		
		Assert.assertNotNull(pets);
		Assert.assertEquals("1 pet found", 1, pets.size());
		Assert.assertEquals(NAME, pets.get(0).getName());
	}	

	@Test
	public void testFindById() {
		List<Pet> pets = petService.findPetById(ID);
		
		Assert.assertNotNull(pets);
		Assert.assertEquals("1 pet found", 1, pets.size());
		Assert.assertEquals(NAME, pets.get(0).getName());
	}
	
	@Test
	public void testRemove() {
		petService.deletePet(ID);
		
		List<Pet> pets = petService.getAll();
		
		Assert.assertEquals(0, pets.size());
	}
}
