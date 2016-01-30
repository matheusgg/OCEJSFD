package scopes.cdi;

import java.io.Serializable;

import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * Beans CDI com escopo de conversacao inicialmente sao transientes, ou seja,
 * sao semelhantes aos beans com escopo de requisicao. Porém quando a
 * conversacao é iniciada (Conversation.begin()) este bean se transforma em uma
 * instancia long-running, ou seja, este objeto sobreviverá através de multiplas
 * requisicoes até que a conversacao seja encerrada (Conversation.end()). Para
 * que isto aconteca, é necessário que as requisicoes enviem o parametro de
 * requisicao "cdi" para identificar a conversacao corrente. O parametro "cdi"
 * deve ser o ID da conversacao gerada pelo metodo Conversation.begin() e pode
 * ser recuperado atraves de Conversation.getId().
 * 
 * @author Matheus
 *
 */
@Named
@ConversationScoped
public class ConversationScopeNamedMB implements Serializable {

	private static final long serialVersionUID = -4451525282936135547L;

	@Inject
	private Conversation conversation;

	private String conversationID;

	public void initConversation() {
		if (this.conversation.isTransient()) {
			this.conversation.begin();
			this.conversationID = this.conversation.getId();
		}
	}

	public void endConversation() {
		if (!this.conversation.isTransient()) {
			this.conversation.end();
		}
	}

	/**
	 * @return the conversationID
	 */
	public String getConversationID() {
		return conversationID;
	}

	/**
	 * @param conversationID
	 *            the conversationID to set
	 */
	public void setConversationID(String conversationID) {
		this.conversationID = conversationID;
	}

}
