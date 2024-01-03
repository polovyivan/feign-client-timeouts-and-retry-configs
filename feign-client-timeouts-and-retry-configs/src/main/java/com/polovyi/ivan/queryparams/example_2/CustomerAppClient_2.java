package com.polovyi.ivan.queryparams.example_2;

import com.polovyi.ivan.dto.CustomerResponse;
import feign.QueryMap;
import feign.RequestLine;
import java.util.List;
import java.util.Map;

public interface CustomerAppClient_2 {

    @RequestLine("GET /customers?fullName={fullName}&phoneNumber={phoneNumber}&createdAt={createdAt}")
    List<CustomerResponse> getCustomers(@QueryMap Map<String, Object> params);
}
