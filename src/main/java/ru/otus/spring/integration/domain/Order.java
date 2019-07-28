package ru.otus.spring.integration.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Arrays;
import java.util.List;

@Data
@AllArgsConstructor
public class Order {

    private String name;

    private List<Component> components;

    private boolean iced;

    @Override
    public String toString() {
        StringBuilder fullName = new StringBuilder();
        fullName.append(name);
        if (isIced()) {
            fullName.append(" with ice");
        }
        if (!components.isEmpty()) {
            fullName.append(". Components: ");
            fullName.append(Arrays.toString(components.toArray()));
        }
        return fullName.toString();
    }
}
