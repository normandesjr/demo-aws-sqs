package com.hibicode.demoawssqs.config.sqs;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.services.sqs.AmazonSQSAsync;
import com.amazonaws.services.sqs.AmazonSQSAsyncClientBuilder;
import com.amazonaws.services.sqs.buffered.AmazonSQSBufferedAsyncClient;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.aws.messaging.config.QueueMessageHandlerFactory;
import org.springframework.cloud.aws.messaging.config.SimpleMessageListenerContainerFactory;
import org.springframework.cloud.aws.messaging.listener.QueueMessageHandler;
import org.springframework.cloud.aws.messaging.listener.SimpleMessageListenerContainer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.handler.annotation.support.PayloadArgumentResolver;

import java.util.Collections;

@Configuration
public class SqsConfig {

    @Autowired
    private AWSCredentialsProvider credentialsProvider;

    @Bean
    public AmazonSQSAsync amazonSQSAsync() {
        AmazonSQSAsync sqsAsync = AmazonSQSAsyncClientBuilder
                .standard()
                .withCredentials(credentialsProvider)
                .build();

        return new AmazonSQSBufferedAsyncClient(sqsAsync);
    }

    @Bean
    public SimpleMessageListenerContainer simpleMessageListenerContainer(AmazonSQSAsync amazonSQSAsync) {
        SimpleMessageListenerContainer messageListenerContainer = simpleMessageListenerContainerFactory(amazonSQSAsync).createSimpleMessageListenerContainer();
        messageListenerContainer.setMessageHandler(queueMessageHandler(amazonSQSAsync));
        return messageListenerContainer;
    }

    @Bean
    public SimpleMessageListenerContainerFactory simpleMessageListenerContainerFactory(AmazonSQSAsync amazonSQSAsync) {
        SimpleMessageListenerContainerFactory messageListenerContainerFactory = new SimpleMessageListenerContainerFactory();
        messageListenerContainerFactory.setAmazonSqs(amazonSQSAsync);
        return messageListenerContainerFactory;
    }

    @Bean
    public QueueMessageHandlerFactory queueMessageHandlerFactory() {
        QueueMessageHandlerFactory factory = new QueueMessageHandlerFactory();
        MappingJackson2MessageConverter jacksonMessageConverter = new MappingJackson2MessageConverter();
        jacksonMessageConverter.setSerializedPayloadClass(String.class);
        jacksonMessageConverter.setStrictContentTypeMatch(false);

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//        objectMapper.registerModules(JsonUtils.JacksonModules.getModules());
        jacksonMessageConverter.setObjectMapper(objectMapper);

        PayloadArgumentResolver payloadArgumentResolver = new PayloadArgumentResolver(jacksonMessageConverter);
        factory.setArgumentResolvers(Collections.singletonList(payloadArgumentResolver));

        return factory;
    }

    @Bean
    public QueueMessageHandler queueMessageHandler(AmazonSQSAsync amazonSQSAsync) {
        queueMessageHandlerFactory().setAmazonSqs(amazonSQSAsync);

        return queueMessageHandlerFactory().createQueueMessageHandler();
    }
}
