package org.androidpn.client;

import org.jivesoftware.smack.packet.IQ;

public class HeartBeatIQ extends IQ {

	@Override
	public String getChildElementXML() {
		//心跳包IQ无实际用途
		return " ";
	}

}
