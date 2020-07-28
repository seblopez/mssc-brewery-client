package guru.springframework.msscbreweryclient.web.client;

import guru.springframework.msscbreweryclient.web.model.BeerDto;
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
class BeerClientTest {

    @Autowired
    BeerClient beerClient;

    @Test
    void getBeerByIdOk() {
        final BeerDto beerDto = beerClient.getBeerById(UUID.randomUUID());

        assertNotNull(beerDto);
    }

    @Test
    void saveNewBeerOk() {
        final URI location = beerClient.saveNewBeer(BeerDto.builder().build());

        assertNotNull(location);
        log.info(MessageFormat.format("URI retrieved: {0}", location.toString()));
    }

    @Test
    void updateBeerOk() {
        beerClient.updateBeer(UUID.randomUUID(), BeerDto.builder().build());
    }

    @Test
    void deleteBeer() {
        beerClient.deleteBeer(UUID.randomUUID());
    }
}
