package org.androidpn.client;

import org.jivesoftware.smack.packet.IQ;

public class HeartBeatIQ extends IQ {

	@Override
	public String getChildElementXML() {
		//������IQ��ʵ����;
		return " ";
	}

}
