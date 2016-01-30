package namedbeans;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 * Junto com a especificacao CDI, vieram os beans gerenciados CDI, que podem ser
 * utilizados em paginas JSF. Essses beans sao semelhantes aos managed beans
 * porem sao mais flexiveis, uma vez que fazem parte de uma especificacao do
 * Java EE responsavel por integrar varias outras especificacoes da plataforma.
 * Deste modo, é aconselhavel a utilizacao dos Named beans ao inves dos managed
 * beans, uma vez que estes sao mais poderosos e praticos. Os namedbeans, assim
 * como os managedbeans, podem possuir 5 tipos de escopo. Dependent,
 * RequestScoped, ConversationScoped, SessionScoped e ApplicacationScoped.
 * Quando nenhum escopo é especificado, o escopo Dependent é utilizado.
 * 
 * @author Matheus
 *
 */
@Named
@RequestScoped
public class AppNamedMB implements Serializable {

	private static final long serialVersionUID = -8384538316414087261L;

	

}
