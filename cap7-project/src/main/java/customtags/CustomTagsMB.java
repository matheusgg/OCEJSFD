package customtags;

import java.io.Serializable;
import java.time.LocalDate;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import lombok.Data;

@ManagedBean
@ViewScoped
@Data
public class CustomTagsMB implements Serializable {

	private static final long serialVersionUID = 2697627172145163714L;

	private LocalDate localDate;
	private Integer value;

}
