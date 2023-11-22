package dev.mprevorovsky.cocktail_proxy_java.utils;

import dev.mprevorovsky.cocktail_proxy_java.model.CocktailDbRecord;
import dev.mprevorovsky.cocktail_proxy_java.model.Drink;
import org.springframework.web.client.RestTemplate;

import static dev.mprevorovsky.cocktail_proxy_java.CocktailProxyJavaApplication.cocktailDbApiRandomDrinkUrl;

/**
 * Retrieves data for a random drink from the CocktailDB.
 */
public class RandomDrinkFromCocktailDb {

    public static Drink getRandomDrinkFromCocktailDb(RestTemplate restTemplate) {
        return restTemplate.getForEntity(cocktailDbApiRandomDrinkUrl, CocktailDbRecord.class)
                .getBody()
                .getDrinks()
                .iterator()
                .next();
    }
}
