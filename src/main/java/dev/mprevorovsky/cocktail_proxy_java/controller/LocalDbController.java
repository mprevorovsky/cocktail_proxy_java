package dev.mprevorovsky.cocktail_proxy_java.controller;

import dev.mprevorovsky.cocktail_proxy_java.datasource.DrinksRepository;
import dev.mprevorovsky.cocktail_proxy_java.model.DrinkJpaCompatible;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for the endpoint "/local-db"
 * <p>
 * Returns all currently available drink data from the local in-memory DB as JSON.
 */
@RestController
@RequestMapping("/local-db")
class LocalDbController {

    @Autowired
    DrinksRepository drinkRepository;

    @GetMapping
    Iterable<DrinkJpaCompatible> getAllDrinks() {
        return drinkRepository.findAll();
    }

}

