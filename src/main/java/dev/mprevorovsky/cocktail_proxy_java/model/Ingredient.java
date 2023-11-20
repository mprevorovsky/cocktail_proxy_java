package dev.mprevorovsky.cocktail_proxy_java.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Data transfer object class for parsing ingredient-related JSON data
 * obtained from the CocktailDB API.
 */
public class Ingredient {
    private int idIngredient;
    private String strIngredient;
    private String strDescription;
    private String strType;
    private String strAlcohol;
    @JsonProperty("strABV")
    private String strAbv;
}
