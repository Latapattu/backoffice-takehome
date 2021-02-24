package com.fuze.takehome.test;

import java.util.List;

import javax.inject.Inject;

import org.junit.Assert;
import org.junit.Test;

import com.fuze.takehome.model.User;
import com.fuze.takehome.service.UserService;

public class UserTest extends AbstractEntityTest {

	@Inject
	private UserService service;
	
	/**
	 * Test the User Entity and User Service.
	 * 
	 * Assert that entity validation is working correctly and
	 * the service methods are doing what they are supposed to be doing.
	 * 
	 * Before this test executes, AbstractEntityTest takes care of initializing
	 * Spring and starting an in-memory DB instance. DummyDataGenerator is
	 * invoked to insert a couple dummy entities into the database. 
	 * 
	 */
	@Test
	public void testUser() {
		List<User> allUsers = service.list();
		Assert.assertNotNull(allUsers);
		Assert.assertEquals(2, allUsers.size());
		
		User newUser = new User()
		.withActive(true)
		.withCustomerId(0L)
		.withEmail("myTest@test.com")
		.withFirstName("My")
		.withLastName("Test")
		.withMobileNumber("555-2356-254")
		.withTelephoneNumber("555-8512-763")
		.withUserName("rcastorena");

		//add the user and check if the size increases to verify its addition
		service.create(newUser);
		allUsers = service.list();
		Assert.assertNotNull(allUsers);
		Assert.assertEquals(3, allUsers.size());
		//get user by id and assert that the values are the same as above
		//checks both User model and read simultaneously
		User testUser = service.read(2L);
		Assert.assertEquals(newUser.getCustomerId(), testUser.getCustomerId());
		Assert.assertEquals(newUser.isActive(), testUser.isActive());
		Assert.assertEquals(newUser.getEmail(), testUser.getEmail());
		Assert.assertEquals(newUser.getFirstName(), testUser.getFirstName());
		Assert.assertEquals(newUser.getLastName(), testUser.getLastName());
		Assert.assertEquals(newUser.getMobileNumber(), testUser.getMobileNumber());
		Assert.assertEquals(newUser.getUserName(), testUser.getUserName());

		//test delete
		service.delete(2L);
		allUsers = service.list();
		Assert.assertNotNull(allUsers);
		Assert.assertEquals(2, allUsers.size());
	}
}
