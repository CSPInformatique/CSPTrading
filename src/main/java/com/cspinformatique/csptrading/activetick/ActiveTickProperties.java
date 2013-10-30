package com.cspinformatique.csptrading.activetick;

public class ActiveTickProperties{
	private String server;
	private int port;
	private String apiKey;
	private String user;
	private String password;
	
	public ActiveTickProperties(){
		
	}
	
	public ActiveTickProperties(
		String server, 
		int port, 
		String apiKey,
		String user, 
		String password
	){
		this.server = server;
		this.port = port;
		this.apiKey = apiKey;
		this.user = user;
		this.password = password;
	}
	public String getServer() {
		return server;
	}
	public void setServer(String server) {
		this.server = server;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	public String getApiKey() {
		return apiKey;
	}
	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
