package ru.otus.spring.integration.configuration;

import org.springframework.stereotype.Service;
import ru.otus.spring.integration.domain.Drink;
import ru.otus.spring.integration.domain.Order;

@Service
public class BarService {

    public Drink make(Order order) throws InterruptedException {
        System.out.println("Making a drink...");
        Thread.sleep(1000);
        return new Drink(order.getName(), order.isIced());
    }
}
