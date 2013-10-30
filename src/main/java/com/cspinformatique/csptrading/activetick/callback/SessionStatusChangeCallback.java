package com.cspinformatique.csptrading.activetick.callback;

import com.cspinformatique.csptrading.activetick.ActiveTickConnector;

import at.feedapi.ATCallback;
import at.feedapi.ATCallback.ATSessionStatusChangeCallback;
import at.shared.ATServerAPIDefines;

public class SessionStatusChangeCallback extends ATCallback implements ATSessionStatusChangeCallback {
	private ActiveTickConnector activeTickConnector;
	
	public SessionStatusChangeCallback(ActiveTickConnector activeTickConnector) {
		this.activeTickConnector = activeTickConnector;
	}

	public void process(at.feedapi.Session session, ATServerAPIDefines.ATSessionStatusType type) {
		String strStatusType = "";
		switch (type.m_atSessionStatusType) {
			case ATServerAPIDefines.ATSessionStatusType.SessionStatusConnected:
				strStatusType = "SessionStatusConnected";
				break;
				
			case ATServerAPIDefines.ATSessionStatusType.SessionStatusDisconnected:
				strStatusType = "SessionStatusDisconnected";
				break;
				
			case ATServerAPIDefines.ATSessionStatusType.SessionStatusDisconnectedDuplicateLogin:
				strStatusType = "SessionStatusDisconnectedDuplicateLogin";
				break;
				
			default:
				break;
		}

		System.out.println("RECV Status change [" + strStatusType + "]");

		// if we are connected to the server, send a login request
		if (type.m_atSessionStatusType == ATServerAPIDefines.ATSessionStatusType.SessionStatusConnected) {
			this.activeTickConnector.sendLoginRequest();
		}
	}
}
