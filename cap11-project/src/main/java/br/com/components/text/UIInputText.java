package br.com.components.text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import javax.faces.application.Application;
import javax.faces.component.FacesComponent;
import javax.faces.component.UIInput;
import javax.faces.component.behavior.ClientBehaviorHolder;
import javax.faces.context.FacesContext;

/**
 * A annotation @FacesComponent informa para o JSF que esta classe representa um
 * componente, ou seja, ela é responsavel por armazenar o estado do componente.
 * O atributo da anotacao é o component-type que sera utilizado no descritor de
 * biblioteca de tags (Tag Library Descriptor). Quando o JSF identifica essa
 * anotacao em um classe, ele chama o metodo
 * {@link Application#addComponent(String, String)
 * Application.addComponent(String componentType,
 * String componentClass)} para registrar este componente em tempo de execucao.
 * É possivel criar instancias deste componente atraves de um dos metodos
 * {@link Application#createComponent(String) Application.createComponent}.
 * 
 * @author Matheus
 *
 */
@FacesComponent(UIInputText.COMPONENT_TYPE)
public class UIInputText extends UIInput implements ClientBehaviorHolder {

	protected enum PropertyKeys {
		accesskey, alt, autocomplete, dir, disabled, label, lang, maxlength, onblur, onchange, onclick, ondblclick, onfocus, onkeydown, onkeypress, onkeyup, onmousedown, onmousemove, onmouseout, onmouseover, onmouseup, onselect, readonly, size, style, styleClass, tabindex, title;
	}

	public static final String COMPONENT_TYPE = "br.com.components.text.InputText";

	public static final List<String> SUPPORTED_AJAX_EVENTS_NAMES = Arrays.asList("valueChange");

	private String teste;

	public UIInputText() {
		/*
		 * Caso o renderizador nao seja definido, ou seja, o metodo
		 * getRendererType() retornar null, o JSF invocara os metodos
		 * encodeBegin(), getRendersChildren(), encodeChildren() e encodeEnd()
		 * desta classe. Porem, se um renderizador externo for definido como o
		 * exemplo abaixo, o JSF delegara a funcao de renderizacao deste
		 * componente para este renderizador informado. É uma boa pratica
		 * definir o renderizador no construtor do componente.
		 */
		super.setRendererType(InputTextRenderer.RENDERER_TYPE);
	}

	/**
	 * Para prover suporte a tag f:ajax, é necessário implementar a interface
	 * ClientBehaviorHolder. Essa interface possui quatro metodos, porem a
	 * classe UIComponentBase ja possui implementacao para todos eles, apesar de
	 * UIComponentBase nao implementar a interface ClientBehaviorHolder. O
	 * metodo getDefaultEventName() srve para indicar qual é o evento ajax
	 * padrao, ou seja, se usuario anexar a tag f:ajax neste componente e nao
	 * especificar o evento, este metodo sera utilizado para descobrir o evento
	 * padrao.
	 * 
	 * @see javax.faces.component.UIComponentBase#getDefaultEventName()
	 */
	@Override
	public String getDefaultEventName() {
		return SUPPORTED_AJAX_EVENTS_NAMES.get(0);
	}

	/**
	 * Este metodo retorna uma colecao contendo os nomes dos eventos ajax
	 * suportados por este componente.
	 * 
	 * @see javax.faces.component.UIComponentBase#getEventNames()
	 */
	@Override
	public Collection<String> getEventNames() {
		return SUPPORTED_AJAX_EVENTS_NAMES;
	}

	public String getTeste() {
		return teste;
	}

	public void setTeste(String teste) {
		this.teste = teste;
	}

	/**
	 * Independente da forma de salvamento de estado, o JSF chama os metodos
	 * saveState() e restoreState() para salvar e restaurar o estado dos
	 * componentes respectivamente. Desta forma, para salvar os valores das
	 * variaveis de instancia é necessario sobrescrever os metodos de salvamento
	 * e restauracao de estado para incluir esses valores. O JSF chamara os
	 * metodos saveState() e restoreState() automaticamente, a nao ser que o
	 * componente estaja marcado com o atributo transient com o valor true. O
	 * metodo saveState() é chamado na fase RENDER_RESPONSE.
	 * 
	 * @see javax.faces.component.UIInput#saveState(javax.faces.context.FacesContext)
	 */
	@Override
	public Object saveState(FacesContext context) {
		Object[] savedState = (Object[]) super.saveState(context);
		List<Object> state = new ArrayList<Object>(Arrays.asList(savedState));
		state.add(this.teste);
		return state.toArray();
	}

