package br.com.components.submit;

import javax.faces.view.facelets.ComponentConfig;
import javax.faces.view.facelets.ComponentHandler;
import javax.faces.view.facelets.MetaRuleset;

import com.sun.faces.facelets.tag.MethodRule;

/**
 * Por padrao, a implementacao do JSF nao sabe como interpretar expressoes de
 * metodos desconhecidas pelo manipulador de tags, ou seja, o TagHandler do JSF
 * é capaz de manipular apenas as expressoes de metodos definidas em action,
 * actionListener, valueChangeListener, converter, validator, etc. Porem, quando
 * é necessario adicionar um atributo que pode aceitar uma expressao de metodo a
 * um componente personalizado, é necessario criar as regras de manipulacao de
 * metodo manualmente. Desta forma, é preciso implementar um ComponentHandler
 * personalizado para o componente que possui o atributo que pode receber uma
 * expressao de metodo.
 * 
 * @author Matheus
 *
 */
public class SubmitButtonHandler extends ComponentHandler {

	public SubmitButtonHandler(ComponentConfig config) {
		super(config);
	}

	@Override
	@SuppressWarnings("rawtypes")
	protected MetaRuleset createMetaRuleset(Class type) {
		/*
		 * Adicionando uma regra de metodo para o atributo method. Deste modo, o
		 * JSF interpretara a expressao de metodo informada no atributo method
		 * como um objeto do tipo MethodExpression.
		 */
		return super.createMetaRuleset(type).addRule(new MethodRule("methodToInvoke", Void.class, new Class[0]));
	}

}
