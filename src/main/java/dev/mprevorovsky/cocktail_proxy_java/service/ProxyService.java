package dev.mprevorovsky.cocktail_proxy_java.service;


import dev.mprevorovsky.cocktail_proxy_java.datasource.DrinksRepository;
import dev.mprevorovsky.cocktail_proxy_java.datasource.ProxyDataSource;
import dev.mprevorovsky.cocktail_proxy_java.model.CocktailDbRecord;
import dev.mprevorovsky.cocktail_proxy_java.model.Drink;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;


/**
 * Service layer for accessing the CocktailDB API.
 * <p>
 * - Relays URI elements supplied by the upstream layers to the Repository layer (DataSource object).
 * <p>
 * - Retrieved drink data are mapped to a DrinkJpaCompatible object and saved to a local in-memory DB
 * (DrinksRepository object).
 * This functionality should likely be made less coupled, e.g. by defining a Service layer interface
 * with a "saveDrinkDataIfNotExists()" function. This would simplify potential future swapping of the
 * current H2 database for some other storage solution.
 * <p>
 * - To demonstrates the possibilities of data transformation in the Service layer, the CocktailDB response
 * data are processed before being returned (the names of any Drinks and/or Ingredients are turned to uppercase)
 */
@Service
public class ProxyService {
    @Autowired
    ProxyDataSource dataSource;
    @Autowired
    DrinksRepository drinksLocalRepository;

    public CocktailDbRecord performProxyGetRequest(
        String consumedApiBaseUrl,
        String consumedApiPath,
        String queryString
    ) {

        CocktailDbRecord response = dataSource.performProxyGetRequest(consumedApiBaseUrl, consumedApiPath, queryString);

        // If any drink data is returned, each *new* drink is saved to an in-memory database.
        if (response.getDrinks() != null) {
            saveDrinkDataIfNotExists(response.getDrinks());
        }

        return makeNamesUppercase(response);
    }


    /**
     * Turns drink and ingredient names to uppercase to demonstrate data processing.
     */
    private CocktailDbRecord makeNamesUppercase(CocktailDbRecord responseToProcess) {
        if (responseToProcess.getDrinks() != null) {
            responseToProcess.getDrinks().forEach(it -> {
                if (it.getStrDrink() != null) {
                    it.setStrDrink(it.getStrDrink().toUpperCase());
                }
            }
            );
        }
        if (responseToProcess.getIngredients() != null) {
            responseToProcess.getIngredients().forEach(it -> {
                        if (it.getStrIngredient() != null) {
                            it.setStrIngredient(it.getStrIngredient().toUpperCase());
                        }
                    }
            );
        }

        return responseToProcess;
    }


    /**
     * Saves new drink data to the local in-memory DB.
     */
    private void saveDrinkDataIfNotExists(Collection<Drink> drinkData) {
        drinkData.forEach(it -> {
                    if (!drinksLocalRepository.existsByIdDrink(it.getIdDrink())) {
                        drinksLocalRepository.save(it.toDrinkJpaCompatible());
                    }
                }
        );
    }
}
