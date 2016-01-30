package navegacao;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class NavegacaoMB implements Serializable {

	private static final long serialVersionUID = -7675252561791498126L;

	private String user;

	@PostConstruct
	public void init() {
		this.getClass();
	}

	/**
	 * Qualquer resultado diferente de null ou String vazia ("") fará com que o
	 * escopo de visualizacao seja reiniciado. <br >
	 * 
	 * Utilizando a navegacao dinamica, a String retornada pelo metodo (outcome)
	 * é transformada em um ID de visualizacao. Entao o handler de navegacao
	 * verifica se existe uma visualizacao com este ID, caso positivo, essa
	 * visualizacao é exibida, senao, a visualizacao atual é reexibida.
	 * 
	 * @return
	 */
	public String verifyUser() {
		if (this.user.isEmpty()) {
			return "";
		}

		if ("root".equals(this.user)) {
			return "sucesso";
		} else {
			return "falha";
		}
	}

	public String testAction1() {
		return "action";
	}

	public String testAction2() {
		return "action";
	}

	public boolean teste() {
		return true;
	}

	/**
	 * @return the user
	 */
	public String getUser() {
		return user;
	}

	/**
	 * @param user
	 *            the user to set
	 */
	public void setUser(String user) {
		this.user = user;
	}
}
