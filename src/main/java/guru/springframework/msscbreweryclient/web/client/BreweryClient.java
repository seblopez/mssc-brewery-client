package guru.springframework.msscbreweryclient.web.client;

import guru.springframework.msscbreweryclient.web.model.BeerDto;
import guru.springframework.msscbreweryclient.web.model.CustomerDto;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.text.MessageFormat;
import java.util.UUID;

@Component
@ConfigurationProperties(value = "sfg.brewery", ignoreUnknownFields = false)
public class BreweryClient {

    public final String BEER_PATH_V1 = "/api/v1/beer";
    public final String CUSTOMER_PATH_V1 = "/api/v1/customer";

    @Setter
    private String apihost;

    private final RestTemplate restTemplate;

    public BreweryClient(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public BeerDto getBeerById(UUID id) {
        return restTemplate.getForObject(MessageFormat.format("{0}{1}/{2}", apihost, BEER_PATH_V1, id.toString()), BeerDto.class);
    }

    public URI saveNewBeer(BeerDto beerDto) {
        return restTemplate.postForLocation(MessageFormat.format("{0}{1}", apihost, BEER_PATH_V1), beerDto);
    }

    public void updateBeer(UUID id, BeerDto beerDto) {
        restTemplate.put(MessageFormat.format("{0}{1}/{2}", apihost, BEER_PATH_V1, id.toString()), beerDto);
    }

    public void deleteBeer(UUID id) {
        restTemplate.delete(MessageFormat.format("{0}{1}/{2}", apihost, BEER_PATH_V1, id.toString()));
    }

    public CustomerDto getCustomerById(UUID id) {
        return restTemplate.getForObject(MessageFormat.format("{0}{1}/{2}", apihost, CUSTOMER_PATH_V1, id), CustomerDto.class);
    }

    public URI saveNewCustomer(CustomerDto customerDto) {
        return restTemplate.postForLocation(MessageFormat.format("{0}{1}", apihost, CUSTOMER_PATH_V1), customerDto);
    }

    public void updateCustomer(UUID id, CustomerDto customerDto) {
        restTemplate.put(MessageFormat.format("{0}{1}/{2}", apihost, CUSTOMER_PATH_V1, id), customerDto);
    }

    public void deleteCustomer(UUID id) {
        restTemplate.delete(MessageFormat.format("{0}{1}/{2}", apihost, CUSTOMER_PATH_V1, id));
    }
}
