package guru.springframework.msscbreweryclient.web.client;

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
class CustomerClientTest {

    @Autowired
    CustomerClient customerClient;

    @Test
    void getCustomerById() {
        final CustomerDto customerDto = customerClient.getCustomerById(UUID.randomUUID());

        assertNotNull(customerDto);

    }

    @Test
    void saveNewCustomer() {
        final URI location = customerClient.saveNewCustomer(CustomerDto.builder().build());

        assertNotNull(location);
        log.info(MessageFormat.format("New customer URL: {0}", location.toString()));
    }

    @Test
    void updateCustomer() {
        customerClient.updateCustomer(UUID.randomUUID(), CustomerDto.builder().build());
    }

    @Test
    void deleteCustomer() {
        customerClient.deleteCustomer(UUID.randomUUID());
    }
}
