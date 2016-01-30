package br.com.components.text;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import javax.el.ValueExpression;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.component.behavior.ClientBehavior;
import javax.faces.component.behavior.ClientBehaviorContext;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.render.FacesRenderer;
import javax.faces.render.RenderKit;
import javax.faces.render.Renderer;

import br.com.components.text.UIInputText.PropertyKeys;

/**
 * A anotacao @FacesRenderer informa para o JSF que esta classe é um
 * renderizador. O valor dos atributos sao utilizados para registrar esse
 * renderizador com o metodo
 * {@link RenderKit#addRenderer(String, String, Renderer)
 * RenderKit.addRenderer(String
 * family, String rendererType,
 * Renderer renderer)} e podem ser utilizados para criar instancias desse
 * renderizador
 * atraves do metodo {@link RenderKit#getRenderer(String, String)
 * RenderKit.getRenderer(String family, String rendererType)}.
 * 
 * @author Matheus
 *
 */
@FacesRenderer(rendererType = InputTextRenderer.RENDERER_TYPE, componentFamily = UIInput.COMPONENT_FAMILY)
public class InputTextRenderer extends Renderer {

	/*
	 * Os componentes e os renderizadores do JSF possuem namespaces diferentes,
	 * deste modo, é permitido especificar o mesmo valor para component-type e
	 * renderer-type.
	 */
	public static final String RENDERER_TYPE = "br.com.components.text.InputText";

	@Override
	public void encodeBegin(FacesContext context, UIComponent component) throws IOException {
		ResponseWriter writer = context.getResponseWriter();
		UIInputText inputText = (UIInputText) component;

		// Teste de salvamento de estado
		inputText.setTeste("Valor de teste " + inputText.getClientId());

		this.encodeLabelFacet(context, writer, inputText);

		writer.startElement("input", inputText);

		this.encodeDefaultAttributes(writer, inputText);

		String clientId = inputText.getClientId();
		writer.writeAttribute("id", clientId, null);
		writer.writeAttribute("name", clientId, null);

		String script = this.getValueChangeScript(context, inputText);
		if (!script.isEmpty()) {
			writer.writeAttribute("onchange", script, null);
		}

		Object value = inputText.getValue();
		if (value != null) {
			writer.writeAttribute("value", value, null);
		}

		writer.endElement("input");
	}

	private void encodeLabelFacet(FacesContext context, ResponseWriter writer, UIInputText inputText) throws IOException {
		UIComponent labelFacet = inputText.getFacet("label");
		if (labelFacet != null) {
			writer.startElement("label", null);
			writer.writeAttribute("for", inputText.getClientId(), null);
			labelFacet.encodeAll(context);
			writer.endElement("label");
		}
	}

	private String getValueChangeScript(FacesContext context, UIInputText inputText) {
		String onchange = inputText.getOnchange() != null ? inputText.getOnchange() + ";" : "";
		String script = null;

		Map<String, List<ClientBehavior>> behaviors = inputText.getClientBehaviors();
		String defaultEvent = inputText.getDefaultEventName();

		if (behaviors.containsKey(defaultEvent)) {
			ClientBehaviorContext behaviorContext = ClientBehaviorContext.createClientBehaviorContext(context, inputText, defaultEvent, inputText.getClientId(), null);
			ClientBehavior clientBehavior = behaviors.get(defaultEvent).get(0);
			script = clientBehavior.getScript(behaviorContext);
		}

		if (script == null) {
			script = "";
		}

		return onchange + script;
	}

	@Override
	public void decode(FacesContext context, UIComponent component) {
		UIInputText inputText = (UIInputText) component;
		String clientId = inputText.getClientId();

		// Teste de salvamento de estado
		System.out.println(inputText.getTeste());

		Map<String, String> requestMap = context.getExternalContext().getRequestParameterMap();
		if (!requestMap.containsKey(clientId)) {
			return;
		}

		String submittedValue = requestMap.get(clientId);
		inputText.setSubmittedValue(submittedValue);

		this.decodeBehaviors(context, inputText, requestMap);
	}

	/**
	 * É preciso decodificar os comportamentos ajax para caso o usuario tenha
	 * adicionado algum metodo no atributo listener. Deste modo, na
	 * decodificacao do comportamento ajax, um evento sera enfileirado.
	 * 
	 * @param context
	 * @param inputText
	 * @param requestMap
	 */
	private void decodeBehaviors(FacesContext context, UIInputText inputText, Map<String, String> requestMap) {
		Map<String, List<ClientBehavior>> behaviors = inputText.getClientBehaviors();

		if (!behaviors.isEmpty() && requestMap.containsKey("javax.faces.behavior.event")) {
			String behaviorEvent = requestMap.get("javax.faces.behavior.event");
			String behaviorSource = requestMap.get("javax.faces.source");

			if (behaviors.containsKey(behaviorEvent) && inputText.getClientId().equals(behaviorSource)) {
				List<ClientBehavior> clientBehaviors = behaviors.get(behaviorEvent);
				clientBehaviors.forEach(clientBehavior -> clientBehavior.decode(context, inputText));
			}
		}
	}

	@Override
	public Object getConvertedValue(FacesContext context, UIComponent component, Object submittedValue) throws ConverterException {
		UIInputText inputText = (UIInputText) component;
		ValueExpression valueExpression = inputText.getValueExpression("value");
		Object value = submittedValue;
		Converter converter = null;

		if (submittedValue != null) {
			converter = inputText.getConverter();

			if (converter != null) {
				value = converter.getAsObject(context, component, (String) submittedValue);

			} else if (valueExpression != null) {
				converter = context.getApplication().createConverter(valueExpression.getType(context.getELContext()));

				if (converter != null) {
					value = converter.getAsObject(context, component, (String) submittedValue);
				}
			}
		}

		return value;
	}

	private void encodeDefaultAttributes(ResponseWriter writer, UIInputText inputText) {
		PropertyKeys[] keys = UIInputText.PropertyKeys.values();
		String methodName = null;
		String keyName = null;
		Object value = null;

		for (PropertyKeys key : keys) {
			keyName = key.name();
			methodName = "get" + keyName.replaceFirst(String.valueOf(keyName.charAt(0)), String.valueOf(keyName.charAt(0)).toUpperCase());
			try {
				Method method = inputText.getClass().getDeclaredMethod(methodName, new Class[0]);
				value = method.invoke(inputText);
				if (value != null && !PropertyKeys.onchange.equals(key)) {
					writer.writeAttribute(key.name(), value, null);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
