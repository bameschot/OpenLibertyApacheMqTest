package com.ameschot.olamqtest.receivers;

import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.Message;
import javax.jms.MessageListener;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@MessageDriven
public class ReceiverInMDB implements MessageListener {

    @Inject
    ReceiverIn receiverIn;

    @Override
    public void onMessage(Message message) {
        receiverIn.process(message);
    }
}
