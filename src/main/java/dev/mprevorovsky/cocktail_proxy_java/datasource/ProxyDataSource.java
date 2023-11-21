package dev.mprevorovsky.cocktail_proxy_java.datasource;


import dev.mprevorovsky.cocktail_proxy_java.model.CocktailDbRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * Interface for the Repository layer objects.
 * <p>
 * Intended to relay requests to the CocktailDB API.
 */

@Repository
public class ProxyDataSource {

    @Autowired
    RestTemplate restTemplate;

    /**
     * Retrieves data from the CocktailDB.
     * <p>
     * Requests to the "random.php" path are not cached to preserve the random character of the responses.
     * All other requests are cached.
     */
    @Cacheable(value="cocktailDb", condition = "#consumedApiPath!='random.php'")
    public CocktailDbRecord performProxyGetRequest(
            String consumedApiBaseUrl,
            String consumedApiPath,
            String queryString
    ) {

        String requestUri = buildRequestUri(consumedApiBaseUrl, consumedApiPath, queryString);

        return restTemplate.getForObject(requestUri, CocktailDbRecord.class);
        //  ?: throw IOException("400 Bad Request: No data could be read from $requestUri")
    }


    /**
     * Builds URI from base URL, path and an optional query string.
     */
    private static String buildRequestUri(
            String consumedApiBaseUrl,
            String consumedApiPath,
            String queryString
    ) {

        return UriComponentsBuilder
                .fromHttpUrl(consumedApiBaseUrl + consumedApiPath)
                .query(queryString)
                .build()
                .toString();
    }
}