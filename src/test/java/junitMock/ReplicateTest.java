package junitMock;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import businessLogic.BLFacadeImplementation;
import domain.User;
import exceptions.NoFounds;
import test.businessLogic.TestFacadeImplementation;

class ReplicateTest {
	
	//private DataAccess da;
	//private DataAccess da = new DataAccess(ConfigXML.getInstance().getDataBaseOpenMode().equals("initialize"));;
	private static TestFacadeImplementation testBL = new TestFacadeImplementation();
	private static User u1;
	
	@Mock
	private static DataAccess da;
	//private static BLFacadeImplementation sut;
	
	@BeforeAll
	public static void initialize()
	{
		//MockitoAnnotations.initMocks(sut);
		sut = new BLFacadeImplementation();
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
		Mockito.doReturn(10.0f).when(sut).changeMoney(u2, 10.0f);
		assertThrows(NoFounds.class,
				()-> sut.replicate(u2, "user1", 2.0));
	}
}
