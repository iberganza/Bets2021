package test.dataAccess;

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

class TestReplicate {
	static TestDataAccess db;
	private DataAccess sut = new DataAccess(ConfigXML.getInstance().getDataBaseOpenMode().equals("initialize"));;
	private static User u1;
	
	@BeforeAll
	public static void initialize()
	{
		u1 = new User("user1", "pass1", "Primero");
		db=new TestDataAccess(); 
		db.addUser("user1", "pass1");

	}
	
	@AfterAll
	public static void clean()
	{
		db.removeUser(u1);
	}

	@Test
	@DisplayName("Test 1: Usuario replicado es null")
	void testReplicate1() 
	{
		User u = new User("n", "n", "n");
		assertThrows(UsernameNoExist.class,
				()-> sut.replicate(u, "user2", 0.0));
	}
	
	@Test
	@DisplayName("Test 2: Usuario replicador no es null, y se añade a la lista de replicados")
	void testReplicate2() 
	{
		User u2 = new User("user2", "pass2", "nombre");
		db.addUser("user2", "pass2");
		try 
		{
			sut.replicate(u2, "user1", 0.0);
		}catch(UsernameNoExist e)
		{
			fail("Usuario no esta en bd");
		}
		
		User replicator = db.find(u2);
		replicator.getReplicatingUsers().contains(u2);
		assertThrows(UsernameNoExist.class,
				()-> sut.replicate(u2, "user2", 0.0));
		
		db.removeUser(u2);
	}
	
	@Test
	@DisplayName("Test 3: Usuario replicador es null")
	void testReplicate3() 
	{
	}
	
	

}
