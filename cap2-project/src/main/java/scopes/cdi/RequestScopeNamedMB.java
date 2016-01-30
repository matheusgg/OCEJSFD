package scopes.cdi;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class RequestScopeNamedMB implements Serializable {

	private static final long serialVersionUID = 7133087518158765820L;

}
