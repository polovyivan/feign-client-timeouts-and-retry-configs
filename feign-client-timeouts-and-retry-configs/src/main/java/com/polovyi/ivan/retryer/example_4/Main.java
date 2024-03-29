package com.polovyi.ivan.retryer.example_4;

import com.polovyi.ivan.configuration.ClientConfig;
import com.polovyi.ivan.dto.CreateCustomerRequest;
import feign.Feign;
import feign.Logger.Level;
import feign.Retryer;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import feign.slf4j.Slf4jLogger;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Main {

    public static void main(String[] args) {
        CustomerAppClient_4 client = Feign.builder()
                .logLevel(Level.FULL)
                .logger(new Slf4jLogger())
                .decoder(new JacksonDecoder(ClientConfig.OBJECT_MAPPER))
                .encoder(new JacksonEncoder(ClientConfig.OBJECT_MAPPER))
                .retryer(new Retryer.Default(1, 2, 3))
                .errorDecoder(new CustomErrorDecoder(ClientConfig.OBJECT_MAPPER))
                .target(CustomerAppClient_4.class,
                        "http://localhost:8001/spring-customer-app");
        log.info("==> Feign will retry and eventually succeed");
        client.createCustomer("500_2", new CreateCustomerRequest("fullName", "17737278341", "address"));
        try {
            log.info("==> Feign will retry and eventually fail");
            client.createCustomer("500_3", new CreateCustomerRequest("fullName", "17737278341", "address"));
        } catch (Exception e) {
            log.error("Received error");
        }
        log.info("==> Feign wont retry and will fail at the first attempt");
        client.createCustomer("", new CreateCustomerRequest(null, "17737278341", "address"));
    }
}