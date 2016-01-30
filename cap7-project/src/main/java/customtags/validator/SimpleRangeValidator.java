package customtags.validator;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import lombok.Data;

@FacesValidator("customtags.validator.SimpleRangeValidator")
@Data
public class SimpleRangeValidator implements Validator, Serializable {

	private static final long serialVersionUID = -8079053886961454150L;

	private static final String DEFAULT_SUMMARY_MESSAGE = "O valor informado está fora do range especificado.";
	private static final String DEFAULT_DETAIL_MESSAGE = "Por favor, informe um valor válido.";
	private String errorSummaryMessage;
	private String errorDetailMessage;
	private Long max = Long.MAX_VALUE;
	private Long min = Long.MIN_VALUE;

	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		if (value == null) {
			return;
		}

		Long longValue = null;

		try {
			longValue = Long.valueOf(value.toString());
		} catch (NumberFormatException e) {
			throw this.createValidatorException(e);
		}

		if (longValue < this.min || longValue > this.max) {
			throw this.createValidatorException(null);
		}
	}

	private ValidatorException createValidatorException(Exception exception) {
		FacesMessage message = null;

		if (this.errorSummaryMessage == null && this.errorDetailMessage == null) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, DEFAULT_SUMMARY_MESSAGE, DEFAULT_DETAIL_MESSAGE);
		} else {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, this.errorSummaryMessage, this.errorDetailMessage);
		}

		return new ValidatorException(message, exception);
	}

}
