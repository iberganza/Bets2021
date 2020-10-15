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
		//añadimos user a la bd
		User user = testBL.addUser(username, pass);
		try {
		assertEquals(sut.login(username, pass),user);
		}catch(Exception e) {}
		//borramos user
		assertTrue(testBL.removeUser(user));
}
	@Test
	void testLogin2() {
	}
	@Test
	void testLogin3() {
		assertThrows(UsernameNoExist.class,
				()-> sut.login(username, pass));
	}

}
