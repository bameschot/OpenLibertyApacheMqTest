package com.ameschot.olamqtest.common;

import lombok.extern.slf4j.Slf4j;

import javax.inject.Singleton;
import javax.jms.Connection;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.QueueConnectionFactory;
import javax.jms.Session;

@Slf4j
@Singleton
public class QueueMessageSender {
    public void send(String message, Queue queue, QueueConnectionFactory queueConnectionFactory) {
        try (Connection connection = queueConnectionFactory.createConnection();
             Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
             MessageProducer producer = session.createProducer(queue)) {

            Message msg = session.createTextMessage(message); // text message
            producer.send(msg);

            log.warn("Message Send: {}", message);
        } catch (Exception e) {
            log.error("Exception Sending message", e);
        }
    }
}
