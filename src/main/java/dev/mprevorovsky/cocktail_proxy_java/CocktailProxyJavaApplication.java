package dev.mprevorovsky.cocktail_proxy_java;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class CocktailProxyJavaApplication {

    /**
     * Base URL for all CocktailDB API calls.
     */
    public static final String cocktailDbApiBaseUrl = "https://www.thecocktaildb.com/api/json/v1/1/";

    /**
     * URL of the CocktailDB resource that returns a random drink.
     */
    public static final String cocktailDbApiRandomDrinkUrl = "https://www.thecocktaildb.com/api/json/v1/1/random.php";

    /**
     * URL of the Svátky API (= name days) which returns information for the current date,
     * including the currently celebrated name.
     */
    public static final String nameDaysApiTodayUrl = "https://svatkyapi.cz/api/day";

    /**
     * The main method of the whole application.
     *
     * @param args
	 * Command line arguments.
     */
    public static void main(String[] args) {
        SpringApplication.run(CocktailProxyJavaApplication.class, args);
    }

}
