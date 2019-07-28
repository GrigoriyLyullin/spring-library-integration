package ru.otus.spring.integration;

import org.apache.commons.lang3.RandomUtils;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import ru.otus.spring.integration.configuration.Bar;
import ru.otus.spring.integration.configuration.BarConfiguration;
import ru.otus.spring.integration.domain.Component;
import ru.otus.spring.integration.domain.Drink;
import ru.otus.spring.integration.domain.Order;

import java.util.ArrayList;
import java.util.List;


@SpringBootApplication
public class Application {

    private static final String[] NAME_BASE = {"Jack", "Manhattan", "Lagoon", "Bay", "Beach"};

    private static final String[] NAME_ADJECTIVE = {"Blue", "Hot", "Old", "Sour"};

    private static final String[] BASE = {"whiskey", "rum", "vodka", "gin"};

    private static final String[] MODIFYING = {"apple juice", "orange juice", "lemon juice", "lime juice", "vermouth"};

    private static final String[] FLAVORING = {"grenadine", "simple syrup", "Angostura bitters", "triple sec", "blue Curaçao"};

    private static Order generateOrder() {
        List<Component> components = new ArrayList<>();
        components.add(new Component(BASE[RandomUtils.nextInt(0, BASE.length)], RandomUtils.nextInt(5, 10)));
        components.add(new Component(MODIFYING[RandomUtils.nextInt(0, MODIFYING.length)], RandomUtils.nextInt(1, 5)));
        components.add(new Component(FLAVORING[RandomUtils.nextInt(0, FLAVORING.length)], RandomUtils.nextInt(1, 3)));
        String name = NAME_ADJECTIVE[RandomUtils.nextInt(0, NAME_ADJECTIVE.length)] + " "
                + NAME_BASE[RandomUtils.nextInt(0, NAME_BASE.length)];
        return new Order(name, components, true);
    }

    public static void main(String[] args) throws Exception {
        AbstractApplicationContext ctx = new AnnotationConfigApplicationContext(BarConfiguration.class);

        Bar bar = ctx.getBean(Bar.class);

        for (int i = 0; i < 100; i++) {
            Thread.sleep(1000);
//            Collection<Order> order = new ArrayList<>();
//            order.add(generateOrder());
//            order.add(generateOrder());
//            order.add(generateOrder());
//
//            System.out.println("********************************************************************************");
//            System.out.println("Ordered orders: " + Arrays.toString(order.toArray()));

            Order order = generateOrder();
            System.out.println("New order: " + order);
            Drink drink = bar.process(order);
            System.out.println("Ready drink: " + drink);

//            System.out.println("Ready drinks: " + Arrays.toString(orders.toArray()));
        }
    }
}
