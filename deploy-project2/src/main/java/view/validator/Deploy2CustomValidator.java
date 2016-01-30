package view.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("Deploy2CustomValidator")
public class Deploy2CustomValidator implements Validator {

	@Override
	public void validate(FacesContext context, UIComponent component, Object value) {
		if (value != null && value.toString().length() < 5) {
			throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "O valor deve ter pelo menos 5 caracteres!", null));
		}
	}

}
