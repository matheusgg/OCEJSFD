package managedbeans.xml;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class CustomViewMB implements Serializable {

	private static final long serialVersionUID = -4324538420658843610L;

	private String texto;
	private List<String> cidades;
	private Map<String, String> mapa;

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

	/**
	 * @return the cidades
	 */
	public List<String> getCidades() {
		return cidades;
	}

	/**
	 * @param cidades
	 *            the cidades to set
	 */
	public void setCidades(List<String> cidades) {
		this.cidades = cidades;
	}

	/**
	 * @return the mapa
	 */
	public Map<String, String> getMapa() {
		return mapa;
	}

	/**
	 * @param mapa
	 *            the mapa to set
	 */
	public void setMapa(Map<String, String> mapa) {
		this.mapa = mapa;
	}

}
