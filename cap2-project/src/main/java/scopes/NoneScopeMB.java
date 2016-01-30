package scopes;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.NoneScoped;

/**
 * Semelhante ao escopo Dependent do CDI. Toda vez que uma instancia deste bean
 * for solicitada, um novo objeto será criado.
 * 
 * @author Matheus
 *
 */
@ManagedBean
@NoneScoped
public class NoneScopeMB implements Serializable {

	private static final long serialVersionUID = 8912260741873404046L;

}
