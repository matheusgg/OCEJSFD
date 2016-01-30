package scopes;

import java.io.Serializable;
import java.util.HashMap;

import javax.annotation.PostConstruct;
import javax.faces.bean.CustomScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

/**
 * O JSF 2.0 trouxe um novo escopo chamado de CustomScoped que disponibiliza
 * mais flexibilidade para os desenvolvedores, uma vez que fica a cargo do
 * programador o controle deste escopo. Uma expressao que deverá avaliar para um
 * mapa precisa ser informada, pois este mapa será utilizado para armazenar a
 * instancia deste bean.
 * 
 * @author Matheus
 *
 */
@ManagedBean
@CustomScoped("#{myCustomScope}")
public class CustomScopeMB implements Serializable {

	private static final long serialVersionUID = -5783294261713473547L;

	@PostConstruct
	public void init() {
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("myCustomScope", new HashMap<String, Object>());
	}

}
