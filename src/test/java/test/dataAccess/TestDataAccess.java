package test.dataAccess;
/**
 * Auxiliary DataAccess class for testing 
 */

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import configuration.ConfigXML;
import domain.Event;
import domain.Question;
import domain.User;

public class TestDataAccess {
	protected  EntityManager  db;
	protected  EntityManagerFactory emf;

	ConfigXML  c=ConfigXML.getInstance();


	public TestDataAccess()  {
		
		System.out.println("Creating TestDataAccess instance");

		open();
		
	}

	
	public void open(){
		
		System.out.println("Opening TestDataAccess instance ");

		String fileName=c.getDbFilename();
		
		if (c.isDatabaseLocal()) {
			  emf = Persistence.createEntityManagerFactory("objectdb:"+fileName);
			  db = emf.createEntityManager();
		} else {
			Map<String, String> properties = new HashMap<String, String>();
			  properties.put("javax.persistence.jdbc.user", c.getUser());
			  properties.put("javax.persistence.jdbc.password", c.getPassword());

			  emf = Persistence.createEntityManagerFactory("objectdb://"+c.getDatabaseNode()+":"+c.getDatabasePort()+"/"+fileName, properties);

			  db = emf.createEntityManager();
    	   }
		
	}
	public void close(){
		db.close();
		System.out.println("DataBase closed");
	}

	public boolean removeEvent(Event ev) {
		System.out.println(">> DataAccessTest: removeEvent");
		Event e = db.find(Event.class, ev.getEventNumber());
		if (e!=null) {
			db.getTransaction().begin();
			db.remove(e);
			db.getTransaction().commit();
			return true;
		} else 
		return false;
    }
		
		public Event addEvent(String desc, Date d) {
			System.out.println(">> DataAccessTest: addEvent");
			Event ev=null;
				db.getTransaction().begin();
				try {
				    ev=new Event(desc,d);
					db.persist(ev);
					db.getTransaction().commit();
				}
				catch (Exception e){
					e.printStackTrace();
				}
				return ev;
	    }
		public User addUser(String user, String pass) {
			System.out.println(">> DataAccessTest: addUser");
			User u = null;
			try {
				db.getTransaction().begin();
				u = new User(user,pass,"");
				db.persist(u);
				db.getTransaction().commit();
			}catch(Exception e) {
				e.printStackTrace();
			}
			return u;
		}
		public boolean removeUser(User u) {
			System.out.println(">> DataAccessTest: removeUser");
			User user = db.find(User.class, u.getUsername());
			if (user!=null) {
				db.getTransaction().begin();
				db.remove(user);
				db.getTransaction().commit();
				System.out.println("user removed");
				return true;
			} else 
			{
				System.out.println("Usuario no esta en db");
			}
			return false;
	    }
		public Question addQuestion(int x,String text, Float f) {
			System.out.println(">> DataAccessTest: addQuestion");
			Question q = null;
			try {
				db.getTransaction().begin();
				q = new Question(x,text,f,null);
				db.persist(q);
				db.getTransaction().commit();
			}catch(Exception e) {
				e.printStackTrace();
			}
			return q;
		}
		public boolean removeQuestion(Question question) {
			System.out.println(">> DataAccessTest: removeQuestion");
			Question q = db.find(Question.class, question.getQuestionNumber());
			if (q!=null) {
				db.getTransaction().begin();
				db.remove(q);
				db.getTransaction().commit();
				System.out.println("Question removed");
				return true;
			} else 
			return false;
	    }
		
		public User find(User u)
		{
			return db.find(User.class, u);
		}
}


