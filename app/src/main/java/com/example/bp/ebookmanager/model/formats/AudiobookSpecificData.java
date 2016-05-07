package com.example.bp.ebookmanager.model.formats;

import com.example.bp.ebookmanager.model.Person;

/**
 * Created by bp on 07.05.16.
 */
public interface AudiobookSpecificData extends FormatSpecificData {
    void setNarrator(Person narrator);
    Person getNarrator();
    void setLenght(int lenght);
    Integer getLenght();
}
