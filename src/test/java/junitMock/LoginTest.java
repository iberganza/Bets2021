package junitMock;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import configuration.ConfigXML;
import dataAccess.DataAccess;
import domain.User;
import exceptions.UsernameNoExist;
import test.businessLogic.TestFacadeImplementation;

class LoginTest {
	private DataAccess sut = new DataAccess(ConfigXML.getInstance().getDataBaseOpenMode().equals("initialize"));;
	private TestFacadeImplementation testBL = new TestFacadeImplementation();
	private String username = "iñigo";
	private String pass = "abc";

	@Test
	void testLogin1() {
			//User u = new User(username,pass,"");
			User user = testBL.addUser(username, pass);
			//assertEquals(u,user);
			boolean b = testBL.removeUser(user);
			assertTrue(b);
	}
	@Test
	void testLogin3() {
		assertThrows(UsernameNoExist.class,
				()-> sut.login(username, pass));
	}

}
