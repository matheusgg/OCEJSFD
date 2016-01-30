package scopes;

import java.io.Serializable;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

@ManagedBean(eager = true)
@ApplicationScoped
public class ApplicationScopeMB implements Serializable {

	private static final long serialVersionUID = -217215464261614800L;

	/**
	 * Não é possível injetar uma propriedade que possui um escopo inferior ao
	 * escopo do ManagedBean em questao.
	 */
	// @ManagedProperty("#{sessionScopeMB}")
	// private SessionScopeMB sessionScopeMB;

	public ApplicationScopeMB() {
		this.getClass();
	}

}
