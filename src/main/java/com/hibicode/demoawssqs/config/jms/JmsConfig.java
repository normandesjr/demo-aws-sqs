package com.hibicode.demoawssqs.config.jms;

import com.amazon.sqs.javamessaging.ProviderConfiguration;
import com.amazon.sqs.javamessaging.SQSConnectionFactory;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;

import javax.annotation.PostConstruct;

@Configuration
public class JmsConfig {

    private SQSConnectionFactory connectionFactory;

    @Autowired
    private AWSCredentialsProvider credentialsProvider;

    @Value("${cloud.aws.queue.test.endpoint}")
    private String serviceEndpoint;

    @Value("${cloud.aws.region.static}")
    private String region;

    @PostConstruct
    public void init() {
        connectionFactory = createSQSConnectionFactory();
    }

    private SQSConnectionFactory createSQSConnectionFactory() {
        final AmazonSQS sqs = AmazonSQSClient.builder()
                .withCredentials(credentialsProvider)
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(serviceEndpoint, region))
                .build();
        return new SQSConnectionFactory(new ProviderConfiguration(), sqs);
    }

    @Bean
    public DefaultJmsListenerContainerFactory jmsListenerContainerFactory() {
        final DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        return factory;
    }

    @Bean
    public JmsTemplate defaultJmsTemplate() {
        return new JmsTemplate(connectionFactory);
    }
}
