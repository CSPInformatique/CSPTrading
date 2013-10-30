package com.cspinformatique.csptrading.activetick.callback;

import at.feedapi.ATCallback;
import at.feedapi.ATCallback.ATRequestTimeoutCallback;

public class RequestTimeoutCallback extends ATCallback implements ATRequestTimeoutCallback {
	public void process(long origRequest) {
		System.out.println("(" + origRequest + "): Request timed-out\n");
	}
}