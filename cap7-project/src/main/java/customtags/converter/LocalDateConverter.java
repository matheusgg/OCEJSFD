package customtags.converter;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import lombok.Data;

@FacesConverter("customtags.converter.LocalDateConverter")
@Data
public class LocalDateConverter implements Converter, Serializable {

	private static final long serialVersionUID = 6552872245340423476L;
	private static final String DEFAULT_SUMMARY_MESSAGE = "Não foi possível converter o valor informado para uma data válida.";
	private static final String DEFAULT_DETAIL_MESSAGE = "Por favor, informe um valor válido.";
	private String pattern;
	private String errorSummaryMessage;
	private String errorDetailMessage;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if (value == null) {
			return value;
		}

		this.validatePattern();

		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern(this.pattern);
			return LocalDate.parse(value, formatter);
		} catch (DateTimeParseException e) {
			throw this.createConverterException(e);
		}
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (!(value instanceof LocalDate)) {
			return null;
		}

		this.validatePattern();

		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern(this.pattern);
			LocalDate localDate = (LocalDate) value;
			return localDate.format(formatter);
		} catch (DateTimeParseException e) {
			throw this.createConverterException(e);
		}
	}

	private void validatePattern() {
		if (this.pattern == null) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Pattern inválido!", "O atributo pattern deve ser definido.");
			throw new ConverterException(message);
		}
	}

	private ConverterException createConverterException(DateTimeParseException exception) {
		FacesMessage message = null;

		if (this.errorSummaryMessage == null && this.errorDetailMessage == null) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, DEFAULT_SUMMARY_MESSAGE, DEFAULT_DETAIL_MESSAGE);
		} else {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, this.errorSummaryMessage, this.errorDetailMessage);
		}

		return new ConverterException(message, exception);
	}

}
