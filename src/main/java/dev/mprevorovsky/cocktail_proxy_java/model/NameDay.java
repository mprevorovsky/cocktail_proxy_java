package dev.mprevorovsky.cocktail_proxy_java.model;

/**
 * Data Transfer Object class used for parsing the JSON responses from Svátky API.
 * <p>
 * Note that the API returns many more data fields, but these are ignored by this application.
 */
public class NameDay {

    private String name;

    public String getName() {
        return name;
    }
}

