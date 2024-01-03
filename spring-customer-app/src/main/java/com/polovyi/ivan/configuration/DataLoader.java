package com.polovyi.ivan.configuration;

import static java.util.stream.Collectors.toList;

import com.github.javafaker.Faker;
import com.polovyi.ivan.entity.CustomerEntity;
import com.polovyi.ivan.repository.CustomerRepository;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.stream.IntStream;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataLoader {

    private final CustomerRepository customerRepository;

    @Bean
    private InitializingBean sendDatabase() {
        Faker faker = new Faker();
        return () -> {
            List<CustomerEntity> entities = generateCustomerList(faker);
            entities.add(new CustomerEntity(
                    UUID.randomUUID().toString(),
                    "John Doe",
                    "17737270000",
                    "Apt. 843 399 Lachelle Crossing, New Eldenhaven, LA 63962-9260",
                    LocalDate.of(2015, 8, 6)
            ));
            customerRepository.saveAll(entities);
        };
    }

    private List<CustomerEntity> generateCustomerList(Faker faker) {
        return IntStream.range(0, 2)
                .mapToObj(i -> CustomerEntity.builder().createdAt(
                                LocalDate.now().minus(Period.ofDays((new Random().nextInt(365 * 10)))))
                        .fullName(faker.name().fullName())
                        .phoneNumber(faker.phoneNumber().cellPhone())
                        .address(faker.address().fullAddress())
                        .build())
                .collect(toList());
    }
}
