package com.cspinformatique.csptrading.activetick;

import com.cspinformatique.csptrading.activetick.callback.LoginResponseCallback;
import com.cspinformatique.csptrading.activetick.callback.RequestTimeoutCallback;
import com.cspinformatique.csptrading.activetick.callback.ServerTimeUpdateCallback;
import com.cspinformatique.csptrading.activetick.callback.SessionOutputMessageCallback;
import com.cspinformatique.csptrading.activetick.callback.SessionStatusChangeCallback;

import at.feedapi.ActiveTickServerAPI;
import at.feedapi.Session;
import at.shared.ATServerAPIDefines;
import at.shared.ATServerAPIDefines.ATGUID;
import at.utils.jlib.Errors;

public class ActiveTickConnector {
	private ActiveTickProperties properties;
	private ActiveTickServerAPI serverAPI;
	private Session session;
	
	private LoginResponseCallback loginResponseCallback;
	private RequestTimeoutCallback requestTimeoutCallback;
	private SessionStatusChangeCallback sessionStatusChangeCallback;
	
	public ActiveTickConnector(ActiveTickProperties properties){
		this.properties = properties;
		
		serverAPI = new ActiveTickServerAPI();
		
		serverAPI.ATInitAPI();
		
		session = serverAPI.ATCreateSession();
		
		this.loginResponseCallback = new LoginResponseCallback();
		this.requestTimeoutCallback = new RequestTimeoutCallback();
		this.sessionStatusChangeCallback = new SessionStatusChangeCallback(this);
		
		this.initSession();
	}

	public Session getSession() {
		return session;
	}
	
	private void initSession(){
		// Configuring API KEY.
		ATGUID atguid = (new ATServerAPIDefines()).new ATGUID();
		atguid.SetGuid(this.properties.getApiKey());
		
		if(this.serverAPI.ATSetAPIUserId(session, atguid) == Errors.ERROR_SUCCESS){
			// Binding default callback handlers.
			this.session.SetServerTimeUpdateCallback(new ServerTimeUpdateCallback());
			this.session.SetOutputMessageCallback(new SessionOutputMessageCallback());
			
			// Initializing session.
			this.serverAPI.ATInitSession(
				session, 
				properties.getServer(), 
				properties.getServer(), 
				properties.getPort(), 
				this.sessionStatusChangeCallback
			);
		}else{
			throw new RuntimeException("Invalid Active Tick API KEY specified.");
		}
	}
	
	public void sendLoginRequest(){
		this.serverAPI.ATSendRequest(
			session, 
			this.serverAPI.ATCreateLoginRequest(
				session, 
				properties.getUser(), 
				properties.getPassword(), 
				this.loginResponseCallback
			), 
			ActiveTickServerAPI.DEFAULT_REQUEST_TIMEOUT,
			this.requestTimeoutCallback
		);
	}
}