	/**
	 * O método restoreState() é chamado na fase RESTORE_VIEW.
	 * 
	 * @see javax.faces.component.UIInput#restoreState(javax.faces.context.FacesContext,
	 *      java.lang.Object)
	 */
	@Override
	public void restoreState(FacesContext context, Object state) {
		super.restoreState(context, state);
		List<Object> savedState = Arrays.asList((Object[]) state);
		if (savedState.size() == 5) {
			this.teste = (String) savedState.get(4);
		}
	}

	public String getAccesskey() {
		return (String) super.getStateHelper().eval(PropertyKeys.accesskey);
	}

	public void setAccesskey(String accesskey) {
		super.getStateHelper().put(PropertyKeys.accesskey, accesskey);
	}

	public String getAlt() {
		return (String) super.getStateHelper().eval(PropertyKeys.alt);
	}

	public void setAlt(String alt) {
		super.getStateHelper().put(PropertyKeys.alt, alt);
	}

	public String getAutocomplete() {
		return (String) super.getStateHelper().eval(PropertyKeys.autocomplete);
	}

	public void setAutocomplete(String autocomplete) {
		super.getStateHelper().put(PropertyKeys.autocomplete, autocomplete);
	}

	public String getDir() {
		return (String) super.getStateHelper().eval(PropertyKeys.dir);
	}

	public void setDir(String dir) {
		super.getStateHelper().put(PropertyKeys.dir, dir);
	}

	public boolean isDisabled() {
		return (java.lang.Boolean) super.getStateHelper().eval(PropertyKeys.disabled, false);

	}

	public void setDisabled(boolean disabled) {
		super.getStateHelper().put(PropertyKeys.disabled, disabled);
	}

	public String getLabel() {
		return (String) super.getStateHelper().eval(PropertyKeys.label);
	}

	public void setLabel(String label) {
		super.getStateHelper().put(PropertyKeys.label, label);
	}

	public String getLang() {
		return (String) super.getStateHelper().eval(PropertyKeys.lang);
	}

	public void setLang(String lang) {
		super.getStateHelper().put(PropertyKeys.lang, lang);
	}

	public Integer getMaxlength() {
		return (Integer) super.getStateHelper().eval(PropertyKeys.maxlength);
	}

	public void setMaxlength(int maxlength) {
		super.getStateHelper().put(PropertyKeys.maxlength, maxlength);
	}

	public String getOnblur() {
		return (String) super.getStateHelper().eval(PropertyKeys.onblur);
	}

	public void setOnblur(String onblur) {
		super.getStateHelper().put(PropertyKeys.onblur, onblur);
	}

	public String getOnchange() {
		return (String) super.getStateHelper().eval(PropertyKeys.onchange);
	}

	public void setOnchange(String onchange) {
		super.getStateHelper().put(PropertyKeys.onchange, onchange);
	}

	public String getOnclick() {
		return (String) super.getStateHelper().eval(PropertyKeys.onclick);
	}

	public void setOnclick(String onclick) {
		super.getStateHelper().put(PropertyKeys.onclick, onclick);
	}

	public String getOndblclick() {
		return (String) super.getStateHelper().eval(PropertyKeys.ondblclick);
	}

	public void setOndblclick(String ondblclick) {
		super.getStateHelper().put(PropertyKeys.ondblclick, ondblclick);
	}

	public String getOnfocus() {
		return (String) super.getStateHelper().eval(PropertyKeys.onfocus);
	}

	public void setOnfocus(String onfocus) {
		super.getStateHelper().put(PropertyKeys.onfocus, onfocus);
	}

