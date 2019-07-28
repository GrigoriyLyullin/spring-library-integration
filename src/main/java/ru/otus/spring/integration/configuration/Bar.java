package ru.otus.spring.integration.configuration;


import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import ru.otus.spring.integration.domain.Drink;
import ru.otus.spring.integration.domain.Order;

@MessagingGateway
public interface Bar {

    @Gateway(requestChannel = "orderChannel", replyChannel = "drinkChannel")
    Drink process(Order order);
}
