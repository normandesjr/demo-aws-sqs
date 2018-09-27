package com.hibicode.demoawssqs.producer.sqs;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class TextSqsProducer {

    private static final Logger logger = LoggerFactory.getLogger(TextSqsProducer.class);

    @Autowired
    private AmazonSQS sqs;

    @Value("${cloud.aws.queue.test.url}")
    private String queueUrl;

    public void send(String message) {
        logger.info("Sending message via sqs...");
        SendMessageRequest sendMessageRequest = new SendMessageRequest()
                .withQueueUrl(queueUrl)
                .withMessageBody(message)
                .withDelaySeconds(3);
        sqs.sendMessage(sendMessageRequest);
        logger.info("... message sent via sqs.");
    }

}
