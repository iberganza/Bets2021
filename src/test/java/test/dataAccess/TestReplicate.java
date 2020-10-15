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
	static TestDataAccess dbManagerTest;
	private DataAccess sut = new DataAccess(ConfigXML.getInstance().getDataBaseOpenMode().equals("initialize"));;

	
	@BeforeAll
	public static void initialize()
	{
		dbManagerTest=new TestDataAccess(); 
		dbManagerTest.addUser("user1", "contr");
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
	@DisplayName("Test 2: Usuario replicador no es null")
	void testReplicate2() 
	{
		assertThrows(UsernameNoExist.class,
				()-> cFull.add(7));
	}
	
	@Test
	@DisplayName("Test 3: Usuario replicador es null")
	void testReplicate3() 
	{
		assertThrows(UsernameNoExist.class,
				()-> cFull.add(7));
	}
	
	

}
