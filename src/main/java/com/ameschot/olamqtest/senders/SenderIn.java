package com.ameschot.olamqtest.senders;

import javax.annotation.Resource;
import javax.ejb.Singleton;
import javax.inject.Inject;
import javax.jms.Connection;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.QueueConnectionFactory;
import javax.jms.Session;

import com.ameschot.olamqtest.common.MessageMarshaller;
import com.ameschot.olamqtest.common.QueueMessageSender;
import com.ameschot.olamqtest.generated.model.TestInMessage;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Singleton
public class SenderIn {

    @Resource(lookup="jms/queueCF")
    QueueConnectionFactory factory;

    @Resource(lookup = "jms/test/in/queue")
    Queue queue;

    @Inject
    QueueMessageSender queueMessageSender;

    MessageMarshaller<TestInMessage> testInMessageMessageMarshaller = new MessageMarshaller<>(TestInMessage.class);


    @SneakyThrows
    public void send(TestInMessage message){
        queueMessageSender.send(testInMessageMessageMarshaller.marshall(message),queue,factory);
    }

//    public static void main(String[] args) throws Exception {
//        //failover:(tcp://localhost:61616)
////        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory("ssl://b-7522217f-2026-465b-88df-2868ce151474-1.mq.eu-central-1.amazonaws.com:61617");
////        factory.setUserName("active-mq-ba-test-broker");
////        factory.setPassword("active-mq-ba-test-broker");
//
//        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory("tcp://localhost:8091");
//
//
//        Session session = null;
//        try (Connection con = factory.createConnection()) {
//
//
//            session = con.createSession(false, Session.AUTO_ACKNOWLEDGE); // non-transacted session
//            System.out.println("create session");
//
//            Queue queue = session.createQueue("q.test.1"); // only specifies queue name
//
//            MessageProducer producer = session.createProducer(queue);
//            Message msg = session.createTextMessage("hello queue"); // text message
//            producer.send(msg);
//            System.out.println("!!!!!!!");
//
//        } catch (
//                Exception e) {
//            System.out.println(e);
//            e.printStackTrace();
//        } finally {
//            session.close();
//        }
//    }
}