package com.polovyi.ivan.timeout.example_1;

import com.polovyi.ivan.configuration.ClientConfig;
import com.polovyi.ivan.dto.CustomerResponse;
import feign.Feign;
import feign.Logger.Level;
import feign.Request.Options;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import feign.slf4j.Slf4jLogger;
import java.util.List;
import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Main {

    public static void main(String[] args) {
        CustomerAppClient_1 client = Feign.builder()
                .logLevel(Level.FULL)
                .logger(new Slf4jLogger())
                .decoder(new JacksonDecoder(ClientConfig.OBJECT_MAPPER))
                .encoder(new JacksonEncoder(ClientConfig.OBJECT_MAPPER))
                .options(new Options(5, TimeUnit.SECONDS, 10, TimeUnit.SECONDS, true))
                .target(CustomerAppClient_1.class,
                        "http://localhost:8001/spring-customer-app");

        List<CustomerResponse> customerResponseListV1 = client.getCustomers(
                null,
                null,
                null,
                null);
        log.info("customerResponseListV1 = " + customerResponseListV1);

        List<CustomerResponse> customerResponseListWithDelay = client.getCustomers("5",
                null,
                null,
                null);
        log.info("customerResponseListWithDelay = " + customerResponseListWithDelay);

        List<CustomerResponse> customerResponseListWithDelayFailed = client.getCustomers("11",
                null,
                null,
                null);
        log.info("customerResponseListWithDelayFailed = " + customerResponseListWithDelayFailed);

    }
}