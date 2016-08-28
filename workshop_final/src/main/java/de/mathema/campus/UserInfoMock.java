package de.mathema.campus;

import java.security.Principal;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.Specializes;

import de.mathema.campus.stereotypes.SingletonService;

@SingletonService
public class UserInfoMock extends UserInfo {

	@Override
	// @Specializes @Produces
	public User getUserLoggedIn(Principal principal) {
		return new User("The Mock","Muck");
	}
}
