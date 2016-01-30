package componentes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.html.HtmlOutputText;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import javax.faces.model.SelectItemGroup;

@ManagedBean
@ViewScoped
public class ComponentesMB implements Serializable {

	private static final long serialVersionUID = 9129717177481737211L;

	private HtmlOutputText htmlOutputText;
	private String htmlDiv;
	private String outcome;
	private boolean selected;
	private String itemSelecionado;
	private SelectItem selectItemOption;
	private SelectItem selectItemSelecionado;
	private List<String> itensSelecionados;
	private String[] itens;
	private List<SelectItem> selectItems;
	private Map<Integer, Object> mapaItens;
	private List<SelectItemGroup> grupos;

	@PostConstruct
	public void init() {
		this.htmlDiv = "<div>Teste</div>";

		// this.selectItemOption = new SelectItem();
		// Value
		// this.selectItemOption = new SelectItem("10");
		// Value, Label
		// this.selectItemOption = new SelectItem("10", "Label");
		// Value, Label, Description
		// this.selectItemOption = new SelectItem("10", "Label", "Descricao");
		// Value, Label, Description, Disabled
		// this.selectItemOption = new SelectItem("10", "Label", "Descricao",
		// false);
		// Value, Label, Description, Disabled, Escape
		// this.selectItemOption = new SelectItem("10", "Label", "Descricao",
		// false, false);
		// Value, Label, Description, Disabled, Escape, NoSelectionOption
		this.selectItemOption = new SelectItem("10", "Label do SelectItem", "Descricao", false, false, false);

		this.selectItems = new ArrayList<>();
		/*
		 * Se um SelectItem for especificado sem valor ou label, a String null
		 * será renderizada.
		 */
		this.selectItems.add(new SelectItem());
		for (int i = 0; i < 10; i++) {
			this.selectItems.add(new SelectItem(i, "Item " + i));
		}

		this.mapaItens = new HashMap<>();
		for (int i = 0; i < 5; i++) {
			this.mapaItens.put(i, "Item do mapa " + i);
		}

		this.grupos = new ArrayList<>();
		/*
		 * Caso um SelectItemGroup seja especificado sem elementos SelectItem,
		 * ocorrerá um NullPointerException no momento da renderizacao da
		 * pagina.
		 */
		// this.grupos.add(new SelectItemGroup("Teste"));
		for (int i = 0; i < 5; i++) {
			this.grupos.add(new SelectItemGroup("Grupo " + i, "Descricao " + i, false,
					new SelectItem[] { new SelectItem("10", "Item 1"), new SelectItem("20", "Item 2") }));
		}
	}

