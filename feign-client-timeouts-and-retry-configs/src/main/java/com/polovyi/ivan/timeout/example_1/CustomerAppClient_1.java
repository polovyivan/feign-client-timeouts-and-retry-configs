package com.polovyi.ivan.timeout.example_1;

import com.polovyi.ivan.dto.CustomerResponse;
import feign.Headers;
import feign.Param;
import feign.RequestLine;
import java.util.List;

public interface CustomerAppClient_1 {

    @RequestLine("GET /customers?fullName={fullName}&phoneNumber={phoneNumber}&createdAt={createdAt}")
    @Headers({"Content-Type:  application/json", "instruction: {instruction}"})
    List<CustomerResponse> getCustomers(
            @Param("instruction") String instruction,
            @Param("fullName") String fullName,
            @Param("phoneNumber") String phoneNumber,
            @Param("createdAt") String createdAt);
}
