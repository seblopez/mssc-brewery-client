package guru.springframework.msscbreweryclient.web.client;

import guru.springframework.msscbreweryclient.web.model.CustomerDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.text.MessageFormat;
import java.util.UUID;

@Component
public class CustomerClient {

    public final String CUSTOMER_PATH_V1 = "/api/v1/customer";

    @Value("${sfg.brewery.apihost}")
    private String apihost;

    private final RestTemplate restTemplate;

    public CustomerClient(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
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
