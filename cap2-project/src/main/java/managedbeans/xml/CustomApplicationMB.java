package managedbeans.xml;

import java.io.Serializable;

import javax.annotation.PostConstruct;

public class CustomApplicationMB implements Serializable {

	private static final long serialVersionUID = 899695330946262031L;

	/**
	 * Não é possivel misturar configuracao por anotacao com configuracao por
	 * XML
	 */
	// @ManagedProperty("Teste")
	private String texto;

	@PostConstruct
	public void init() {
		this.getClass();
	}

	/**
	 * @return the texto
	 */
	public String getTexto() {
		return texto;
	}

	/**
	 * @param texto
	 *            the texto to set
	 */
	public void setTexto(String texto) {
		this.texto = texto;
	}

}
