package dev.mprevorovsky.cocktail_proxy_java.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Data transfer object class for parsing ingredient-related JSON data
 * obtained from the CocktailDB API.
 */
public class Ingredient {
    @JsonProperty("idIngredient")
    private int idIngredient;
    @JsonProperty("strIngredient")
    private String strIngredient;
    @JsonProperty("strDescription")
    private String strDescription;
    @JsonProperty("strType")
    private String strType;
    @JsonProperty("strAlcohol")
    private String strAlcohol;
    @JsonProperty("strABV")
    private String strAbv;

    public Ingredient() {
        super();
    }

    public Ingredient(int idIngredient, String strIngredient, String strDescription, String strType, String strAlcohol, String strAbv) {
        super();
        this.idIngredient = idIngredient;
        this.strIngredient = strIngredient;
        this.strDescription = strDescription;
        this.strType = strType;
        this.strAlcohol = strAlcohol;
        this.strAbv = strAbv;
    }

    public String getStrIngredient() {
        return strIngredient;
    }

    public void setStrIngredient(String strIngredient) {
        this.strIngredient = strIngredient;
    }
}