	public String getOnkeydown() {
		return (String) super.getStateHelper().eval(PropertyKeys.onkeydown);
	}

	public void setOnkeydown(String onkeydown) {
		super.getStateHelper().put(PropertyKeys.onkeydown, onkeydown);
	}

	public String getOnkeypress() {
		return (String) super.getStateHelper().eval(PropertyKeys.onkeypress);
	}

	public void setOnkeypress(String onkeypress) {
		super.getStateHelper().put(PropertyKeys.onkeypress, onkeypress);
	}

	public String getOnkeyup() {
		return (String) super.getStateHelper().eval(PropertyKeys.onkeyup);
	}

	public void setOnkeyup(String onkeyup) {
		super.getStateHelper().put(PropertyKeys.onkeyup, onkeyup);
	}

	public String getOnmousedown() {
		return (String) super.getStateHelper().eval(PropertyKeys.onmousedown);
	}

	public void setOnmousedown(String onmousedown) {
		super.getStateHelper().put(PropertyKeys.onmousedown, onmousedown);
	}

	public String getOnmousemove() {
		return (String) super.getStateHelper().eval(PropertyKeys.onmousemove);
	}

	public void setOnmousemove(String onmousemove) {
		super.getStateHelper().put(PropertyKeys.onmousemove, onmousemove);
	}

	public String getOnmouseout() {
		return (String) super.getStateHelper().eval(PropertyKeys.onmouseout);
	}

	public void setOnmouseout(String onmouseout) {
		super.getStateHelper().put(PropertyKeys.onmouseout, onmouseout);
	}

	public String getOnmouseover() {
		return (String) super.getStateHelper().eval(PropertyKeys.onmouseover);
	}

	public void setOnmouseover(String onmouseover) {
		super.getStateHelper().put(PropertyKeys.onmouseover, onmouseover);
	}

	public String getOnmouseup() {
		return (String) super.getStateHelper().eval(PropertyKeys.onmouseup);
	}

	public void setOnmouseup(String onmouseup) {
		super.getStateHelper().put(PropertyKeys.onmouseup, onmouseup);
	}

	public String getOnselect() {
		return (String) super.getStateHelper().eval(PropertyKeys.onselect);
	}

	public void setOnselect(String onselect) {
		super.getStateHelper().put(PropertyKeys.onselect, onselect);
	}

	public boolean isReadonly() {
		return (Boolean) super.getStateHelper().eval(PropertyKeys.readonly, false);
	}

	public void setReadonly(boolean readonly) {
		super.getStateHelper().put(PropertyKeys.readonly, readonly);
	}

	public Integer getSize() {
		return (Integer) super.getStateHelper().eval(PropertyKeys.size);
	}

	public void setSize(int size) {
		super.getStateHelper().put(PropertyKeys.size, size);
	}

	public String getStyle() {
		return (String) super.getStateHelper().eval(PropertyKeys.style);
	}

	public void setStyle(String style) {
		super.getStateHelper().put(PropertyKeys.style, style);
	}

	public String getStyleClass() {
		return (String) super.getStateHelper().eval(PropertyKeys.styleClass);
	}

	public void setStyleClass(String styleClass) {
		super.getStateHelper().put(PropertyKeys.styleClass, styleClass);
	}

	public String getTabindex() {
		return (String) super.getStateHelper().eval(PropertyKeys.tabindex);
	}

	public void setTabindex(String tabindex) {
		super.getStateHelper().put(PropertyKeys.tabindex, tabindex);
	}

	public String getTitle() {
		return (String) super.getStateHelper().eval(PropertyKeys.title);
	}

	public void setTitle(String title) {
		super.getStateHelper().put(PropertyKeys.title, title);
	}

	public String getDisabled() {
		return (String) super.getStateHelper().eval(PropertyKeys.disabled);
	}

	public void setDisabled(String disabled) {
		super.getStateHelper().put(PropertyKeys.disabled, disabled);
	}

	public String getReadonly() {
		return (String) super.getStateHelper().eval(PropertyKeys.readonly);
	}

	public void setReadonly(String readonly) {
		super.getStateHelper().put(PropertyKeys.readonly, readonly);
	}

}
