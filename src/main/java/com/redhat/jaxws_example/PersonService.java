package com.redhat.jaxws_example;

import java.util.List;

import javax.jws.WebService;

@WebService
public interface PersonService {
	
	public Person getPerson(int id);
	public List <Person> getPersons();
	public void createPerson(Person p);
	public void updatePerson(int id,Person p);
	public void deletePerson(int id);
}

