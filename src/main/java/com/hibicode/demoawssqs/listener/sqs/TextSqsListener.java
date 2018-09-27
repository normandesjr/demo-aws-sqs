package com.hibicode.demoawssqs.listener.sqs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.stereotype.Component;

@Component
public class TextSqsListener {

    private Logger logger = LoggerFactory.getLogger(TextSqsListener.class);

    @SqsListener("${cloud.aws.queue.test.url}")
    public void receiveMessage(String text) {
        logger.info("Message received by Sqs listener: " + text);
    }
}
