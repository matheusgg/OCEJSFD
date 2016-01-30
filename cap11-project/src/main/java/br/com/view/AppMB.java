package br.com.view;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.ValueChangeEvent;

import lombok.Data;

@Data
@ManagedBean
@ViewScoped
public class AppMB implements Serializable {

	private static final long serialVersionUID = 6535689338631806982L;

	private String text;

	private Integer number;

	public void change(ValueChangeEvent event) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Chage - " + event));
	}

	public void action() {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Action " + FacesContext.getCurrentInstance().getCurrentPhaseId()));
	}

	public void actionListener(ActionEvent event) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("ActionListener " + FacesContext.getCurrentInstance().getCurrentPhaseId()));
	}

	public void method() {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Method " + FacesContext.getCurrentInstance().getCurrentPhaseId()));
	}

	public void ajaxTest(AjaxBehaviorEvent event) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(event + " - " + FacesContext.getCurrentInstance().getCurrentPhaseId()));
	}
}
