package dev.mprevorovsky.cocktail_proxy_java.model;

/**
 * Extensive data transfer object class for parsing drink-related JSON data
 * obtained from the CocktailDB API.
 */
public class Drink {

    private int idDrink;
    private String strDrink;
    private String strInstructions;
    private String strDrinkThumb;

    /**
     * Converts the Drink object into a DrinkJpaCompatible object.
     */
    public DrinkJpaCompatible toDrinkJpaCompatible() {
        return new DrinkJpaCompatible(this.idDrink, this.strDrink);
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

}
