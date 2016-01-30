package compositecomponents;

import java.io.Serializable;
import java.time.LocalDate;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import lombok.Data;

@Data
@ManagedBean
@ViewScoped
public class TestCompositeComponentsMB implements Serializable {

	private static final long serialVersionUID = 2601481024272607199L;

	private String user;
	private String pass;
	private LocalDate localDate = LocalDate.now();

	public void test() {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Test Composite Component"));
	}

}
