package com.hibicode.demoawssqs.listener.jms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class TextJmsListener {

    private Logger logger = LoggerFactory.getLogger(TextJmsListener.class);

    @JmsListener(destination = "${cloud.aws.queue.test.name}")
    public void messageConsumer(@Headers Map<String, Object> messageAttributes,
                                @Payload String message) {

        logger.info("Message received by Jms listener: " + message);
    }

}
