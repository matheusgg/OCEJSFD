package br.com.components.submit;

import java.util.Map;

import javax.el.MethodExpression;
import javax.faces.application.Application;
import javax.faces.application.ResourceHandler;
import javax.faces.component.FacesComponent;
import javax.faces.component.UICommand;
import javax.faces.component.UIOutput;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;

@FacesComponent("br.com.components.submit.SubmitButton")
public class UISubmitButton extends UICommand {

	private static final String ADD_JQUERY_INIT_PARAM_KEY = "br.com.components.submit.SubmitButton.ADD_JQUERY";
	private static final String JQUERY_SCRIPT_FILE_NAME = "jquery-1.11.3.min.js";

	public UISubmitButton() {
		super.setRendererType("br.com.components.submit.SubmitButton");
		this.addScriptResource();
	}

	/**
	 * Renderizacao condicional de recursos (js ou css). Este metodo deve ser
	 * chamado antes da renderizacao do componente, caso contrario, a adicao de
	 * recursos no facet head de UIViewRoot nao tera efeito, pois no momento que
	 * este componente é renderizado, todos os recursos ja foram escritos na
	 * resposta. Este método segue o algoritmo descrito na anotacao
	 * ResourceDependency.
	 */
	private void addScriptResource() {
		FacesContext context = FacesContext.getCurrentInstance();
		Application application = context.getApplication();
		ResourceHandler resourceHandler = application.getResourceHandler();
		String param = context.getExternalContext().getInitParameter(ADD_JQUERY_INIT_PARAM_KEY);

		if (Boolean.parseBoolean(param) && resourceHandler.libraryExists("js")) {
			UIOutput componentResource = (UIOutput) application.createComponent(UIOutput.COMPONENT_TYPE);
			componentResource.setRendererType(resourceHandler.getRendererTypeForResourceName(JQUERY_SCRIPT_FILE_NAME));

			Map<String, Object> attrs = componentResource.getAttributes();
			attrs.put("name", JQUERY_SCRIPT_FILE_NAME);
			attrs.put("library", "js");
			attrs.put("target", "head");

			UIViewRoot viewRoot = context.getViewRoot();
			viewRoot.addComponentResource(context, componentResource);
		}
	}

	public void setMethodToInvoke(MethodExpression methodExpression) {
		super.getStateHelper().put("methodToInvoke", methodExpression);
	}

	public MethodExpression getMethodToInvoke() {
		return (MethodExpression) super.getStateHelper().eval("methodToInvoke");
	}

}
