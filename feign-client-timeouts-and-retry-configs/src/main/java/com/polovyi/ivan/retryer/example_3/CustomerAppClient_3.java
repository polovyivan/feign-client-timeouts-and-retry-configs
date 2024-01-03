package com.polovyi.ivan.retryer.example_3;

import com.polovyi.ivan.dto.CreateCustomerRequest;
import feign.Headers;
import feign.RequestLine;

public interface CustomerAppClient_3 {

    @RequestLine("POST /customers")
    @Headers({"Content-Type:  application/json"})
    void createCustomer(CreateCustomerRequest body);
}
