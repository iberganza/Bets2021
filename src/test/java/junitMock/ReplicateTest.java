package junitMock;

import static org.junit.Assert.fail;
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
import exceptions.UsernameNoExist;

class ReplicateTest {
	
	private BLFacadeImplementation facade;
	private User u2;
	
	@Mock
	private DataAccess sut;
	

	@BeforeEach
	public void initialize()
	{
		MockitoAnnotations.initMocks(this);
		facade = new BLFacadeImplementation(sut);
		u2 = new User("user2", "pass2", "Primero");
		u2.setMoney(10.0f);
	}
	
	@Test
	@DisplayName("Test 1: El dinero del usuario es mayor que el parametro amount pasado")
	void replicate1() 
	{
		try {
			User u2 = new User("user2", "pass2", "Primero");
			u2.setMoney(10.0f);
			facade.replicate(u2, "user1", 2.0);
			
			ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);
			ArgumentCaptor<String> stringCaptor = ArgumentCaptor.forClass(String.class);
			ArgumentCaptor<Double> quantityCaptor = ArgumentCaptor.forClass(Double.class);
			
			Mockito.verify(sut,Mockito.times(1)).replicate(userCaptor.capture(),
					stringCaptor.capture(),quantityCaptor.capture());
		}catch(NoFounds e)
		{
			fail("Error de dinero");
		}catch(UsernameNoExist e)
		{
			fail("Error de usuario");
		}
	}
	
	@Test
	@DisplayName("Test 2: El dinero del usuario es menor que el parametro amount pasado")
	void replicate2() 
	{
		assertThrows(NoFounds.class,
				()-> facade.replicate(u2, "user1", 50.0));
	}
}
