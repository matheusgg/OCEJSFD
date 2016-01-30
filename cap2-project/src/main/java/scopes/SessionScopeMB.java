package scopes;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class SessionScopeMB implements Serializable {

	private static final long serialVersionUID = -6513850639719079697L;

	/**
	 * Não é possível injetar uma propriedade que possui um escopo inferior ao
	 * escopo do ManagedBean em questao.
	 */
	// @ManagedProperty("#{requestScopeMB}")
	// private RequestScopeMB requestScopeMB;

	@ManagedProperty("Teste")
	private String texto;

	public void setTexto(String texto) {
		this.texto = texto;
	}

}
