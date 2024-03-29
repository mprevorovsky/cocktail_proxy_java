## *NOTE: This is a demo JAVA/SpringBoot project*

The application is deployed at https://stark-atoll-68507-d6bb2d828e80.herokuapp.com/

## The CocktailDb proxy application is linked to two REST APIs:

1) the CocktailDB API (documentation at https://www.thecocktaildb.com/api.php) - a database of drinks.

2) Svátky API (documentation at https://svatkyapi.cz/) - a nameday database.


## The application provides 4 endpoints:
1) **"/proxy"** - redirects *all* calls (all paths and queries) to the remote CocktailDB API and returns the results
   as JSON data.

   The free public part of the CocktailDB API only provides GET endpoints, which all retrieve information
   on drinks and/or ingredients in some form.
   For simplicity, all JSON content returned from the remote server is mapped onto one common data structure
   (CocktailDbRecord class), and returned by the application.

   Names of drinks and/or ingredients are turned to UPPERCASE as a demonstration of data processing.

   When *new* drink data are retrieved, drink id (idDrink), name (strDrink), recipe (strInstructions) and thumbnail link (strDrinkThumb) are saved to a local in-memory
   database and can be retrieved from the "/local-db" endpoint.

   All requests, except for the "random.php" path, are cached.

   EXAMPLE USE: <a href="https://stark-atoll-68507-d6bb2d828e80.herokuapp.com/proxy/filter.php?g=Champagne_flute">/proxy/filter.php?g=Champagne_flute</a>

   *NOTE: This single-endpoint solution simplified the implementation of the proxy a lot, but it could become
   limiting if future extensions of the application are required.*

2) **"<a href="https://stark-atoll-68507-d6bb2d828e80.herokuapp.com/random-drink">/random-drink</a>"** - calls the "random.php" path on the CocktailDB API to retrieve data for a random drink,
   and then retrieves the currently celebrated name from Svátky API.

   The results are presented as a simple webpage (Thymeleaf template).

   Any new drink data are saved to the local in-memory DB.

3) **"<a href="https://stark-atoll-68507-d6bb2d828e80.herokuapp.com/local-db">/local-db</a>"** - retrieves all drink records stored in the local in-memory H2 database.

4) **"/drink-of-the-day"** - retrieves the drink associated with a supplied date from the local in-memory DB as JSON.

   If no such record exists, a random drink is fetched from https://www.thecocktaildb.com/api/json/v1/1/random.php,
   associated with the date, saved to the local in-memory DB and returned as JSON.

   EXAMPLE USE: <a href="https://stark-atoll-68507-d6bb2d828e80.herokuapp.com/drink-of-the-day/2023/09/13">/drink-of-the-day/2023/09/13</a>