package view;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import lombok.Data;

@Data
@Named
@RequestScoped
public class CdiMB implements Serializable {

	private static final long serialVersionUID = 282665295955343314L;

	private String text;

}
