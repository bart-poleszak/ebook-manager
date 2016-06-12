package com.example.bp.ebookmanager.model;

/**
 * Ebook Manager
 * Created by bp on 07.05.16.
 */
public class Person {
    private String name;

    private Person() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static Person named(String name) {
        Person result = new Person();
        result.setName(name);
        return result;
    }
}
