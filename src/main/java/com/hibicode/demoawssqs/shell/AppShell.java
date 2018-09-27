package com.hibicode.demoawssqs.shell;

import com.hibicode.demoawssqs.producer.jms.TextJmsProducer;
import com.hibicode.demoawssqs.producer.sqs.TextSqsProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

@ShellComponent
public class AppShell {

    private static Logger logger = LoggerFactory.getLogger(AppShell.class);

    @Autowired
    private TextSqsProducer sqsProducer;

    @Autowired
    private TextJmsProducer jmsProducer;

    @ShellMethod("Send a message using JMS model")
    public void sendViaJms(@ShellOption(defaultValue = "hibicode") String text) {
        jmsProducer.send(text);
    }

    @ShellMethod("Send a message using SQS model")
    public void sendViaSqs(@ShellOption(defaultValue = "hibicode") String text) {
        sqsProducer.send(text);
    }


}
