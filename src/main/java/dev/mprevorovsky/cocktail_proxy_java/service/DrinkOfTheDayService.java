package dev.mprevorovsky.cocktail_proxy_java.service;

import dev.mprevorovsky.cocktail_proxy_java.datasource.DrinksRepository;
import dev.mprevorovsky.cocktail_proxy_java.model.Drink;
import dev.mprevorovsky.cocktail_proxy_java.model.DrinkJpaCompatible;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import static dev.mprevorovsky.cocktail_proxy_java.utils.RandomDrinkFromCocktailDb.getRandomDrinkFromCocktailDb;

/**
 * Service layer for the drink-of-the-day functionality.
 * <p>
 * Returns the drink associated with a supplied date from the local in-memory DB as JSON
 * (as a DrinkJpaCompatible object).
 * <p>
 * If no such record exists yet, a random drink is fetched from www.thecocktaildb.com/api/json/v1/1/random.php,
 * mapped on a DrinkJpaCompatible object, associated with the date, saved to the local in-memory DB and returned as JSON.
 * In case of duplicity (fetched random drink already exists in the local DB, but is not yet associated with
 * any date), the existing drink record is just updated (date property).
 */
@Service
public class DrinkOfTheDayService {

    @Autowired
    private DrinksRepository drinksLocalRepository;
    @Autowired
    private RestTemplate restTemplate;

    public DrinkJpaCompatible getDrinkOfTheDay(String date) {

        DrinkJpaCompatible drinkOfTheDay = drinksLocalRepository.findByDate(date);

        if (drinkOfTheDay == null) {

            Drink randomDrink = getRandomDrinkFromCocktailDb(restTemplate);
            drinkOfTheDay = new DrinkJpaCompatible(
                    randomDrink.getIdDrink(),
                    randomDrink.getStrDrink(),
                    date
            );

            // If fetched random drink is already present in the local in-memory DB, the existing
            // drink record is just updated (associated with the date).
            // (idDrink is a unique drink ID used by the CocktailDB)
            if (drinksLocalRepository.existsByIdDrink(drinkOfTheDay.getIdDrink())) {

                drinkOfTheDay.setId(drinksLocalRepository
                        .findByIdDrink(drinkOfTheDay.getIdDrink())
                        .getId()
                );

            }

            // Save/update drink-of-the-day data to the local in-memory DB.
            drinksLocalRepository.save(drinkOfTheDay);

        }

        return drinkOfTheDay;
    }
}
