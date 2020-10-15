package test.businessLogic;
/**
 * Auxiliary FacadeImplementation class for testing DataAccess
 */

import java.util.Date;

import configuration.ConfigXML;
import domain.Event;
import domain.Question;
import domain.User;
import test.dataAccess.TestDataAccess;

public class TestFacadeImplementation {
	TestDataAccess dbManagerTest;
 	
    
	   public TestFacadeImplementation()  {
			
			System.out.println("Creating TestFacadeImplementation instance");
			ConfigXML c=ConfigXML.getInstance();
			dbManagerTest=new TestDataAccess(); 
			dbManagerTest.close();
		}
		
		 
		public boolean removeEvent(Event ev) {
			dbManagerTest.open();
			boolean b=dbManagerTest.removeEvent(ev);
			dbManagerTest.close();
			return b;

		}
		
		public Event addEvent(String desc, Date d) {
			dbManagerTest.open();
			Event o=dbManagerTest.addEvent(desc,d);
			dbManagerTest.close();
			return o;

		}
		public boolean removeUser(User u) {
			dbManagerTest.open();
			boolean b=dbManagerTest.removeUser(u);
			dbManagerTest.close();
			return b;

		}
		public User addUser(String username, String pass) {
			dbManagerTest.open();
			User o=dbManagerTest.addUser(username,pass);
			dbManagerTest.close();
			return o;

		}
		public boolean removeQuestion(Question u) {
			dbManagerTest.open();
			boolean b=dbManagerTest.removeQuestion(u);
			dbManagerTest.close();
			return b;

		}
		public Question addQuestion(int x,String text, Float f) {
			dbManagerTest.open();
			Question o=dbManagerTest.addQuestion(x,text,f);
			dbManagerTest.close();
			return o;

		}
		public User findUser(User u) {
			dbManagerTest.open();
			User e = dbManagerTest.find(u);
			dbManagerTest.close();
			return e;
		}

}
