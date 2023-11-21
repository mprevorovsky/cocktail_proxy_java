package dev.mprevorovsky.cocktail_proxy_java.datasource;


import dev.mprevorovsky.cocktail_proxy_java.model.DrinkJpaCompatible;
import org.springframework.data.repository.CrudRepository;

/**
 * Interface defining a CRUD repository.
 * <p>
 * Intended to manipulate the local in-memory DB with drink data.
 */
public interface DrinksRepository extends CrudRepository<DrinkJpaCompatible, Long>
{

    // Function is auto-implemented by Spring.
    Boolean existsByIdDrink(int idDrink);

    // Function is auto-implemented by Spring.
    DrinkJpaCompatible findByDate(String date);

    // Function is auto-implemented by Spring.
    DrinkJpaCompatible findByIdDrink(int idDrink);
}
