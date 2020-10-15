package test.dataAccess;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import configuration.ConfigXML;
import dataAccess.DataAccess;
import domain.User;
import exceptions.UsernameNoExist;
import test.businessLogic.TestFacadeImplementation;

class TestReplicate {
	private DataAccess sut = new DataAccess(ConfigXML.getInstance().getDataBaseOpenMode().equals("initialize"));
	private static TestFacadeImplementation testBL = new TestFacadeImplementation();
	private static User u1;
	
	@BeforeAll
	public static void initialize()
	{
		u1 = new User("user1", "pass1", "Primero");
		testBL.addUser("user1", "pass1");

	}
	
	@AfterAll
	public static void clean()
	{
		testBL.removeUser(u1);
	}

	@Test
	@DisplayName("Test 1: Usuario replicado es null")
	void testReplicate1() 
	{
		User u = new User("n", "n", "n");
		assertThrows(UsernameNoExist.class,
				()-> sut.replicate(u, "n", 0.0));
	}
	
	@Test
	@DisplayName("Test 2: Usuario replicador no es null, y se añade a la lista de replicados")
	void testReplicate2() 
	{
		User u2 = new User("user2", "pass2", "nombre");
		assertEquals(u2.getReplicatingUsers().size(),0);
		testBL.addUser("user2", "pass2");
		try 
		{
			sut.replicate(u2, "user1", 0.0);
		}catch(UsernameNoExist e)
		{
			fail("Usuario no esta en bd");
		}
		
		User replicator = testBL.findUser(u2);
		assertEquals(u2.getReplicatingUsers().size(),1);
		testBL.removeUser(u2);
	}
	
	@Test
	@DisplayName("Test 3: Usuario replicador es null")
	void testReplicate3() 
	{
		User u3 = new User("user3", "pass3", "nombre");
		assertEquals(u3.getReplicatingUsers().size(),0);
		testBL.addUser("user3", "pass3");
		try 
		{
			sut.replicate(u3, "user1", 0.0);
		}catch(UsernameNoExist e)
		{
			fail("Usuario no esta en bd");
		}
		assertEquals(u3.getReplicatingUsers().size(),1);
		testBL.removeUser(u3);
		try 
		{
			sut.replicate(u3, "user1", 0.0);
		}catch(UsernameNoExist e)
		{
			fail("Usuario no esta en bd");
		}
		assertEquals(u3.getReplicatingUsers().size(),1);
		//Al hacer otro replicate, ya esta añadido
		//por lo que devuelve null, ver metodo addReplicator en user
	}
	
	

}
