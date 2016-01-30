package beanvalidation;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.Data;
import beanvalidation.Range;

@ManagedBean
@ViewScoped
@Data
public class BeanValidationMB implements Serializable {

	private static final long serialVersionUID = -1878007013145412923L;

	/**
	 * Esta mensagem nao sera exibida, pois no componente que foi aplicado a
	 * validacao de bean foi especificado o atributo validatorMessage.
	 */
	@NotNull(groups = Serializable.class, message = "O nome do usuário não pode ser nulo!")
	private String name;

	/**
	 * A mensagem relacionada a esta chave sera recuperada do arquivo
	 * ValidationMessage.properties e passada para preencher o placeholder {0}
	 * da mensagem associada a chave javax.faces.validator.BeanValidator.MESSAGE
	 * do arquivo de mensagens padrao do JSF (ou um message-bundle).
	 */
	@Min(value = 18, message = "{msg.idade.minima.invalida}")
	private Integer idade;

	/**
	 * A mensagem relacionada a esta chave sera recuperada do arquivo
	 * ValidationMessage.properties e passada para preencher o placeholder {0}
	 * da mensagem associada a chave javax.faces.validator.BeanValidator.MESSAGE
	 * do arquivo de mensagens padrao do JSF (ou um message-bundle).
	 */
	@NotNull(message = "{msg.endereco.invalido}")
	private String endereco;

	@Range(min = 10, max = 20)
	private Long valor;

}
