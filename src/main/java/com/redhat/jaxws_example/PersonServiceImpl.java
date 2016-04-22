
package com.redhat.jaxws_example;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;





@WebService(endpointInterface = "com.redhat.jaxws_example.PersonService", serviceName="PersonWS")
public class PersonServiceImpl implements PersonService {
	
	private SessionFactory sessionFcatory;
	
	@Override
	@WebMethod
	public List<Person> getPersons() {
		System.out.println("PersonService getPersons() called!!!!!");
		Session session = getSession();
		Transaction tx = session.beginTransaction();
		
		Query query = session.createQuery("from Person");
		List<Person> list =  query.list();
		tx.commit();
		session.close();
		return list;
		
	}

	@Override
	@WebMethod
	public void createPerson(Person p) {
		System.out.println("PersonService createPerson called!!!!!");
		Session session = getSession();
		Transaction tx = session.beginTransaction();
		session.save(p);
		tx.commit();
		session.close();
		
	}

	@Override
	@WebMethod
	public void updatePerson(int id,Person p) {
		System.out.println("PersonService updatePerson() called!!!!!");
		Session session = getSession();
		Transaction tx = session.beginTransaction();
		p.setId(id);	
		session.update(p);
		tx.commit();
		session.close();
		
	}

	@Override
	@WebMethod
	public void deletePerson(int id) {
		System.out.println("PersonService deletePerson() called!!!!!");
		Session session = getSession();
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery("from Person where id = "+id);
		Person p =  (Person)query.list().get(0);
		session.delete(p);
		tx.commit();
		session.close();
		
	}
	
	@WebMethod
	public Person getPerson(int count) {
		System.out.println("PersonService getPerson() called!!!!!");
		Session session = getSession();
		Transaction tx = session.beginTransaction();
		
		Query query = session.createQuery("from Person where id = "+count);
		List<Person> list =  query.list();
		tx.commit();
		session.close();
		return (Person)list.get(0);
		
	}
	
	@WebMethod(exclude=true)
	public Session getSession()
	{
		sessionFcatory = new AnnotationConfiguration().configure().buildSessionFactory();
		return sessionFcatory.openSession();
	}

}

