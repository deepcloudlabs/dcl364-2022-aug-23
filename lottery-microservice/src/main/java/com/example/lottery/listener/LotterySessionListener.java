package com.example.lottery.listener;

import java.util.Date;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@WebListener
public class LotterySessionListener implements HttpSessionListener,HttpSessionAttributeListener {

	@Override
	public void sessionCreated(HttpSessionEvent se) {
		HttpSessionListener.super.sessionCreated(se);
		System.err.println(String.format(String.format("Session (%s) is created at %s",se.getSession().getId(), new Date())));
		var names = se.getSession().getAttributeNames();
		while (names.hasMoreElements()) {
			var name = names.nextElement();
			System.err.println(se.getSession().getAttribute(name));
		}
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		System.err.println(String.format(String.format("Session (%s) is destroyed at %s",se.getSession().getId(), new Date())));
		HttpSessionListener.super.sessionDestroyed(se);
		
	}

	@Override
	public void attributeAdded(HttpSessionBindingEvent event) {
		HttpSessionAttributeListener.super.attributeAdded(event);
		System.err.println(event.getSource());
		System.err.println(event.getValue());
	}

	@Override
	public void attributeRemoved(HttpSessionBindingEvent event) {
		HttpSessionAttributeListener.super.attributeRemoved(event);
	}

	@Override
	public void attributeReplaced(HttpSessionBindingEvent event) {
		HttpSessionAttributeListener.super.attributeReplaced(event);
	}

}
