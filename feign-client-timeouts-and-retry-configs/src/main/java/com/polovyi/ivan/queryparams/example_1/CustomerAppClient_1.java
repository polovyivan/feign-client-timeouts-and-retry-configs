package com.polovyi.ivan.queryparams.example_1;

import com.polovyi.ivan.dto.CustomerResponse;
import feign.Param;
import feign.RequestLine;
import java.util.List;

public interface CustomerAppClient_1 {

    @RequestLine("GET /customers?fullName={fullName}&phoneNumber={phoneNumber}&createdAt={createdAt}")
    List<CustomerResponse> getCustomers(
            @Param("fullName") String fullName,
            @Param("phoneNumber") String phoneNumber,
            @Param("createdAt") String createdAt);
}
