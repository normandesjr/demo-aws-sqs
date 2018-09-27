package com.hibicode.demoawssqs;

import com.amazon.sqs.javamessaging.ProviderConfiguration;
import com.amazon.sqs.javamessaging.SQSConnectionFactory;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;

import javax.annotation.PostConstruct;

@SpringBootApplication
//@EnableJms
//@Configuration
@EnableConfigurationProperties
public class DemoAwsSqsApplication {

    private SQSConnectionFactory connectionFactory;

	public static void main(String[] args) {
		SpringApplication.run(DemoAwsSqsApplication.class, args);
	}

//    @PostConstruct
//    public void init() {
//        connectionFactory = createSQSConnectionFactory();
//    }
//
//    private SQSConnectionFactory createSQSConnectionFactory() {
//        final AmazonSQS sqs = AmazonSQSClient.builder()
////                .withRegion(Regions.US_EAST_1)
//                .withCredentials(new ProfileCredentialsProvider("app"))
//                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration("sqs.us-east-1.amazonaws.com/746213696263", "us-east-1"))
//                .build();
//        return new SQSConnectionFactory(new ProviderConfiguration(), sqs);
//    }
//
//    @Bean
//    public DefaultJmsListenerContainerFactory jmsListenerContainerFactory() {
//        final DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
//        factory.setConnectionFactory(connectionFactory);
//        return factory;
//    }
//
//    @Bean
//    public JmsTemplate defaultJmsTemplate() {
//        return new JmsTemplate(connectionFactory);
//    }
}
