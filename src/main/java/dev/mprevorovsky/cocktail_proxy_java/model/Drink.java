package dev.mprevorovsky.cocktail_proxy_java.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Data transfer object class for parsing drink-related JSON data
 * obtained from the CocktailDB API.
 */
public class Drink {

    @JsonProperty("idDrink")
    private int idDrink;
    @JsonProperty("strDrink")
    private String strDrink;
    @JsonProperty("strInstructions")
    private String strInstructions;
    @JsonProperty("strDrinkThumb")
    private String strDrinkThumb;

    public Drink() {
        super();
    }

    public Drink(int idDrink, String strDrink, String strInstructions, String strDrinkThumb) {
        super();
        this.idDrink = idDrink;
        this.strDrink = strDrink;
        this.strInstructions = strInstructions;
        this.strDrinkThumb = strDrinkThumb;
    }

    /**
     * Converts the Drink object into a DrinkJpaCompatible object.
     */
    public DrinkJpaCompatible toDrinkJpaCompatible() {
        return new DrinkJpaCompatible(this.idDrink, this.strDrink, null);
    }

    public String getStrDrinkThumb() {
        return strDrinkThumb;
    }

    public String getStrInstructions() {
        return strInstructions;
    }

    public String getStrDrink() {
        return strDrink;
    }

    public int getIdDrink() {
        return idDrink;
    }

    public void setStrDrink(String strDrink) {
        this.strDrink = strDrink;
    }
}
