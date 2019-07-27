package ru.otus.spring.integration.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Drink {

    private String drink;

    private boolean shouldBeIced;

    private Ice ice;

    public Drink(String drink, boolean shouldBeIced) {
        this.drink = drink;
        this.shouldBeIced = shouldBeIced;
    }

    @Override
    public String toString() {
        if (isShouldBeIced() && ice != null) {
            return drink + " with " + ice.getCount() + " cubicles of ice";
        }
        return drink;
    }
}
