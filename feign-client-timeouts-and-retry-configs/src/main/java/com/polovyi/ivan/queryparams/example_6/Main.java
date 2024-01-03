package com.polovyi.ivan.queryparams.example_6;

import com.polovyi.ivan.configuration.ClientConfig;
import com.polovyi.ivan.dto.CustomerResponse;
import feign.Feign;
import feign.Logger.Level;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import feign.slf4j.Slf4jLogger;
import java.time.LocalDateTime;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        CustomerAppClient_6 client = Feign.builder()
                .logLevel(Level.FULL)
                .logger(new Slf4jLogger())
                .decoder(new JacksonDecoder(ClientConfig.OBJECT_MAPPER))
                .encoder(new JacksonEncoder(ClientConfig.OBJECT_MAPPER))
                .target(CustomerAppClient_6.class,
                        "http://localhost:8001/spring-customer-app");

        List<CustomerResponse> customerResponseListV1 = client.getCustomers(
                "John Doe",
                "17737270000",
                LocalDateTime.of(2015, 8, 6, 1, 20));
        System.out.println("customerResponseListV1 = " + customerResponseListV1);

        List<CustomerResponse> customerResponseListV2 = client.getCustomers(
                null,
                null,
                LocalDateTime.of(2015, 8, 6, 1, 20));
        System.out.println("customerResponseListV2 = " + customerResponseListV2);

        List<CustomerResponse> customerResponseListV3 = client.getCustomers(
                null,
                null,
                null);
        System.out.println("customerResponseListV3 = " + customerResponseListV3);
    }
}