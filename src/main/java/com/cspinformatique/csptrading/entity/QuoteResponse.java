package com.cspinformatique.csptrading.entity;

import java.util.Map;
/* {	"serverTime":1375626548183,
		"delay":60,
		"stocks":{
			"360097430":{
				"open":"42.3350",
				"time":"02/08",
				"pct":"1.8363",
				"last":"42.9800",
				"volume":924999,
				"high":"43.1250",
				"ask":"43.0000",
				"low":"42.3050",
				"bid":"42.9400",
				"prev":"42.2050"
			}
		}
	}
*/
public class QuoteResponse {
	private long serverTime;
	private int delay;
	private Map<Long, Quote> stocks;
	
	public QuoteResponse(){
		
	}
	
	public QuoteResponse(long serverTime, int delay, Map<Long, Quote> stocks) {
		this.serverTime = serverTime;
		this.delay = delay;
		this.stocks = stocks;
	}
	public long getServerTime() {
		return serverTime;
	}
	public void setServerTime(long serverTime) {
		this.serverTime = serverTime;
	}
	public int getDelay() {
		return delay;
	}
	public void setDelay(int delay) {
		this.delay = delay;
	}
	public Map<Long, Quote> getStocks() {
		return stocks;
	}
	public void setStocks(Map<Long, Quote> stocks) {
		this.stocks = stocks;
	}
}
