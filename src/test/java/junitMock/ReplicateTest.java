package junitMock;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import businessLogic.BLFacadeImplementation;
import dataAccess.DataAccess;
import domain.User;
import exceptions.NoFounds;

class ReplicateTest {
	
	private BLFacadeImplementation facade;
	//private DataAccess da;
	//private DataAccess da = new DataAccess(ConfigXML.getInstance().getDataBaseOpenMode().equals("initialize"));;
	private User u1;
	
	
	@Mock
	private DataAccess sut;

	@BeforeEach
	public void initialize()
	{
		MockitoAnnotations.initMocks(this);
		facade = new BLFacadeImplementation();
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
		try
		{
			User userMock = Mockito.mock(User.class);
			User u2 = new User("user2", "pass2", "Primero");
			u2.setMoney(10.0f);
			
			
			
			
			/*ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);
			ArgumentCaptor<String> stringCaptor = ArgumentCaptor.forClass(String.class);
			ArgumentCaptor<Double> quantityCaptor = ArgumentCaptor.forClass(Double.class);
	
			Mockito.verify(facade,Mockito.times(0)).replicate(userCaptor.capture(),
					stringCaptor.capture(),quantityCaptor.capture());*/
			
			//Mockito.doReturn(u0).when(sut).changeMoney(u2, 10.0f);
			//Mockito.doReturn(u0).when(sut).changeMoney(u2, 10.0f);
			//doThrow(NoFounds.class).when(listMock).clear();
			//assertThrows(NoFounds.class,
			//		()-> facade.replicate(u2, "user1", 50.0));
		}catch(Exception e)
		{
			
		}
		
	}
}
