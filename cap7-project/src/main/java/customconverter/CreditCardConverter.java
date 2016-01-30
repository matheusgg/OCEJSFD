package customconverter;

import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import model.CreditCard;

/**
 * A anotacao FacesConverter especifica para a implementacao JSF registrar este
 * conversor no momento da inicializacao da apliacacao. Caso o atributo value
 * seja especificado, a implementacao JSF deve chamar o método
 * FacesContext.getCurrentInstace().getApplication().addConverter(String,
 * String) passando o value como primeiro argumento e o nome totalmente
 * qualificado da classe do conversor como segundo argumento para registrar esse
 * conversor. Caso o atributo value nao seja definido, o JSF deve chamar o
 * método FacesContext.getCurrentInstace().getApplication().addConverter(Class,
 * String) passando o valor do atributo forClass como primeiro argumento e o
 * nome totalmente qualificado da classe do conversor como segundo argumento
 * para registrar esse conversor. Por padrao, o valor do atributo forClass é
 * java.lang.Object.class. O atributo value é utilizado como o identificador do
 * converter (converterId).
 * 
 * @author Matheus
 *
 */
@FacesConverter(forClass = CreditCard.class)
public class CreditCardConverter implements Converter {

	private static final String PATTERN_KEY = "customconverter.CreditCardConverter_pattern";
	private static final String DEFAULT_PATTERN = "\\D";

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if (value != null) {
			// value = value.replaceAll("\\D", "");
			String pattern = this.getPattern(component);

			if (Pattern.compile(pattern).matcher(value).find()) {
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid CreditCard number!", "CreditCard number can only have numbers.");

				/*
				 * javax.faces.convert.ConverterException possui 6 construtores.
				 * Apenas os construtores que recebem um FacesMessage exibem a
				 * mensagem para o usuário.
				 */
				// throw new ConverterException();
				throw new ConverterException(message);
				// throw new ConverterException("Invalid CreditCard number!");
				// throw new ConverterException(new IllegalArgumentException());
				// throw new ConverterException(message, new
				// IllegalArgumentException());
				// throw new ConverterException("Invalid CreditCard number!",
				// new IllegalArgumentException());
			}

			return new CreditCard(Long.valueOf(value));
		}
		return value;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			CreditCard creditCard = (CreditCard) value;
			Long number = creditCard.getNumber();
			if (number != null) {
				return number.toString();
			}
		}
		return null;
	}

	private String getPattern(UIComponent component) {
		String pattern = (String) component.getAttributes().get(PATTERN_KEY);
		if (pattern == null) {
			pattern = DEFAULT_PATTERN;
		}
		return pattern;
	}
}
