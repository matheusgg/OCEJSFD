package scopes.cdi;

import java.io.Serializable;

import javax.enterprise.context.Dependent;
import javax.inject.Named;

/**
 * Beans CDI com escopos dependentes sao inferiores aos beans com escopo de
 * requisicao, pois sempre serao instanciados quando solicitados, ou seja, a
 * mesma instancia nunca seja compartilhada, deste modo, uma nova instancia
 * deste bean sempre sera criada.
 * 
 * @author Matheus
 *
 */
@Named
@Dependent
public class DependentScopeNamedMB implements Serializable {

	private static final long serialVersionUID = -2701238980270463044L;

}
