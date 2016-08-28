package de.mathema.campus;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Named
@ApplicationScoped
public class Marquee {

	@PersistenceContext
	private EntityManager entityManager;

	public List<Customer> getRecentRegistrations(){
		return entityManager.createQuery("Select c from Customer c", Customer.class).getResultList();
	}
}
