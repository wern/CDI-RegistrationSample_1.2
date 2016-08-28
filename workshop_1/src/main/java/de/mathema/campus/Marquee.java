package de.mathema.campus;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import de.mathema.campus.stereotypes.SingletonService;

@SingletonService
public class Marquee {

	@PersistenceContext
	private EntityManager entityManager;

	public List<Customer> getRecentRegistrations() {
		return entityManager.createQuery("Select c from Customer c", Customer.class).getResultList();
	}
}
