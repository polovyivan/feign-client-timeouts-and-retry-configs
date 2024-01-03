package com.polovyi.ivan.queryparams.example_3;

import com.polovyi.ivan.configuration.ClientConfig;
import com.polovyi.ivan.dto.CustomerResponse;
import com.polovyi.ivan.queryparams.example_3.CustomerAppClient_3.GetCustomersQueryParamsMap;
import feign.Feign;
import feign.Logger.Level;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import feign.slf4j.Slf4jLogger;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        CustomerAppClient_3 client = Feign.builder()
                .logLevel(Level.FULL)
                .logger(new Slf4jLogger())
                .decoder(new JacksonDecoder(ClientConfig.OBJECT_MAPPER))
                .encoder(new JacksonEncoder(ClientConfig.OBJECT_MAPPER))
                .target(CustomerAppClient_3.class,
                        "http://localhost:8001/spring-customer-app");

        List<CustomerResponse> customerResponseListV1 = client.getCustomers(new GetCustomersQueryParamsMap());
        System.out.println("customerResponseListV1 = " + customerResponseListV1);

        GetCustomersQueryParamsMap queryParamsMap = new GetCustomersQueryParamsMap()
                .fullName("John Doe")
                .phoneNumber("17737270000")
                .createdAt("2015-08-06");

        List<CustomerResponse> customerResponseListV2 = client.getCustomers(queryParamsMap);
        System.out.println("customerResponseListV2 = " + customerResponseListV2);

        List<CustomerResponse> customerResponseListV3 = client.getCustomers(new GetCustomersQueryParamsMap()
                .fullName("John Doe"));
        System.out.println("customerResponseListV3 = " + customerResponseListV3);

    }
}