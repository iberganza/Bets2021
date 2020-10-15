package junitMock;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import businessLogic.BLFacade;
import businessLogic.BLFacadeImplementation;
import configuration.ConfigXML;
import dataAccess.DataAccess;
import domain.User;
import exceptions.NoFounds;
import test.businessLogic.TestFacadeImplementation;

class ReplicateTest {
	
	private DataAccess da = new DataAccess(ConfigXML.getInstance().getDataBaseOpenMode().equals("initialize"));;
	private BLFacadeImplementation sut = new BLFacadeImplementation(da);
	private static TestFacadeImplementation testBL = new TestFacadeImplementation();
	private static User u1;
	
	@Mock
	private BLFacade facade;
	
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
	@DisplayName("Test 1")
	void replicate1() 
	{
		
	}
	
	@Test
	@DisplayName("Test 2")
	void replicate2() 
	{
		
		User u2 = new User("user2", "pass2", "Primero");
		testBL.addUser("user2", "pass2");
		Mockito.doReturn(10.0f).when().u1.getMoney();
		assertThrows(NoFounds.class,
				()-> sut.replicate(u2, "user1", 2.0));
	}

}
