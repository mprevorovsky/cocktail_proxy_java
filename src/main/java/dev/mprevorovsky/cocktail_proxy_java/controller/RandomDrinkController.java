package dev.mprevorovsky.cocktail_proxy_java.controller;


import dev.mprevorovsky.cocktail_proxy_java.service.RandomDrinkService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controller for the endpoint "/random-drink"
 * <p>
 * A controller for retrieving brief info on a random drink from
 * www.thecocktaildb.com/api/json/v1/1/random.php
 * and the currently celebrated name from Svátky API.
 * Selected drink attributes and the celebrated name are then served as an HTML page using
 * a Thymeleaf template.
 * Any new drink data are saved to a local in-memory DB.
*/
@Controller
@RequestMapping("/random-drink")
public class RandomDrinkController {
    RandomDrinkService randomDrinkService;

    public RandomDrinkController(RandomDrinkService randomDrinkService) {
        this.randomDrinkService = randomDrinkService;
    }

    @GetMapping
    public void getRandomDrinkAndCelebratedName(Model model) {
        randomDrinkService.getRandomDrinkAndCelebratedName(model);
    }
}
