package dev.mprevorovsky.cocktail_proxy_java.controller;


import dev.mprevorovsky.cocktail_proxy_java.model.DrinkJpaCompatible;
import dev.mprevorovsky.cocktail_proxy_java.service.DrinkOfTheDayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.DateTimeException;
import java.time.LocalDate;

/**
 * Controller for the endpoint "/drink-of-the-day"
 * <p>
 * Returns the drink associated with a supplied date from the local in-memory DB as JSON.
 * <p>
 * If no such record exists yet, a random drink is fetched from www.thecocktaildb.com/api/json/v1/1/random.php,
 * associated with the date, saved to the local in-memory DB and returned as JSON.
 * In case of duplicity (fetched random drink already exists in the local DB, but is not yet associated with
 * <p>
 * any date), the existing drink record is just updated (date property).
 * <p>
 * EXAMPLE USE: "/drink-of-the-day/2023/09/13"
 */
@Controller
@RequestMapping("/drink-of-the-day")
class DrinkOfTheDayController {

    @Autowired
    private DrinkOfTheDayService drinkOfTheDayService;

    /**
     * Handles exceptions triggered by attempting to access invalid date paths.
     */
    @ExceptionHandler(DateTimeException.class)
    private ResponseEntity<String> handleInvalidDatePath(DateTimeException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }


    @ResponseBody
    @GetMapping("/{year}/{month}/{day}")
    public DrinkJpaCompatible getDrinkOfTheDay(
            @PathVariable int year,
            @PathVariable int month,
            @PathVariable int day
    ) {

        // conversion to LocalDate is performed for date validation
        String date = LocalDate.of(year, month, day).toString();

        return drinkOfTheDayService.getDrinkOfTheDay(date);
    }
}
