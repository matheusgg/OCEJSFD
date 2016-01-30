package datatable;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.sql.rowset.CachedRowSet;

import lombok.Data;
import datatable.model.Nome;

@ManagedBean
@ViewScoped
@Data
public class DataTableMB implements Serializable {

	private static final long serialVersionUID = 2155446126762406682L;

	private Nome[] nomes;
	private Nome nome;
	// private ResultSet resultSet;
	private CachedRowSet cachedRowSet;

	@PostConstruct
	void init() {
		this.nomes = new Nome[] { new Nome("Primeiro Nome 1", "Sobrenome 1"), new Nome("Primeiro Nome 2", "Sobrenome 2"), new Nome("Primeiro Nome 3", "Sobrenome 3"),
				new Nome("Primeiro Nome 4", "Sobrenome 4") };

		this.nome = new Nome("Matheus", "Gomes Góes");

		// try {
		// Class.forName("com.mysql.jdbc.Driver");
		// Connection con =
		// DriverManager.getConnection("jdbc:mysql://localhost:3306/app_db",
		// "root", "root");
		// Statement stm = con.createStatement(ResultSet.TYPE_FORWARD_ONLY,
		// ResultSet.CONCUR_UPDATABLE);
		// this.resultSet = stm.executeQuery("select * from Cliente");
		// } catch (Exception e) {
		// e.printStackTrace();
		// }

		try {
			Class.forName("com.mysql.jdbc.Driver");
			this.prepareRowSet();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * Caso um ResultSet seja utilizado como value de uma DataTable, uma conexao
	 * com a base de dados deve permanecer aberta, consumindo assim, recursos
	 * desnecessarios. Para resolver este problema, um CachedRowSet pode ser
	 * utilizado. Essa classe tem como finalidade cachear os dados de um
	 * ResultSet e trabalhar com esses dados mesmo se a conexao com o banco
	 * estiver fechada. Deste modo, é possivel utilizar um
	 * CacherRowSet, que também é um ResultSet, como value de uma DataTable e
	 * fechar os objetos Connection, Statement e ResultSet abertos para realizar
	 * a consulta na base.
	 */
	@SuppressWarnings("restriction")
	private void prepareRowSet() {
		try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/app_db", "root", "root");
				Statement stm = con.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
				ResultSet resultSet = stm.executeQuery("select * from Cliente")) {

			this.cachedRowSet = new com.sun.rowset.CachedRowSetImpl();
			this.cachedRowSet.populate(resultSet);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void selectRow(Map<String, Object> row) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, row.toString(), row.toString()));
	}

}
