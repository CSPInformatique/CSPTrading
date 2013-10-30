package com.cspinformatique.csptrading.activetick.callback;

import at.feedapi.ATCallback;
import at.feedapi.ATCallback.OutputMessageCallback;
import at.utils.jlib.OutputMessage;
import at.utils.jlib.OutputMessage.Severity;

public class SessionOutputMessageCallback extends ATCallback implements OutputMessageCallback {
	public SessionOutputMessageCallback() {

	}

	public void process(OutputMessage outputMessage) {
		switch (outputMessage.m_severity.m_level) {
			case Severity.SEVERITY_INFORMATIONAL: {
				System.out.println(outputMessage.m_strOutput);
			}
				break;
	
			case Severity.SEVERITY_DATA: {
				System.out.println(outputMessage.m_strOutput);
			}
				break;
	
			case Severity.SEVERITY_EXCEPTION: {
				System.out.println(outputMessage.m_strOutput);
			}
				break;
	
			case Severity.SEVERITY_ERROR: {
				System.out.println(outputMessage.m_strOutput);
			}
				break;
	
			default: {
				System.out.println(outputMessage.m_severity + " : "
						+ outputMessage.m_strOutput);
			}
				break;
		}
	}
}