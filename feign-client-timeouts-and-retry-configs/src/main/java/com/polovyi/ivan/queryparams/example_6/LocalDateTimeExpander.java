package com.polovyi.ivan.queryparams.example_6;

import feign.Param;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

public class LocalDateTimeExpander implements Param.Expander {

    @Override
    public String expand(Object value) {
        return Optional.ofNullable(value)
                .map(LocalDateTime.class::cast)
                .map(LocalDateTime::toLocalDate)
                .map(LocalDate::toString).orElse(null);
    }
}
