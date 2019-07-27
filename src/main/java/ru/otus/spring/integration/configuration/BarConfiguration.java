package ru.otus.spring.integration.configuration;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.annotation.Router;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.Pollers;
import org.springframework.integration.dsl.channel.MessageChannels;
import org.springframework.integration.scheduling.PollerMetadata;
import ru.otus.spring.integration.domain.Drink;
import ru.otus.spring.integration.domain.Order;

@ComponentScan
@IntegrationComponentScan
@Configuration
@EnableIntegration
@AllArgsConstructor
public class BarConfiguration {

    @Bean
    public DirectChannel drinkChannel() {
        return MessageChannels.direct().datatype(Drink.class).get();
    }

    @Bean
    public DirectChannel readyChannel() {
        return MessageChannels.direct().datatype(Drink.class).get();
    }

    @Bean
    public DirectChannel icedChannel() {
        return MessageChannels.direct().datatype(Drink.class).get();
    }

    @Bean
    public DirectChannel routerChannel() {
        return MessageChannels.direct().datatype(Drink.class).get();
    }

    @Bean
    public QueueChannel orderChannel() {
        return MessageChannels.queue(10).datatype(Order.class).get();
    }

    @Bean
    public IntegrationFlow makeDrink() {
        return IntegrationFlows.from("orderChannel")
                .handle("barService", "make")
                .channel("routerChannel")
                .get();
    }

    @Router(inputChannel = "routerChannel")
    public String route(Drink drink) {
        return drink.isShouldBeIced() ? "icedChannel" : "readyChannel";
    }

    @Bean(name = PollerMetadata.DEFAULT_POLLER)
    public PollerMetadata poller() {
        return Pollers.fixedRate(1000).get();
    }

    @Bean
    public IntegrationFlow aggregateFlow() {
        return IntegrationFlows.from("readyChannel")
                .channel("drinkChannel")
                .get();
    }

    @Bean
    public IntegrationFlow icedFlow() {
        return IntegrationFlows.from("icedChannel")
                .handle("iceService", "addIce")
                .channel("readyChannel")
                .get();
    }
}
