package de.mathema.campus;

import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import de.mathema.campus.exception.DuplicatedCustomerException;
import de.mathema.campus.exception.NameNotNiceException;
import de.mathema.campus.qualifiers.Registered;
import de.mathema.campus.stereotypes.SingletonService;

@SingletonService
public class Registration implements IRegistration {

	@PersistenceContext
	private EntityManager entityManager;

	@Inject
	@Registered
	private Event<Customer> newCustomerEvent;

	@Transactional(rollbackOn = { DuplicatedCustomerException.class }, dontRollbackOn = { NameNotNiceException.class })
	public String register(Customer customer) {
		newCustomerEvent.fire(customer);
		checkIfRegistered(customer);
		entityManager.persist(customer);
		checkIfNameIsNice(customer);
		return "confirmation";
	}

	private void checkIfNameIsNice(Customer customer) {
		if (customer.getNachname().toLowerCase().contains("eber")) {
			throw new NameNotNiceException();
		}
	}

	private void checkIfRegistered(Customer customer) {
		TypedQuery<Customer> query = entityManager.createQuery("SELECT c from Customer c WHERE c.email = :email",
				Customer.class);
		query.setParameter("email", customer.getEmail());
		if (!query.getResultList().isEmpty()) {
			throw new DuplicatedCustomerException("Costumer already registred");
		}
	}
}
