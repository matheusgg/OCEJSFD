package datatable.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Nome implements Serializable {

	private static final long serialVersionUID = 8061296585312529488L;

	private String first;
	private String last;

}
