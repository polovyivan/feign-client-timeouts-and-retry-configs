package com.polovyi.ivan.controller;

import com.polovyi.ivan.dto.CreateCustomerRequest;
import com.polovyi.ivan.dto.CustomerResponse;
import com.polovyi.ivan.service.CustomerService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.TimeUnit;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@Slf4j
@RestController
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;
    private int times;
    private int status;

    @GetMapping(path = "/customers")
    @ResponseStatus(HttpStatus.OK)
    public List<CustomerResponse> getAllCustomersWithFilters(
            @RequestParam(required = false) String fullName,
            @RequestParam(required = false) String phoneNumber,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate createdAt,
            @RequestHeader(required = false) String instruction) throws InterruptedException {

        if (StringUtils.isNoneBlank(instruction)) {
            log.info("Sleeping for {} seconds", instruction);
            TimeUnit.SECONDS.sleep(Integer.valueOf(instruction));
        }

        return customerService.getCustomersWithFilters(fullName, phoneNumber, createdAt);
    }

    @PostMapping(path = "/customers")
    public void createCustomer(@Valid @RequestBody CreateCustomerRequest createCustomerRequest,
            UriComponentsBuilder uriBuilder, HttpServletResponse response, @RequestHeader(required = false) String instruction) {

        if (mockResponse(response, instruction)) {
            return;
        }

        String customerId = customerService.createCustomer(createCustomerRequest).getId();
        response.addHeader("location", uriBuilder.path("/customers/{id}")
                .buildAndExpand(customerId).toUriString());
        response.setStatus(201);

    }

    private boolean mockResponse(HttpServletResponse response, String instruction) {
        if (times == 0) {
            if (StringUtils.isNoneBlank(instruction)) {
                times = Integer.valueOf(StringUtils.substringAfter(instruction, "_"));
                status = Integer.valueOf(StringUtils.substringBefore(instruction, "_"));
                response.setStatus(status);
                return true;
            }
        } else if (times > 1) {
            response.setStatus(status);
            times--;
            return true;
        } else if (times == 1) {
            response.setStatus(status);
            times--;
        }
        return false;
    }

}
