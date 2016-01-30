package view.listener;

import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

/**
 * Fases do cliclo de vida de uma requisição do JSF: <br>
 * <b>
 * RESTORE_VIEW > APPLY REQUEST VALUES > PROCESS VALIDATION > UPDATE MODEL
 * VALUES > INVOKE APPLICATION > RENDER RESPONSE.</b><br>
 * Quando uma pagina é solicitada pela primeira vez, significa que a requisicao
 * nao possui valores, desta forma, apenas as fases <b>RESTORE VIEW e RENDER
 * RESPONSE</b> sao executadas.
 * <hr>
 * <b>RESTORE VIEW: </b>O JSF identifica a pagina solicitada e monta uma arvore
 * de visualizacao com os componentes desta pagina na memoria;<br>
 * <b>APPLY REQUEST VALUES: </b>Caso a requisicao possua valores, o JSF percorre
 * a arvore de visualizacao montada anteriormente dando chance para cada
 * componente recuperar e armazenar os valores partinentes da requisicao;<br>
 * <b>PROCESS VALIDATION: </b>Nesta fase ocorrem as conversoes e validacoes dos
 * valores armazenados pelos componentes na memoria. Caso ocorra algum erro
 * neste processo, a requisicao é passada para a ultima fase do ciclo de vida,
 * RENDER RESPONSE;<br>
 * <b>UPDATE MODEL VALUES: </b>Aqui, os valores dos componentes sao atribuidos
 * as propriedades associadas aos ManagedBeans;<br>
 * <b>INVOKE APPLICATION: </b>Nesta fase, os métodos e listeners de acoes
 * associados aos botoes ou links da pagina sao executados;<br>
 * <b>RENDER RESPONSE: </b>Esta é a ultima fase do ciclo de vida, nela, cada
 * componente tem a chance de escrever na resposta que é montada e enviada para
 * o cliente.<br>
 * 
 * @author Matheus
 *
 */
public class CustomPhaseListener implements PhaseListener {

	private static final long serialVersionUID = 5323404845562429186L;

	@Override
	public void afterPhase(PhaseEvent event) {
		System.out.println("CustomPhaseListener.afterPhase() " + event.getPhaseId());
	}

	@Override
	public void beforePhase(PhaseEvent event) {
		System.out.println("CustomPhaseListener.beforePhase() " + event.getPhaseId());
	}

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.ANY_PHASE;
	}

}
