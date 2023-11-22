package dev.mprevorovsky.cocktail_proxy_java.utils;

/**
 * Retrieves data for a random drink from the CocktailDB.
 */

import dev.mprevorovsky.cocktail_proxy_java.model.CocktailDbRecord;
import dev.mprevorovsky.cocktail_proxy_java.model.Drink;
import org.springframework.web.client.RestTemplate;

import static dev.mprevorovsky.cocktail_proxy_java.CocktailProxyJavaApplication.cocktailDbApiRandomDrinkUrl;

public class RandomDrinkFromCocktailDb {

    public static Drink getRandomDrinkFromCocktailDb(RestTemplate restTemplate) {
        return restTemplate.getForEntity(cocktailDbApiRandomDrinkUrl, CocktailDbRecord.class)
                .getBody()
                .getDrinks()
                .iterator()
                .next();
    }
}
