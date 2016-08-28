package de.mathema.campus;

import javax.ejb.Stateless;
import javax.enterprise.context.Dependent;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Dependent
@Stateless
@Named
public class Registration implements IRegistration {

	@PersistenceContext
	private EntityManager entityManager;

	public String register(Customer customer) {
		entityManager.persist(customer);
		return "confirmation";
	}
}
