package datatable.datamodel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.ArrayDataModel;
import javax.faces.model.ListDataModel;

import lombok.Data;
import datatable.model.Nome;

@ManagedBean
@ViewScoped
@Data
public class DataModelMB implements Serializable {

	private static final long serialVersionUID = 4632881266930219559L;

	private ArrayDataModel<Nome> arrayDataModel;
	private ListDataModel<Nome> listDataModel;
	private SortDataModel<Nome> sortDataModel;

	@PostConstruct
	void init() {
		Nome[] nomes = new Nome[] { new Nome("Primeiro Nome 4", "Sobrenome 4"), new Nome("Primeiro Nome 1", "Sobrenome 1"), new Nome("Primeiro Nome 3", "Sobrenome 3"),
				new Nome("Primeiro Nome 2", "Sobrenome 2") };

		this.arrayDataModel = new ArrayDataModel<>(nomes);
		this.listDataModel = new ListDataModel<>(new ArrayList<>(Arrays.asList(nomes)));
		this.sortDataModel = new SortDataModel<>(this.arrayDataModel);
	}

	/**
	 * Na fase APPLY_REQUEST_VALUES, para cada iteracao de item, a DataTable
	 * (UIData) decodifica todos os seus componentes filhos, desta forma, caso
	 * algum botao seja clicado, quando o mesmo for decodificado, ele gerara um
	 * ActionEvent que sera enfileirado para ser invocado na fase
	 * INVOKE_APPLICATION. Quando este evento é enfileirado pelo botao ou link,
	 * os componentes ancestrais desse ActionSource2 também enfileram este
	 * evento. Quando chega a vez de UIData enfileirar este ActionEvent, ela
	 * cria um WrapperEvent com o indice da iteracao atual e o ActionEvent
	 * disparado. Quando a fase INVOKE_APPLICATION é alcancada, o metodo
	 * broadcast de UIData é chamado, e ele recupera o ActionEvent encapsulado
	 * por WrapperEvent junto com o indice salvo anteriormente. Depois disso, o
	 * método setRowIndex(int) da DataModel é invocado recebendo o indice salvo
	 * por WrapperEvent. Assim que o indice é atualizado, é feito o broadcast do
	 * ActionEvent original para que o action do botao ou link seja invocado,
	 * por este motivo, quando o método getRowData() é chamado, o indice ja
	 * esta atualizado com a linha do botao ou link que foi clicado.
	 */
	public void removeItem() {
		Nome nome = this.listDataModel.getRowData();
		((List<?>) this.listDataModel.getWrappedData()).remove(nome);
	}

	public void sortBy(String arg) {
		if ("first".equals(arg)) {
			this.sortDataModel.sortBy(new Comparator<Nome>() {

				@Override
				public int compare(Nome o1, Nome o2) {
					return o1.getFirst().compareTo(o2.getFirst());
				}
			});
		} else {
			this.sortDataModel.sortBy(new Comparator<Nome>() {

				@Override
				public int compare(Nome o1, Nome o2) {
					return o1.getLast().compareTo(o2.getLast());
				}
			});
		}
	}

}
