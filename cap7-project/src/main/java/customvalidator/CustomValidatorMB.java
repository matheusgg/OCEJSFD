package customvalidator;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import lombok.Data;

@ManagedBean
@ViewScoped
@Data
public class CustomValidatorMB implements Serializable {

	private static final long serialVersionUID = -9000018334200580892L;

	private String name;

	/**
	 * Método utilizado no atributo validator para validar se o valor não está
	 * vazio. Deve possuir a mesma assinatura do método validate de
	 * javax.faces.validator.Validator.
	 * 
	 * @param context
	 * @param component
	 * @param value
	 */
	public void validateTest(FacesContext context, UIComponent component, Object value) {
		if (value.toString().trim().isEmpty()) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Value cannot be empty!", "Value must be specified.");
			throw new ValidatorException(message);
		}
	}

}
