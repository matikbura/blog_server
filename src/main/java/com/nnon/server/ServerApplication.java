package com.nnon.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

@SpringBootApplication
public class ServerApplication{
    public static void main(String[] args) {
        SpringApplication.run(ServerApplication.class,args);
    }
}
