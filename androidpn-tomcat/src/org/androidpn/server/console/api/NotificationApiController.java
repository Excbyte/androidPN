//
//  NotificationApiController.java
//  FeOA
//
//  Created by LuTH on 2012-4-1.
//  Copyright 2012 flyrise. All rights reserved.
//

package org.androidpn.server.console.api;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.androidpn.server.util.Config;
import org.androidpn.server.xmpp.push.NotificationManager;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

public class NotificationApiController extends MultiActionController {

	private NotificationManager notificationManager;

	public NotificationApiController() {
		notificationManager = new NotificationManager();
	}

	public void list(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		send(request, response);
	}

	public void send(HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String category = ServletRequestUtils.getStringParameter(request,
				"category", "0");
		String title = ServletRequestUtils.getStringParameter(request, "title",
				"");
		String message = ServletRequestUtils.getStringParameter(request,
				"message", "");

		String username = ServletRequestUtils.getStringParameter(request,
				"username", "");
		String alias = ServletRequestUtils.getStringParameter(request, "alias",
				"");
		String tag = ServletRequestUtils.getStringParameter(request, "tag", "");
		String uri = ServletRequestUtils.getStringParameter(request, "uri", "");

		String apiKey = Config.getString("apiKey", "");
		logger.debug("apiKey=" + apiKey);
		if ("".equals(title)) {
			response.getWriter().print(getResult(false, "缺少title参数"));
			return;
		}

		if ("".equals(message)) {
			response.getWriter().print(getResult(false, "缺少message参数"));
			return;
		}

		if (category.equalsIgnoreCase("0")) {
			notificationManager.sendBroadcast(apiKey, title, message, uri);
		} else if (category.equalsIgnoreCase("1")) {
			if ("".equals(username)) {
				response.getWriter().print(getResult(false, "缺少username参数"));
				return;
			}
			String id = String.valueOf(new Date().getTime());
			notificationManager.sendNotifcationToUser(id, apiKey, username,
					title, message, uri, true);
		} else if (category.equalsIgnoreCase("2")) {
			if ("".equals(alias)) {
				response.getWriter().print(getResult(false, "缺少alias参数"));
				return;
			}
			// 别名推送
			notificationManager.sendNotificatoionByAlias(alias, apiKey, title,
					message, uri, true);
		} else if (category.equalsIgnoreCase("3")) {
			if ("".equals(tag)) {
				response.getWriter().print(getResult(false, "缺少tag参数"));
				return;
			}
			// 标签推送
			notificationManager.sendNotificationByTag(tag, apiKey, title,
					message, uri, true);
		}

		response.getWriter().print(getResult(true, ""));
	}

	private String getResult(boolean status, String msg) {

		return "{\"status\":\"" + status + "\",\"msg\":\"" + msg + "\"}";
	}

}
