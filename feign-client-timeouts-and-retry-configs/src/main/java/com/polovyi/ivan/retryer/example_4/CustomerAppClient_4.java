package com.polovyi.ivan.retryer.example_4;

import com.polovyi.ivan.dto.CreateCustomerRequest;
import feign.Headers;
import feign.Param;
import feign.RequestLine;

public interface CustomerAppClient_4 {

    @RequestLine("POST /customers")
    @Headers({"Content-Type:  application/json", "instruction: {instruction}"})
    void createCustomer(@Param("instruction") String instruction, CreateCustomerRequest body);
}
