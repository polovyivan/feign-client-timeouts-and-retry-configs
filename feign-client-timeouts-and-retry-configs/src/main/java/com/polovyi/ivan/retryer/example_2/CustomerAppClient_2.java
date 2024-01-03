package com.polovyi.ivan.retryer.example_2;

import com.polovyi.ivan.dto.CreateCustomerRequest;
import feign.Headers;
import feign.RequestLine;

public interface CustomerAppClient_2 {

    @RequestLine("POST /customers")
    @Headers({"Content-Type:  application/json"})
    void createCustomer(CreateCustomerRequest body);
}