	public void testFind(ActionEvent event) {
		UIComponent component = event.getComponent();

		/*
		 * O método findComponent inicia a busca a partir do primeiro
		 * NamingContainer encontrado caso a expressao informada nao seja
		 * absoluta (comeca com :). Primeiro é verificado se o componente onde
		 * esta sendo feita a busca é um NamingContainer, caso positivo, ele é
		 * utilizado como componente base para realizar a busca. Caso o
		 * componente nao seja um NamingContainer, os componentes pais sao
		 * analisado até que seja encontrado um NamingContainer. Geralmente esta
		 * busca resulta no formulario onde o componente que chamou o método
		 * findComponent está contido. Ou seja, sempre que o método
		 * findComponent é chamado com uma expressao que nao é absoluta, o
		 * formulário onde está contido o componente que chamou o método é
		 * utilizado como base para a pesquisa. Por este motivo, se o componente
		 * buscado nao estiver contido no mesmo formulario, ele nao sera
		 * encontrado. Para encontrar componentes de outros formularios, é
		 * necessário especificar uma expressao absoluta (comecando com :)
		 * seguido pelo id do formulario que servira de base para a pesquisa.
		 */
		UIComponent foundComponent = component.findComponent("txtFind");
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(foundComponent.getClientId()));
	}

	public void show() {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Mensagem de Teste"));

		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sumario", "Detalhamento"));
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Sumario", "Detalhamento"));
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Sumario", "Detalhamento"));
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Sumario", "Detalhamento"));
	}

	/**
	 * Quando é feito um binding, caso o método get retorne null, o método set é
	 * chamado passando componente criado da arvore de visualizacao.
	 * 
	 * @return the htmlOutputText
	 */
	public HtmlOutputText getHtmlOutputText() {
		return htmlOutputText;
	}

	/**
	 * @param htmlOutputText
	 *            the htmlOutputText to set
	 */
	public void setHtmlOutputText(HtmlOutputText htmlOutputText) {
		this.htmlOutputText = htmlOutputText;
		this.htmlOutputText.setValue("Teste Binding dinâmico");
	}

	/**
	 * @return the htmlDiv
	 */
	public String getHtmlDiv() {
		return htmlDiv;
	}

	/**
	 * @param htmlDiv
	 *            the htmlDiv to set
	 */
	public void setHtmlDiv(String htmlDiv) {
		this.htmlDiv = htmlDiv;
	}

	/**
	 * @return the outcome
	 */
	public String getOutcome() {
		return outcome;
	}

	/**
	 * @param outcome
	 *            the outcome to set
	 */
	public void setOutcome(String outcome) {
		this.outcome = outcome;
	}

	/**
	 * @return the selected
	 */
	public boolean isSelected() {
		return selected;
	}

	/**
	 * @param selected
	 *            the selected to set
	 */
	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	/**
	 * @return the itemSelecionado
	 */
	public String getItemSelecionado() {
		return itemSelecionado;
	}

	/**
	 * @param itemSelecionado
	 *            the itemSelecionado to set
	 */
	public void setItemSelecionado(String itemSelecionado) {
		this.itemSelecionado = itemSelecionado;
	}

	/**
	 * @return the itensSelecionados
	 */
	public List<String> getItensSelecionados() {
		return itensSelecionados;
	}

	/**
	 * @param itensSelecionados
	 *            the itensSelecionados to set
	 */
	public void setItensSelecionados(List<String> itensSelecionados) {
		this.itensSelecionados = itensSelecionados;
	}

	/**
	 * @return the itens
	 */
	public String[] getItens() {
		return itens;
	}

	/**
	 * @param itens
	 *            the itens to set
	 */
	public void setItens(String[] itens) {
		this.itens = itens;
	}

	/**
	 * @return the selectItemOption
	 */
	public SelectItem getSelectItemOption() {
		return selectItemOption;
	}

	/**
	 * @param selectItemOption
	 *            the selectItemOption to set
	 */
	public void setSelectItemOption(SelectItem selectItemOption) {
		this.selectItemOption = selectItemOption;
	}

	/**
	 * @return the selectItemSelecionado
	 */
	public SelectItem getSelectItemSelecionado() {
		return selectItemSelecionado;
	}

	/**
	 * @param selectItemSelecionado
	 *            the selectItemSelecionado to set
	 */
	public void setSelectItemSelecionado(SelectItem selectItemSelecionado) {
		this.selectItemSelecionado = selectItemSelecionado;
	}

	/**
	 * @return the selectItems
	 */
	public List<SelectItem> getSelectItems() {
		return selectItems;
	}

	/**
	 * @param selectItems
	 *            the selectItems to set
	 */
	public void setSelectItems(List<SelectItem> selectItems) {
		this.selectItems = selectItems;
	}

	/**
	 * @return the mapaItens
	 */
	public Map<Integer, Object> getMapaItens() {
		return mapaItens;
	}

	/**
	 * @param mapaItens
	 *            the mapaItens to set
	 */
	public void setMapaItens(Map<Integer, Object> mapaItens) {
		this.mapaItens = mapaItens;
	}

	/**
	 * @return the grupos
	 */
	public List<SelectItemGroup> getGrupos() {
		return grupos;
	}

	/**
	 * @param grupos
	 *            the grupos to set
	 */
	public void setGrupos(List<SelectItemGroup> grupos) {
		this.grupos = grupos;
	}

}
