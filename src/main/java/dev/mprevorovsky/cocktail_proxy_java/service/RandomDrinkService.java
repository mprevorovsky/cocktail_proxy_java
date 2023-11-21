package dev.mprevorovsky.cocktail_proxy_java.service;


import dev.mprevorovsky.cocktail_proxy_java.datasource.DrinksRepository;
import dev.mprevorovsky.cocktail_proxy_java.model.Drink;
import dev.mprevorovsky.cocktail_proxy_java.model.DrinkJpaCompatible;
import dev.mprevorovsky.cocktail_proxy_java.model.NameDay;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

import static dev.mprevorovsky.cocktail_proxy_java.CocktailProxyJavaApplication.nameDaysApiTodayUrl;
import static dev.mprevorovsky.cocktail_proxy_java.utils.RandomDrinkFromCocktailDb.getRandomDrinkFromCocktailDb;

/**
 * Service layer for retrieving required data for the "/random-drink" endpoint.
 * <p>
 * Retrieves 1 random drink from the CocktailDB.
 * Retrieves the currently celebrated name from Svátky API.
 * <p>
 * Retrieved drink data are mapped to a DrinkJpaCompatible object and saved to a local in-memory DB
 * (DrinksRepository object) if not already present.
 */
@Service
public class RandomDrinkService {
    @Autowired
    private RestTemplate restTemplate;

    public RandomDrinkService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Autowired
    private DrinksRepository drinksLocalRepository;


    public void getRandomDrinkAndCelebratedName(Model model) {
        // get data for 1 random drink (from thecocktaildb.com)
        Drink randomDrink = getRandomDrinkFromCocktailDb(restTemplate);

        // get the name celebrated today (from svatkyapi.cz)
        String nameCelebratedToday = getNameCelebratedToday();

        // pass obtained data to an HTML template
        model.addAttribute("strDrink", randomDrink.getStrDrink());
        model.addAttribute("strInstructions", randomDrink.getStrInstructions());
        model.addAttribute("strDrinkThumb", randomDrink.getStrDrinkThumb());
        model.addAttribute("nameDayPhrase", nameCelebratedToday + " celebrates today... Cheers!");

        saveDrinkDataIfNotExists(randomDrink.toDrinkJpaCompatible());
    }

    /**
     * Retrieves the currently celebrated name from Svátky API.
     */
    private String getNameCelebratedToday() {
        return Objects.requireNonNull(restTemplate.getForEntity(nameDaysApiTodayUrl, NameDay.class)
                        .getBody())
                .getName();
        //?: throw IOException("Could not retrieve a random drink from $nameDaysApiTodayUrl")
    }


    /**
     * Saves new drink data to the local in-memory DB.
     */

    private void saveDrinkDataIfNotExists(DrinkJpaCompatible drink) {
        if (!drinksLocalRepository.existsByIdDrink(drink.getIdDrink()))
            drinksLocalRepository.save(drink);
    }
}