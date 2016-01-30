package customvalidator;

import java.util.Arrays;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 * A anotacao FacesValidator registra este validador chamando o método
 * FacesContext.getCurrentInstance().getApplication().addValidator(String,
 * String) passando o id do validador informado no atributo value como primeiro
 * argumento e o nome totalmente qualificado da classe desse validador como
 * segundo argumento. O atributo isDefault serve para especificar se este
 * validador deve ser adicionado na lista de validadores padrao.
 * 
 * @author Matheus
 *
 */
@FacesValidator(value = "customvalidator.LengthValidator", isDefault = false)
public class LengthValidator implements Validator {

	private static final String USER_DEFINED_LENGTH_KEY = "customvalidator.LengthValidator_length";

	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		if (value == null) {
			return;
		}

		String length = this.getUserDefinedLength(component);

		if (length == null) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Length is not defined!", "The length must be defined.");
			/*
			 * ValidatorException possui 4 construtores.
			 */
			// throw new ValidatorException(message);
			throw new ValidatorException(Arrays.asList(message));
			// throw new ValidatorException(Arrays.asList(message), new
			// IllegalArgumentException());
			// throw new ValidatorException(message, new
			// IllegalArgumentException());
		}

		if (!Integer.valueOf(length).equals(value.toString().length())) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid length!", "The Length can't be different than " + length);
			throw new ValidatorException(message);
		}
	}

	private String getUserDefinedLength(UIComponent component) {
		return (String) component.getAttributes().get(USER_DEFINED_LENGTH_KEY);
	}

}
