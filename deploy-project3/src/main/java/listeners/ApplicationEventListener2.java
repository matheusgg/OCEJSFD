package listeners;

import javax.faces.application.Application;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.SystemEvent;
import javax.faces.event.SystemEventListener;

public class ApplicationEventListener2 implements SystemEventListener {

	@Override
	public void processEvent(SystemEvent event) throws AbortProcessingException {
		System.out.println("deploy-project3 - ApplicationEventListener.processEvent()");
	}

	@Override
	public boolean isListenerForSource(Object source) {
		return source instanceof Application;
	}

}
