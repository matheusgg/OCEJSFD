package br.com.components.submit;

import java.io.IOException;
import java.util.Map;

import javax.el.MethodExpression;
import javax.faces.application.ResourceDependencies;
import javax.faces.application.ResourceDependency;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.event.ActionEvent;
import javax.faces.event.ActionListener;
import javax.faces.event.FacesEvent;
import javax.faces.event.MethodExpressionActionListener;
import javax.faces.event.PhaseId;
import javax.faces.render.FacesRenderer;
import javax.faces.render.Renderer;

/**
 * A anotacao @ResourceDependency serve para adicionar dependencias (arquivos
 * js, css, etc.) nas paginas que os componentes renderizados por este
 * renderizador forem utilizados. Ja a anotacao @ResourceDependencies serve para
 * agrupar varias anotacoes @ResourceDependency. Tanto @ResourceDependencies
 * quanto @ResourceDependency podem ser aplicadas a classes de componentes
 * (subtipos de UIComponent) ou renderizadores (subtipos de Renderer). Os
 * valores suportados pelo atributo target sao os mesmos suportados pelo
 * componente h:outputScript, ou seja, head, body e form, sendo head o valor
 * padrao caso nada seja especificado.
 * 
 * @author Matheus
 *
 */
@FacesRenderer(componentFamily = "javax.faces.Command", rendererType = "br.com.components.submit.SubmitButton")
@ResourceDependencies({ @ResourceDependency(name = "bootstrap-min.css", library = "css", target = "head"),
		@ResourceDependency(name = "bootstrap.min.js", library = "js", target = "body") })
public class SubmitButtonRenderer extends Renderer {

	private MethodExpressionActionListener listener;

	@Override
	public void encodeBegin(FacesContext context, UIComponent component) throws IOException {
		ResponseWriter writer = context.getResponseWriter();
		UISubmitButton submitButton = (UISubmitButton) component;

		if (this.listener != null) {
			submitButton.removeActionListener(this.listener);
		}

		String clientId = submitButton.getClientId();
		Object value = submitButton.getValue();

		writer.startElement("input", submitButton);
		writer.writeAttribute("type", "submit", null);
		writer.writeAttribute("id", clientId, null);
		writer.writeAttribute("name", clientId, null);
		writer.writeAttribute("class", "btn btn-primary", null);

		if (value != null) {
			writer.writeAttribute("value", value, null);
		}

		writer.endElement("input");
	}

	@Override
	public void decode(FacesContext context, UIComponent component) {
		UISubmitButton submitButton = (UISubmitButton) component;
		Map<String, String> requestMap = context.getExternalContext().getRequestParameterMap();

		if (requestMap.containsKey(submitButton.getClientId())) {
			MethodExpression methodExpression = submitButton.getActionExpression();
			ActionListener[] listeners = submitButton.getActionListeners();

			if (methodExpression != null || (listeners != null && listeners.length > 0)) {
				FacesEvent event = new ActionEvent(component);
				if (submitButton.isImmediate()) {
					event.setPhaseId(PhaseId.APPLY_REQUEST_VALUES);
				} else {
					event.setPhaseId(PhaseId.INVOKE_APPLICATION);
				}
				event.queue();
			}

			MethodExpression methodToInvoke = submitButton.getMethodToInvoke();
			if (methodExpression != null) {
				this.listener = new MethodExpressionActionListener(methodToInvoke);
				submitButton.addActionListener(this.listener);
			}
		}
	}

}
