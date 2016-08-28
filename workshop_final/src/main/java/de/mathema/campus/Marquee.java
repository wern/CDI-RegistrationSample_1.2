package de.mathema.campus;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.event.TransactionPhase;
import javax.enterprise.inject.Produces;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import de.mathema.campus.qualifiers.Registered;
import de.mathema.campus.stereotypes.SingletonService;

@SingletonService
public class Marquee {

	@Produces
	@SessionScoped
	@Named
	private List<Customer> recentRegistrations = new ArrayList<Customer>();

	public void addRegistration(@Observes(during = TransactionPhase.AFTER_SUCCESS) @Registered Customer customer) {
		recentRegistrations.add(customer);
	}

	public void failRegistration(@Observes(during = TransactionPhase.AFTER_FAILURE) @Registered Customer customer) {
		FacesContext.getCurrentInstance().addMessage("Costumer",
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Costumer bereits registiert", null));
	}
}
