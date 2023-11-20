package dev.mprevorovsky.cocktail_proxy_java.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.util.Objects;

/**
 * Data Transfer Object class used for interaction with the Jakarta Persistence API.
 * <p>
 * Intended to extract a subset of Drink-related attributes from CocktailDbRecord and make
 * the data object suitable for interaction with the Jakarta Persistence API.
 * <p>
 * The optional date property is used by the "drink-of-the-day" endpoint to associate the
 * particular drink with a particular date.
 * <p>
 * Default values for idDrink and strDrink are provided to force Kotlin to
 * create a default constructor, so that there is no clash with Jakarta Persistence API.
 */
@Entity
public class DrinkJpaCompatible {
    private int idDrink;
    private String strDrink;
    private String date; //String?=null
    @Id
    @GeneratedValue
    private Long id;  //Long?=null

    public DrinkJpaCompatible(int idDrink, String strDrink) {
        this(idDrink, strDrink, null);
    }

    public DrinkJpaCompatible(int idDrink, String strDrink, String date) {
        this.idDrink = idDrink;
        this.strDrink = Objects.requireNonNull(strDrink);
        this.date = date;
    }

    public int getIdDrink() {
        return idDrink;
    }

    public String getStrDrink() {
        return strDrink;
    }

    public String getDate() {
        return date;
    }
}
