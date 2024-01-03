package com.polovyi.ivan.queryparams.example_2;

import com.polovyi.ivan.configuration.ClientConfig;
import com.polovyi.ivan.dto.CustomerResponse;
import feign.Feign;
import feign.Logger.Level;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import feign.slf4j.Slf4jLogger;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        CustomerAppClient_2 client = Feign.builder()
                .logLevel(Level.FULL)
                .logger(new Slf4jLogger())
                .decoder(new JacksonDecoder(ClientConfig.OBJECT_MAPPER))
                .encoder(new JacksonEncoder(ClientConfig.OBJECT_MAPPER))
                .target(CustomerAppClient_2.class,
                        "http://localhost:8001/spring-customer-app");

        List<CustomerResponse> customerResponseListV1 = client.getCustomers(Map.of());
        System.out.println("customerResponseListV1 = " + customerResponseListV1);
        Map<String, Object> queryParams = Map.of(
                "fullName", "John Doe",
                "phoneNumber", "17737270000",
                "createdAt", "2015-08-06");

        List<CustomerResponse> customerResponseListV2 = client.getCustomers(queryParams);
        System.out.println("customerResponseListV2 = " + customerResponseListV2);

    }
}