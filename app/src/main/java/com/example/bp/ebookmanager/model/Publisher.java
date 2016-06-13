package com.example.bp.ebookmanager.model;

/**
 * Ebook Manager
 * Created by bp on 07.05.16.
 */
public class Publisher {
    private String name;

    private Publisher() {

    }

    public static Publisher named(String name) {
        Publisher publisher = new Publisher();
        publisher.name = name;
        return publisher;
    }

    public String getName() {
        return name;
    }

}
