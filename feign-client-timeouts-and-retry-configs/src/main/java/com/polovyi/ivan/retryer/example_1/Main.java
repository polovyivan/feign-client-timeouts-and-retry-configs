package com.polovyi.ivan.retryer.example_1;

import com.polovyi.ivan.configuration.ClientConfig;
import com.polovyi.ivan.dto.CreateCustomerRequest;
import feign.Feign;
import feign.Logger.Level;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import feign.slf4j.Slf4jLogger;

public class Main {

    public static void main(String[] args) {
        CustomerAppClient_1 client = Feign.builder()
                .logLevel(Level.FULL)
                .logger(new Slf4jLogger())
                .decoder(new JacksonDecoder(ClientConfig.OBJECT_MAPPER))
                .encoder(new JacksonEncoder(ClientConfig.OBJECT_MAPPER))
                .target(CustomerAppClient_1.class,
                        "http://localhost:8001/spring-customer-app");
        client.createCustomer(new CreateCustomerRequest("fullName", "17737278341", "address"));
    }
}