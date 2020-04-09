package com.example.jmsdemo;

import org.apache.activemq.artemis.core.config.impl.ConfigurationImpl;
import org.apache.activemq.artemis.core.server.ActiveMQServers;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JmsDemoApplication {

    public static void main(String[] args) throws Exception {

        // set up embedded activemq server (obviously only for demo stuff)
        var config = new ConfigurationImpl()
                .setPersistenceEnabled(false)
                .setSecurityEnabled(false)
                .setJournalDirectory("build/data/journal")
                .addAcceptorConfiguration("invm", "vm://0");

        ActiveMQServers.newActiveMQServer(config).start();

        SpringApplication.run(JmsDemoApplication.class, args);
    }

}
