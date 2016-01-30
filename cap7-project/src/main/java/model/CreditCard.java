package model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreditCard implements Serializable {

	private static final long serialVersionUID = 7008510481522027982L;

	private Long number;

}
