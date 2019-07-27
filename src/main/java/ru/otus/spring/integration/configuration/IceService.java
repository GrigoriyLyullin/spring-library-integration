package ru.otus.spring.integration.configuration;

import org.apache.commons.lang3.RandomUtils;
import org.springframework.stereotype.Service;
import ru.otus.spring.integration.domain.Drink;
import ru.otus.spring.integration.domain.Ice;

@Service
public class IceService {

    public Drink addIce(Drink drink) throws Exception {
        System.out.println("Adding some ice...");
        Thread.sleep(500);
        if (drink.isShouldBeIced() && drink.getIce() == null) {
            drink.setIce(new Ice(RandomUtils.nextInt(1, 5)));
        }
        return drink;
    }
}
