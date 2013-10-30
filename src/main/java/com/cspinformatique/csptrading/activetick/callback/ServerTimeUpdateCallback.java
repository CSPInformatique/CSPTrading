package com.cspinformatique.csptrading.activetick.callback;

import at.feedapi.ATCallback;
import at.feedapi.ATCallback.ATServerTimeUpdateCallback;
import at.shared.ATServerAPIDefines.SYSTEMTIME;

public class ServerTimeUpdateCallback extends ATCallback implements ATServerTimeUpdateCallback {
	public void process(SYSTEMTIME serverTime) {
		// System.out.println("RECV: Server time update [" + serverTime.hour
		// + ":" + serverTime.minute +
		// ":" + serverTime.second + ":" + serverTime.month + ":" +
		// serverTime.day + ":" + serverTime.year);
	}
}