package com.cspinformatique.csptrading.activetick.callback;

import at.feedapi.ATCallback;
import at.feedapi.Session;
import at.feedapi.ATCallback.ATLoginResponseCallback;
import at.shared.ATServerAPIDefines;
import at.shared.ATServerAPIDefines.ATLOGIN_RESPONSE;

public class LoginResponseCallback extends ATCallback implements ATLoginResponseCallback {
	private String strLoginResponseType = "";

	public LoginResponseCallback() {

	}

	public void process(Session session, long requestId, ATLOGIN_RESPONSE response) {
		// System.out.println("Processing Login Response Callback.");
		
		switch (response.loginResponse.m_atLoginResponseType) {
			case ATServerAPIDefines.ATLoginResponseType.LoginResponseSuccess:
				strLoginResponseType = "LoginResponseSuccess";
				break;
				
			case ATServerAPIDefines.ATLoginResponseType.LoginResponseInvalidUserid:
				strLoginResponseType = "LoginResponseInvalidUserid";
				break;
				
			case ATServerAPIDefines.ATLoginResponseType.LoginResponseInvalidPassword:
				strLoginResponseType = "LoginResponseInvalidPassword";
				break;
				
			case ATServerAPIDefines.ATLoginResponseType.LoginResponseInvalidRequest:
				strLoginResponseType = "LoginResponseInvalidRequest";
				break;
				
			case ATServerAPIDefines.ATLoginResponseType.LoginResponseLoginDenied:
				strLoginResponseType = "LoginResponseLoginDenied";
				break;
				
			case ATServerAPIDefines.ATLoginResponseType.LoginResponseServerError:
				strLoginResponseType = "LoginResponseServerError";
				break;
				
			default:
				strLoginResponseType = "unknown";
				break;
		}

		System.out.println("RECV " + requestId + ": Login Response ["
				+ strLoginResponseType + "]");
	}
}