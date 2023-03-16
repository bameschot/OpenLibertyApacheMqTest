package com.ameschot.olamqtest.receivers;

import com.ameschot.olamqtest.common.MessageMarshaller;
import com.ameschot.olamqtest.common.MessageUnMarshaller;
import com.ameschot.olamqtest.common.QueueMessageSender;
import com.ameschot.olamqtest.generated.model.TestInMessage;
import com.ameschot.olamqtest.generated.model.TestOutMessage;
import com.ameschot.olamqtest.service.AccountService;
import lombok.Getter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.Resource;
import javax.ejb.Singleton;
import javax.inject.Inject;
import javax.jms.Message;
import javax.jms.Queue;
import javax.jms.QueueConnectionFactory;
import javax.jms.TextMessage;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.ByteArrayOutputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

@Singleton
@Slf4j
public class ReceiverIn {

    @Resource(lookup = "jms/queueCF")
    QueueConnectionFactory factory;

    @Resource(lookup = "jms/test/out/queue")
    Queue queueOut;

    @Inject
    QueueMessageSender queueMessageSender;

    @Inject
    AccountService accountService;

    private MessageMarshaller<TestOutMessage> tomMessageMarshaller = new MessageMarshaller<>(TestOutMessage.class);

    private MessageUnMarshaller<TestInMessage> timMessageUnMarshaller = new MessageUnMarshaller<>(TestInMessage.class);

    @SneakyThrows
    public void process(Message message) {

        try {

            log.warn("MDB Received: {}", message);
            String outPayload = tomMessageMarshaller.marshall(doWork(timMessageUnMarshaller.unmarshall(((TextMessage) message).getText())));
            log.warn("MDB Send: {}", outPayload);
            queueMessageSender.send(outPayload, queueOut, factory);
        } catch (Exception e) {
            log.error("Exception processing MDB in for " + queueOut, e);
        }
    }

    TestOutMessage doWork(TestInMessage inMessage) {
        TestOutMessage testOutMessage = new TestOutMessage();
        testOutMessage.setAccount(inMessage.getAccount());
        testOutMessage.setTotal(accountService.getAccounts().compute(inMessage.getAccount(), (account, sum) -> sum == null ? inMessage.getAmount() : sum + inMessage.getAmount()));

        return testOutMessage;
    }
}
