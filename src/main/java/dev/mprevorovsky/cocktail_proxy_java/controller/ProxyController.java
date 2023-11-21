package dev.mprevorovsky.cocktail_proxy_java.controller;


import dev.mprevorovsky.cocktail_proxy_java.model.CocktailDbRecord;
import dev.mprevorovsky.cocktail_proxy_java.service.ProxyService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.io.IOException;

import static dev.mprevorovsky.cocktail_proxy_java.CocktailProxyJavaApplication.cocktailDbApiBaseUrl;

/**
 * Controller for the endpoint "/proxy"
 * <p>
 * All GET requests to this endpoint are just redirected to the CocktailDB.
 * The path and any query strings are extracted and passed on to the CocktailDB API.
 * <p>
 * The whole functionality downstream of the controller is split between:
 * - Service layer (ProxyService class)
 * - Repository layer (ProxyDataSource class)
 * to allow for better testability of the code and to provide modularity for potential
 * future changes of the application logic.
 * <p>
 * Also, server-side HTTP errors are handled by this controller.
 * <p>
 * EXAMPLE USE: /proxy/filter.php?g=Champagne_flute
*/
@RestController
@RequestMapping("/proxy")
class ProxyController {

    @Autowired
    HttpServletRequest httpRequest;
    @Autowired
    ProxyService service;


    /**
     * Handles exceptions triggered by requests to non-existing paths at the CocktailDB.
     */
    @ExceptionHandler(HttpClientErrorException.class)
    private ResponseEntity<String> handleUriNotFound(HttpClientErrorException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    /**
     * Handles exceptions triggered by requests to the CocktailDB with malformed URI
     * (typically the query string).
     */
    @ExceptionHandler(IOException.class)
    private ResponseEntity<String> handleNoDataCouldBeReadFromUri(IOException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @GetMapping("{path}")
    public CocktailDbRecord performProxyGetRequest(@PathVariable String path) {
        return service.performProxyGetRequest(cocktailDbApiBaseUrl, path, httpRequest.getQueryString());
    }
}

