package com.polovyi.ivan.retryer.example_5;

import static feign.FeignException.errorStatus;

import com.fasterxml.jackson.databind.ObjectMapper;
import feign.FeignException;
import feign.Response;
import feign.RetryableException;
import feign.codec.ErrorDecoder;
import java.io.IOException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CustomErrorDecoder implements ErrorDecoder {

    private final ObjectMapper objectMapper;

    @Override
    public Exception decode(String methodKey, Response response) {
        try {
            if (response.status() > 499) {
                FeignException feignException = errorStatus(methodKey, response);
                return new RetryableException(
                        response.status(),
                        feignException.getMessage(),
                        response.request().httpMethod(),
                        feignException,
                        null,
                        response.request()
                );
            }

            String responseAsString = new String(response.body()
                    .asInputStream().readAllBytes(), "UTF-8");
            ClientExceptionDetails clientExceptionDetails = objectMapper
                    .readValue(responseAsString, ClientExceptionDetails.class);
            clientExceptionDetails.setMethod(methodKey);
            throw new ClientException(clientExceptionDetails);
        } catch (IOException e) {
            throw new RuntimeException("Error occurred while decoding error response.", e);
        }
    }
}
