package test.dataAccess;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import configuration.ConfigXML;
import dataAccess.DataAccess;
import domain.Odd;
import domain.Question;
import exceptions.InvalidRate;
import exceptions.OddExist;
import test.businessLogic.TestFacadeImplementation;

class TestCreateOdd {
	private static DataAccess sut = new DataAccess(ConfigXML.getInstance().getDataBaseOpenMode().equals("initialize"));;
	private static TestFacadeImplementation testBL = new TestFacadeImplementation();
	private static String queryText = "A question";
	private static Float betMinimum = 2.0f;
	private	static int x = 1;
	private static Question q;
	private static Float f= 1.25f;
	private String result = "1-0";
	

	@Test
	void testCreateOdd1() {
		q = testBL.addQuestion(x, queryText, betMinimum);
		try {
		Odd o = sut.createOdd(q, f, result);
		Float flo= o.getFee();
		Question qu =o.getQuestion();
		String str = o.getResultBet();;
		assertEquals(o.getFee(),flo);
		assertEquals(o.getQuestion(),qu);
		assertEquals(o.getResultBet(),str);
		}catch(Exception e) {}
		testBL.removeQuestion(q);
	}
	@Test
	void testCreateOdd2() {
		q = testBL.addQuestion(x, queryText, betMinimum);
		try {
			sut.createOdd(q, f, result);
			assertThrows(OddExist.class,
					()-> sut.createOdd(q, f, result));
			}catch(Exception e) {}
		testBL.removeQuestion(q);
	}
	@Test
	void testCreateOdd3() {
		q = testBL.addQuestion(x, queryText, betMinimum);
		try {
			assertThrows(InvalidRate.class,
					()-> sut.createOdd(q, 0.5f, result));
			}catch(Exception e) {}
		testBL.removeQuestion(q);
	}

}
