package test.dataAccess;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import configuration.ConfigXML;
import dataAccess.DataAccess;
import domain.Person;
import domain.User;
import exceptions.BadPassword;
import exceptions.UsernameNoExist;
import test.businessLogic.TestFacadeImplementation;

class TestLogin {

	private DataAccess sut = new DataAccess(ConfigXML.getInstance().getDataBaseOpenMode().equals("initialize"));;
	private TestFacadeImplementation testBL = new TestFacadeImplementation();
	private String username = "i�igo";
	private String pass = "abc";

	@Test
	void testLogin1() {
		//a�adimos user a la bd
		User user = testBL.addUser(username, pass);
		try {
		Person i�igo = sut.login(username, pass);
		assertEquals(i�igo.getUsername(),"i�igo");
		assertEquals(i�igo.getPassword(),"abc");
		}catch(Exception e) {}
		//borramos user
		assertTrue(testBL.removeUser(user));
	}
	@Test
	void testLogin2() {
		User user = testBL.addUser(username, pass);
		assertThrows(BadPassword.class,
				()-> sut.login(username, "def"));
		assertTrue(testBL.removeUser(user));
	}
	@Test
	void testLogin3() {
		assertThrows(UsernameNoExist.class,
				()-> sut.login("i�igow", pass));
	}

}
