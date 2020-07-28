package guru.springframework.msscbreweryclient.web.client;

import guru.springframework.msscbreweryclient.web.model.BeerDto;
import guru.springframework.msscbreweryclient.web.model.CustomerDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.net.URI;
import java.text.MessageFormat;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@Slf4j
@SpringBootTest
class BreweryClientTest {

    @Autowired
    BreweryClient breweryClient;

    @Test
    void getBeerByIdOk() {
        final BeerDto beerDto = breweryClient.getBeerById(UUID.randomUUID());

        assertNotNull(beerDto);
    }

    @Test
    void saveNewBeerOk() {
        final URI location = breweryClient.saveNewBeer(BeerDto.builder().build());

        assertNotNull(location);
        log.info(MessageFormat.format("URI retrieved: {0}", location.toString()));
    }

    @Test
    void updateBeerOk() {
        breweryClient.updateBeer(UUID.randomUUID(), BeerDto.builder().build());
    }

    @Test
    void deleteBeer() {
        breweryClient.deleteBeer(UUID.randomUUID());
    }

    @Test
    void getCustomerById() {
        final CustomerDto customerDto = breweryClient.getCustomerById(UUID.randomUUID());

        assertNotNull(customerDto);

    }

    @Test
    void saveNewCustomer() {
        final URI location = breweryClient.saveNewCustomer(CustomerDto.builder().build());

        assertNotNull(location);
        log.info(MessageFormat.format("New customer URL: {0}", location.toString()));
    }

    @Test
    void updateCustomer() {
        breweryClient.updateCustomer(UUID.randomUUID(), CustomerDto.builder().build());
    }

    @Test
    void deleteCustomer() {
        breweryClient.deleteCustomer(UUID.randomUUID());
    }
}
