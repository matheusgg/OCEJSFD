package messages;

import java.io.Serializable;
import java.util.Locale;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

@Named
@RequestScoped
public class MessagesMB implements Serializable {

	private static final long serialVersionUID = 808111928112513326L;

	private Double valor = 100.0;

	/**
	 * @return the valor
	 */
	public Double getValor() {
		return valor;
	}

	/**
	 * @param valor
	 *            the valor to set
	 */
	public void setValor(Double valor) {
		this.valor = valor;
	}

	/**
	 * Converte ponto.
	 *
	 * @param ponto
	 *            the ponto
	 * @return the int
	 */
	public int convertePonto(int ponto) {
		return ponto;
	}

	/**
	 * É possivel definir a localizacao da visualizacao atual programaticamente.
	 * Caso nao exista arquivo de mensagem para a localidade informada, o
	 * arquivo de mensagem da localidade padrao será utilizadao.
	 *
	 * @param language
	 *            the language
	 * @param country
	 *            the country
	 */
	public void changeLanguage(String language, String country) {
		Locale userLocale = new Locale(language, country);
		FacesContext context = FacesContext.getCurrentInstance();
		context.getViewRoot().setLocale(userLocale);
	}

}
