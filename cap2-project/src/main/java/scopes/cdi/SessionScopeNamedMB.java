package scopes.cdi;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named
@SessionScoped
public class SessionScopeNamedMB implements Serializable {

	private static final long serialVersionUID = 4372035472966976770L;
	
}
