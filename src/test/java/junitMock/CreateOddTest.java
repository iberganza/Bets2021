package junitMock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import businessLogic.BLFacadeImplementation;
import dataAccess.DataAccess;
import domain.Odd;
import domain.Question;
import exceptions.InvalidRate;
import exceptions.OddExist;

class CreateOddTest {

	private BLFacadeImplementation facade;
	private static String queryText = "A question";
	private static Float betMinimum = 2.0f;
	private	static int x = 1;
	private static Question q;
	private static Float f= 1.25f;
	private String result = "1-0";
	
	@Mock
	private DataAccess sut;
	
	@BeforeEach
	public void setUp() {
		 MockitoAnnotations.initMocks(this);
		 facade = new BLFacadeImplementation(sut);
	}
	@Test
	void testCreateOdd1() {
		try {
		Odd odd = new Odd(f, result, q);
		Mockito.doReturn(odd).when(sut.createOdd(q, f, result));
		assertEquals(odd,facade.createOdd(q, f, result));
		} catch(Exception e) {}
	}
	@Test
	void testCreateOdd2() {
		try {
		Mockito.when(sut.createOdd(q, f, result)).thenThrow(OddExist.class);
		assertThrows(OddExist.class,
				()-> sut.createOdd(q, f, result));		
		} catch(Exception e) {}
	}
	@Test
	void testCreateOdd3() {
		try {
		Mockito.when(sut.createOdd(q, f, result)).thenThrow(InvalidRate.class);
		assertThrows(InvalidRate.class,
				()-> sut.createOdd(q, f, result));		
		} catch(Exception e) {}
	}

}
