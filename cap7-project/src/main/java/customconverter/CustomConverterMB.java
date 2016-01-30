package customconverter;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import lombok.Data;
import model.CreditCard;

@ManagedBean
@ViewScoped
@Data
public class CustomConverterMB implements Serializable {

	private static final long serialVersionUID = 8992497883781778267L;

	private CreditCard creditCard;

}
