package junitMock;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import businessLogic.BLFacadeImplementation;
import dataAccess.DataAccess;
import domain.Person;
import exceptions.UsernameNoExist;

class LoginTest {
	
	private BLFacadeImplementation facade;
	
	@Mock
	private DataAccess sut;
	
	@BeforeEach
	public void setUp() {
		 MockitoAnnotations.initMocks(this);
		 facade = new BLFacadeImplementation(sut);
	}
	@Test
	public void test1() {
		Person p = new Person("I�igo","abc");
		try {
		Mockito.doReturn(p).when(sut.login("I�igo", "abc"));
		facade.login("I�igo", "abc");
		assertEquals(p.getUsername(),"I�igo");
		assertEquals(p.getUsername(),"abc");
		}catch(Exception e) {}
	}
	//falta por hacer
	@Test
	public void test2() {
		Person p = new Person("I�igo","abc");
		try {
		Mockito.doReturn().when(sut.login("I�igo", "abc"));
		facade.login("I�igo", "abc");
		assertEquals(p.getUsername(),"I�igo");
		assertEquals(p.getUsername(),"abc");
		}catch(Exception e) {}
	}
	@Test
	void testLogin3() {
		assertThrows(UsernameNoExist.class,
				()-> sut.login("i�igow", "123"));
	}

}
