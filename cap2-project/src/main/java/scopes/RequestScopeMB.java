package scopes;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class RequestScopeMB implements Serializable {

	private static final long serialVersionUID = 4568193862316699999L;

	/**
	 * A anotacao @ManagedProperty é utilizada para injetar valores em uma
	 * determinada propriedade de um managed bean. Para isso, é preciso que o
	 * campo anotado com @ManagedProperty possua um método set, pois a
	 * implementacao do JSF procurara um método set + valor do atributo name da
	 * anotacao @ManagedProperty. Caso o atributo name não seja especificado, o
	 * nome do campo é utilizado para encontrar o método set.
	 */
	@ManagedProperty(value = "#{sessionScopeMB}")
	private SessionScopeMB sessionScopeMB;

	/**
	 * Deste modo, o método setTeste será chamado e receberá a instancia de
	 * applicationMB.
	 */
	@ManagedProperty(value = "#{applicationScopeMB}", name = "teste")
	private ApplicationScopeMB applicationScopeMB;

	public void setSessionScopeMB(SessionScopeMB sessionScopeMB) {
		this.sessionScopeMB = sessionScopeMB;
	}

	public void setTeste(ApplicationScopeMB applicationScopeMB) {
		this.applicationScopeMB = applicationScopeMB;
	}

}
