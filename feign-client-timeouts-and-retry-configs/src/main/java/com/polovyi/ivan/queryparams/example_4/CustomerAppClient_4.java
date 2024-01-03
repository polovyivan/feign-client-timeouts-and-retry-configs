package com.polovyi.ivan.queryparams.example_4;

import com.polovyi.ivan.dto.CustomerResponse;
import feign.QueryMap;
import feign.RequestLine;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public interface CustomerAppClient_4 {

    @RequestLine("GET /customers?fullName={fullName}&phoneNumber={phoneNumber}&createdAt={createdAt}")
    List<CustomerResponse> getCustomers(@QueryMap GetCustomersWithFiltersQueryParams params);

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    class GetCustomersWithFiltersQueryParams {

        private String fullName;
        private String phoneNumber;
        private String createdAt;
    }
}
