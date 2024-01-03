package com.polovyi.ivan.queryparams.example_5;

import com.polovyi.ivan.configuration.ClientConfig;
import com.polovyi.ivan.dto.CustomerResponse;
import com.polovyi.ivan.queryparams.example_5.CustomerAppClient_5.GetCustomersWithFiltersQueryParams;
import feign.Feign;
import feign.Logger.Level;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import feign.querymap.BeanQueryMapEncoder;
import feign.slf4j.Slf4jLogger;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        // bean query map encoder uses getters
        CustomerAppClient_5 client = Feign.builder()
                .logLevel(Level.FULL)
                .logger(new Slf4jLogger())
                .decoder(new JacksonDecoder(ClientConfig.OBJECT_MAPPER))
                .encoder(new JacksonEncoder(ClientConfig.OBJECT_MAPPER))
                .queryMapEncoder(new BeanQueryMapEncoder())
                .target(CustomerAppClient_5.class,
                        "http://localhost:8001/spring-customer-app");

        List<CustomerResponse> customerResponseListV1 = client.getCustomers(new GetCustomersWithFiltersQueryParams());
        System.out.println("customerResponseListV1 = " + customerResponseListV1);

        GetCustomersWithFiltersQueryParams queryParams = new GetCustomersWithFiltersQueryParams(
                "John Doe",
                "17737270000",
                "2015-08-06");

        List<CustomerResponse> customerResponseListV2 = client.getCustomers(queryParams);
        System.out.println("customerResponseListV2 = " + customerResponseListV2);

        List<CustomerResponse> customerResponseListV3 = client.getCustomers(new GetCustomersWithFiltersQueryParams(
                null,
                null,
                "2015-08-06"));
        System.out.println("customerResponseListV3 = " + customerResponseListV3);

    }
}