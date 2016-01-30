package scopes.cdi;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

/**
 * Por padrao, beans CDI com escopo de aplicacao são lazy, ou seja, só serão
 * criados na primeira vez que forem requisitados.
 * 
 * @author Matheus
 *
 */
@Named
@ApplicationScoped
public class ApplicationScopeNamedMB implements Serializable {

	private static final long serialVersionUID = 4872631718179314688L;

	public ApplicationScopeNamedMB() {
		this.getClass();
	}
}
