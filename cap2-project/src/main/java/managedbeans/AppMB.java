package managedbeans;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;

/**
 * ManagedBeans são componentes que armazenam o estado das interfaces em JSF.
 * Quando a anotacao @ManagedBean é utilizada sem a especificacao do atributo
 * "name", ou este atributo possua uma string vazia "", o nome simples da classe
 * é utilizado como nome do bean gerenciado com a primeira letra em caixa baixa.
 * O atributo eager é utilizado em conjunto com o escopo de aplicacao, deste
 * modo, caso este atributo possua o valor true e o escopo definido seja de
 * aplicacao, a implementacao JSF deve instanciar este bean antes da aplicacao
 * ser colocada em servico. Os managed beans podem possuir 6 tipos de escopo,
 * que nada mais é do que o tempo de vida util deste bean. NoneScoped,
 * CustomScoped, RequestScoped, ViewScoped, SessionScoped e ApplicacationScoped.
 * Caso nenhum escopo seja definido, o escopo de requisicao será utilizado.
 * 
 * @author Matheus
 *
 */
@ManagedBean(name = "")
// @SessionScoped
public class AppMB implements Serializable {

	private static final long serialVersionUID = -6371950992067232142L;

	private String text;
	private String URL;

	/**
	 * @return the text
	 */
	public String getText() {
		return text;
	}

	/**
	 * @param text
	 *            the text to set
	 */
	public void setText(String text) {
		this.text = text;
	}

	/**
	 * @return the uRL
	 */
	public String getURL() {
		return URL;
	}

	/**
	 * @param uRL
	 *            the uRL to set
	 */
	public void setURL(String uRL) {
		URL = uRL;
	}

}
