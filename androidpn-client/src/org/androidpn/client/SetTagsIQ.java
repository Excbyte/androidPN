package org.androidpn.client;

import java.util.ArrayList;
import java.util.List;

import org.jivesoftware.smack.packet.IQ;

import android.R.string;

public class SetTagsIQ extends IQ {

	private String username;

	private List<String> tagList = new ArrayList<String>();

	@Override
	public String getChildElementXML() {
		StringBuilder buf = new StringBuilder();
		buf.append("<").append("settags").append(" xmlns=\"")
				.append("androidpn:iq:settags").append("\">");
		if (username != null) {
			buf.append("<username>").append(username).append("</username>");
		}
		if (tagList != null && !tagList.isEmpty()) {
			buf.append("<tags>");
			boolean needSeperate = false;
			for (String tag : tagList) {
				if (needSeperate) {
					buf.append(",");
				}
				buf.append(tag);
				needSeperate = true;
			}
			buf.append("</tags>");
		}

		buf.append("</").append("settags").append("> ");
		return buf.toString();
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<String> getTagList() {
		return tagList;
	}

	public void setTagList(List<String> tagList) {
		this.tagList = tagList;
	}
}
