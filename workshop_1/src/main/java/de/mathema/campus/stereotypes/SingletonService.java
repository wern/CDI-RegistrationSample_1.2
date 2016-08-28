package de.mathema.campus.stereotypes;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Stereotype;
import javax.inject.Named;


@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@ApplicationScoped
@Named
@Stereotype
public @interface SingletonService {
}
