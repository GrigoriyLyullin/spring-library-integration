package ru.otus.spring.integration.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Component {

    private String name;

    private int parts;

    @Override
    public String toString() {
        return name;
    }
}
