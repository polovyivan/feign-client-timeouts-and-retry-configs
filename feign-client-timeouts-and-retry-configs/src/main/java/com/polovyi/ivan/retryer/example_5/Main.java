package com.polovyi.ivan.retryer.example_5;

import com.polovyi.ivan.configuration.ClientConfig;
import com.polovyi.ivan.dto.CreateCustomerRequest;
import feign.Feign;
import feign.Logger.Level;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import feign.slf4j.Slf4jLogger;

public class Main {

    public static void main(String[] args) {
        CustomerAppClient_5 client = Feign.builder()
                .logLevel(Level.FULL)
                .logger(new Slf4jLogger())
                .decoder(new JacksonDecoder(ClientConfig.OBJECT_MAPPER))
                .encoder(new JacksonEncoder(ClientConfig.OBJECT_MAPPER))
                .retryer(new CustomRetryer())
                .target(CustomerAppClient_5.class,
                        "http://localhost:8001/spring-customer-app");

        client.createCustomer(null, new CreateCustomerRequest("fullName", "17737278341", "address"));
    }
}