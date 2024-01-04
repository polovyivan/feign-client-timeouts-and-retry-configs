package com.polovyi.ivan.retryer.example_5;

import feign.RetryableException;
import feign.Retryer;
import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomRetryer implements Retryer {

    @Override
    public void continueOrPropagate(RetryableException e) {
        System.out.println("e = " + e);
        int status = e.status();
        if (status == 501) {
            throw e;
        }

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
            throw e;
        }
    }

    @Override
    public Retryer clone() {
        return new CustomRetryer();
    }
}

