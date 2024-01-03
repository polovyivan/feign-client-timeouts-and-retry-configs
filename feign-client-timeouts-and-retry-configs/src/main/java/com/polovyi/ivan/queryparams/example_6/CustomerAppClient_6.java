package com.polovyi.ivan.queryparams.example_6;

import com.polovyi.ivan.dto.CustomerResponse;
import feign.Param;
import feign.RequestLine;
import java.time.LocalDateTime;
import java.util.List;

public interface CustomerAppClient_6 {

    @RequestLine("GET /customers?fullName={fullName}&phoneNumber={phoneNumber}&createdAt={createdAt}")
    List<CustomerResponse> getCustomers(
            @Param("fullName") String fullName,
            @Param("phoneNumber") String phoneNumber,
            @Param(value = "createdAt", expander = LocalDateTimeExpander.class)
            LocalDateTime createdAt);
}
