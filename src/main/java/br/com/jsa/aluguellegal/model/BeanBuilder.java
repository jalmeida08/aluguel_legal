package br.com.jsa.aluguellegal.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@JsonPOJOBuilder(buildMethodName = "createBean", withPrefix = "construct")
public class BeanBuilder {
    private int idValue;
    private String nameValue;

    public BeanBuilder constructId(int id) {
        idValue = id;
        return this;
    }

    public BeanBuilder constructName(String name) {
        nameValue = name;
        return this;
    }

    public Pessoa createBean() {
        return new Pessoa();
    }
}