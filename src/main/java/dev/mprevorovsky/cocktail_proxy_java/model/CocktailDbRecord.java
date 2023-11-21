package dev.mprevorovsky.cocktail_proxy_java.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Collection;


/**
 * Universal data transfer object class for parsing JSON data obtained from the CocktailDB API.
 * <p>
 * The CocktailDB API returns either some type of Drink record (minimal with just a few attributes, or
 * extensive with all attributes) or an Ingredient record. To simplify the implementation of the proxy
 * functionality, all retrieved JSON data are mapped to a common data structure.
 */
public class CocktailDbRecord {

    @JsonProperty("drinks")
    private Collection<Drink> drinks;
    @JsonProperty("ingredients")
    private Collection<Ingredient> ingredients;

    public Collection<Drink> getDrinks() {
        return drinks;
    }

    public Collection<Ingredient> getIngredients() {
        return ingredients;
    }
}