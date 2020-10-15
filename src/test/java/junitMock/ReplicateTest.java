package junitMock;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import businessLogic.BLFacade;
import businessLogic.BLFacadeImplementation;
import domain.User;
import exceptions.NoFounds;
import test.businessLogic.TestFacadeImplementation;

class ReplicateTest {
	
	private BLFacadeImplementation sut;
	//private DataAccess da;
	//private DataAccess da = new DataAccess(ConfigXML.getInstance().getDataBaseOpenMode().equals("initialize"));;
	private User u1;
	
	
	@Mock
	private BLFacade facade;
	
	@BeforeEach
	public void initialize()
	{
		MockitoAnnotations.initMocks(this);
		//facade = new BLFacade();
		u1 = new User("user1", "pass1", "Primero");

	}
	
	@AfterEach
	public void clean()
	{
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
		Mockito.doReturn(-1).when(facade).changeMoney(u2, 10.0f);
		assertThrows(NoFounds.class,
				()-> facade.replicate(u2, "user1", 2.0));
	}
}
