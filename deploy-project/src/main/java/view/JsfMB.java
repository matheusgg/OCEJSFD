package view;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import lombok.Data;

@Data
@ManagedBean
@ViewScoped
public class JsfMB implements Serializable {

	private static final long serialVersionUID = 780030470916802405L;

	private String texto;

}
