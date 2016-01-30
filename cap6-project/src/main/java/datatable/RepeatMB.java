package datatable;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import lombok.Data;
import datatable.model.Nome;

@ManagedBean
@ViewScoped
@Data
public class RepeatMB implements Serializable {

	private static final long serialVersionUID = -2865824802170933287L;

	private Nome[] nomes;
	private Nome nome;
	private String teste;
	private List<Nome> nomesList;

	// private ResultSet resultSet;

	@PostConstruct
	void init() {
		this.nomes = new Nome[] { new Nome("Primeiro Nome 1", "Sobrenome 1"), new Nome("Primeiro Nome 2", "Sobrenome 2"), new Nome("Primeiro Nome 3", "Sobrenome 3"),
				new Nome("Primeiro Nome 4", "Sobrenome 4"), new Nome("Primeiro Nome 5", "Sobrenome 5"), new Nome("Primeiro Nome 6", "Sobrenome 6"),
				new Nome("Primeiro Nome 7", "Sobrenome 7"), new Nome("Primeiro Nome 8", "Sobrenome 8") };

		this.nome = new Nome("Matheus", "Gomes Góes");

		this.teste = "Teste";

		this.nomesList = Arrays.asList(this.nomes);

		// this.resultSet = this.findClientes();
	}

	// private ResultSet findClientes() {
	// try {
	// Class.forName("com.mysql.jdbc.Driver");
	// Connection con =
	// DriverManager.getConnection("jdbc:mysql://localhost:3306/app_db", "root",
	// "root");
	// PreparedStatement stmt = con.prepareStatement("SELECT * FROM Cliente;");
	// return stmt.executeQuery();
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// return null;
	// }
}
