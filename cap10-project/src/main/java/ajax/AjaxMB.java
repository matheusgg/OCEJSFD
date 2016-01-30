package ajax;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.ValueChangeEvent;

@ManagedBean
@ViewScoped
public class AjaxMB implements Serializable {

	private static final long serialVersionUID = -2082202010267910252L;

	public void show(boolean error) {
		if (error) {
			throw new RuntimeException("Erro de Teste");
		}
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage("Show " + context.getCurrentPhaseId()));
	}

	public void ajaxAction(AjaxBehaviorEvent event) {
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage(event.toString() + context.getCurrentPhaseId()));
	}

	public void beforeAction(ActionEvent event) {
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage(event.toString() + context.getCurrentPhaseId()));
	}

	public void beforeChange(ValueChangeEvent event) {
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage(event.toString() + context.getCurrentPhaseId()));
	}

}
