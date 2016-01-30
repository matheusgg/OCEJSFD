package compositecomponents.backingcomponents;

import java.io.IOException;
import java.text.DateFormatSymbols;
import java.time.LocalDate;

import javax.faces.application.FacesMessage;
import javax.faces.component.FacesComponent;
import javax.faces.component.NamingContainer;
import javax.faces.component.UIInput;
import javax.faces.component.UISelectItem;
import javax.faces.component.UIViewRoot;
import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.context.FacesContext;
import javax.faces.convert.ConverterException;

/**
 * É possivel associar uma instancia que servira como backing component para um
 * componente composto. Este objeto deve implementar a interface NamingContainer
 * e seu método getFamily() deve retornar javax.faces.NamingContainer.
 * 
 * @author Matheus
 *
 */
@FacesComponent(BackingDate.COMPONENT_TYPE)
public class BackingDate extends UIInput implements NamingContainer {

	public static final String COMPONENT_TYPE = "compositecomponents.backingcomponents.BackingDate";
	public static final String COMPONENT_FAMILY = "javax.faces.NamingContainer";

	/**
	 * Carrega os combos de dias, meses e anos antes de renderizar a pagina.
	 * 
	 * @see javax.faces.component.UIComponentBase#encodeBegin(javax.faces.context.FacesContext)
	 */
	@Override
	public void encodeBegin(FacesContext context) throws IOException {
		this.populateFields(context);
		super.encodeBegin(context);
	}

	/**
	 * É preciso retornar um valor no método getSubmittedValue() de um
	 * BackingComponent relacionado a um componente composto. Sem isso, o método
	 * getConvertedValue() nao será chamado.
	 * 
	 * @see javax.faces.component.UIInput#getSubmittedValue()
	 */
	@Override
	public Object getSubmittedValue() {
		return this;
	}

	/**
	 * Recupera os valores dos combos e retorna um objeto LocalDate com as
	 * informacoes inseridas pelo usuario.
	 * 
	 * @see javax.faces.component.UIInput#getConvertedValue(javax.faces.context.FacesContext,
	 *      java.lang.Object)
	 */
	@Override
	public Object getConvertedValue(FacesContext context, Object newSubmittedValue) throws ConverterException {
		HtmlSelectOneMenu days = (HtmlSelectOneMenu) super.findComponent("days");
		HtmlSelectOneMenu months = (HtmlSelectOneMenu) super.findComponent("months");
		HtmlSelectOneMenu years = (HtmlSelectOneMenu) super.findComponent("years");

		/*
		 * É preciso recuperar o valor submitado, pois como este metodo é
		 * chamado na fase PROCCESS_VALIDATION, as conversoes e as validacoes
		 * dos valores dos combos ainda nao aconteceram. Desta forma, a chamada
		 * ao metodo getValue() retornaria o valor antido dos componentes e nao
		 * o valor real informado pelo usuario.
		 */
		Integer day = Integer.valueOf(days.getSubmittedValue().toString());
		Integer month = Integer.valueOf(months.getSubmittedValue().toString());
		Integer year = Integer.valueOf(years.getSubmittedValue().toString());

		try {
			return LocalDate.of(year, month, day);
		} catch (Exception e) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Data inválida!", "Data inválida!");
			throw new ConverterException(message, e);
		}
	}

	private void populateFields(FacesContext context) {
		UIViewRoot viewRoot = context.getViewRoot();
		UISelectItem item = null;

		// Days
		HtmlSelectOneMenu days = (HtmlSelectOneMenu) super.findComponent("days");
		for (int i = 0; i < 31; i++) {
			item = new UISelectItem();
			item.setItemLabel(String.valueOf(i + 1));
			item.setItemValue(i + 1);
			days.getChildren().add(item);
		}

		// Months
		HtmlSelectOneMenu months = (HtmlSelectOneMenu) super.findComponent("months");
		String[] arrayMonths = new DateFormatSymbols(viewRoot.getLocale()).getMonths();
		for (int i = 0; i < 12; i++) {
			item = new UISelectItem();
			item.setItemLabel(arrayMonths[i]);
			item.setItemValue(i + 1);
			months.getChildren().add(item);
		}

		// Years
		HtmlSelectOneMenu years = (HtmlSelectOneMenu) super.findComponent("years");
		for (int i = 1900; i < 2900; i++) {
			item = new UISelectItem();
			item.setItemLabel(String.valueOf(i));
			item.setItemValue(i);
			years.getChildren().add(item);
		}

		// Definindo os valores
		LocalDate value = (LocalDate) super.getValue();
		if (value != null) {
			days.setValue(value.getDayOfMonth());
			months.setValue(value.getMonthValue());
			years.setValue(value.getYear());
		}
	}

	@Override
	public String getFamily() {
		return COMPONENT_FAMILY;
	}

}
