package flash;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;

@ManagedBean
@RequestScoped
public class FlashMB implements Serializable {

	private static final long serialVersionUID = 1552618762510825153L;

	private String nome;

	/**
	 * O escopo Flash é um mapa utilizado para manter objetos entre requisicoes.
	 * 
	 * @param attr
	 * @param value
	 * @return
	 */
	public String testFlash(String attr, Object value) {
		Flash flash = this.getFlash();

		// Adicionando atributos no escopo de requisicao
		this.getExternalContext().getRequestMap().put("teste", "param");
		this.getExternalContext().getRequestMap().put("teste1", "param1");

		/*
		 * O método keep promove um atributo do escopo de requisicao
		 * (RequestScope) para o escopo de flash.
		 */
		flash.keep("teste");

		/*
		 * O método put (ou putNow) adiciona um atributo no escopo de flash.
		 */
		flash.put(attr, value); // ou flash.putNow(attr, value);

		return "page2";
	}

	public String testMessages() {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Mensagem de Teste", "Mensagem de Teste"));

		/*
		 * Como as FacesMessages sao armazenadas no escopo de flash, se o método
		 * setKeepMessages for chamado passando o valor true e um
		 * redirecionamento fisico seja feito (atraves de um faces-redirect), as
		 * mensagens serao preservadas para serem exibidas na proxima
		 * requisicao. Este método só tem efeito caso um redirecionamento físico
		 * seja feito.
		 */
		this.getFlash().setKeepMessages(true);

		return "messages2?faces-redirect=true";
	}

	private Flash getFlash() {
		return this.getExternalContext().getFlash();
	}

	private ExternalContext getExternalContext() {
		return FacesContext.getCurrentInstance().getExternalContext();
	}

	/**
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * @param nome
	 *            the nome to set
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

}
