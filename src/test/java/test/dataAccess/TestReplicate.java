package test.dataAccess;

import static org.junit.jupiter.api.Assertions.assertThrows;

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

	
	@BeforeAll
	public static void initialize()
	{
		db=new TestDataAccess(); 
		db.addUser("user1", "pass1");
	}

	@Test
	@DisplayName("Test 1: Usuario replicado es null")
	void testReplicate1() 
	{
		User u = new User("n", "n", "n");
		assertThrows(UsernameNoExist.class,
				()-> sut.replicate(u, "user1", 0.0));
	}
	
	@Test
	@DisplayName("Test 2: Usuario replicador no es null, y se añade a la lista de replicados")
	void testReplicate2() 
	{
		User u = new User("user2", "pass2", "nombre");
		try {
			
		}catch()
		sut.replicate(u, "user1", 0.0);
		User replicator = db.find(u);
		replicator.getReplicatingUsers().contains(u);
		assertThrows(UsernameNoExist.class,
				()-> sut.replicate(u, "user2", 0.0));
		
	}
	
	@Test
	@DisplayName("Test 3: Usuario replicador es null")
	void testReplicate3() 
	{
	}
	
	

}
