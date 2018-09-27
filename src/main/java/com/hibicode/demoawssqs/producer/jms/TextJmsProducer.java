package com.hibicode.demoawssqs.producer.jms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class TextJmsProducer {

    private static final Logger logger = LoggerFactory.getLogger(TextJmsProducer.class);

    @Autowired
    private JmsTemplate template;

    @Value("${cloud.aws.queue.test.name}")
    private String destination;

    public void send(String text) {
        logger.info("Sending message via jms...");
        template.convertAndSend(destination, text);
        logger.info("... message sent via jms.");

    }
}
