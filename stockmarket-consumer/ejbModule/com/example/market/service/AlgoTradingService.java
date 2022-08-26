package com.example.market.service;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

@MessageDriven(
	activationConfig = {
		@ActivationConfigProperty(propertyName = "destination", propertyValue = "java:/jms/queue/stockQueue"),	
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")	
	}	
)
public class AlgoTradingService implements MessageListener {

	@Override
	public void onMessage(Message message) {
		if (message instanceof TextMessage) {
			String json = null;
			try {
				json = ((TextMessage) message).getText();
				System.err.println("Message has arrived: "+json);
			} catch (JMSException e) {
				System.err.println("Error while receiving the jms message: "+e.getMessage());
			}
		}
		
	}

}
