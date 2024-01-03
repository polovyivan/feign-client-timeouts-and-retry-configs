package com.polovyi.ivan.queryparams.example_3;

import com.polovyi.ivan.dto.CustomerResponse;
import feign.QueryMap;
import feign.RequestLine;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface CustomerAppClient_3 {

    @RequestLine("GET /customers?fullName={fullName}&phoneNumber={phoneNumber}&createdAt={createdAt}")
    List<CustomerResponse> getCustomers(@QueryMap Map<String, Object> params);

    class GetCustomersQueryParamsMap extends HashMap<String, Object> {

        public GetCustomersQueryParamsMap fullName(final String value) {
            this.put("fullName", value);
            return this;
        }

        public GetCustomersQueryParamsMap phoneNumber(final String value) {
            this.put("phoneNumber", value);
            return this;
        }

        public GetCustomersQueryParamsMap createdAt(final String value) {
            this.put("createdAt", value);
            return this;
        }
    }
}
