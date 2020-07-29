package guru.springframework.msscbreweryclient.config;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultConnectionKeepAliveStrategy;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateCustomizer;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class BlockingRestTemplateCustomizer implements RestTemplateCustomizer {

    private final Integer maxTotal;
    private final Integer defaultMaxPerRoute;
    private final Integer connectionRequestTimeOut;
    private final Integer socketTimeOut;

    public BlockingRestTemplateCustomizer(@Value("${sfg.resttemplate.blocking.customizer.maxtotal}") Integer maxTotal,
                                          @Value("${sfg.resttemplate.blocking.customizer.default.max.per.route}") Integer defaultMaxPerRoute,
                                          @Value("${sfg.resttemplate.blocking.customizer.connection.request.timeout}") Integer connectionRequestTimeOut,
                                          @Value("${sfg.resttemplate.blocking.customizer.socket.timeout}") Integer socketTimeOut) {
        this.maxTotal = maxTotal;
        this.defaultMaxPerRoute = defaultMaxPerRoute;
        this.connectionRequestTimeOut = connectionRequestTimeOut;
        this.socketTimeOut = socketTimeOut;
    }

    public ClientHttpRequestFactory clientHttpRequestFactory() {
        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
        connectionManager.setMaxTotal(maxTotal);
        connectionManager.setDefaultMaxPerRoute(defaultMaxPerRoute);

        RequestConfig requestConfig = RequestConfig
                .custom()
                .setConnectionRequestTimeout(connectionRequestTimeOut)
                .setSocketTimeout(socketTimeOut)
                .build();

        CloseableHttpClient httpClient = HttpClients
                .custom()
                .setConnectionManager(connectionManager)
                .setKeepAliveStrategy(new DefaultConnectionKeepAliveStrategy())
                .setDefaultRequestConfig(requestConfig)
                .build();

        return new HttpComponentsClientHttpRequestFactory(httpClient);

    }

    @Override
    public void customize(RestTemplate restTemplate) {
        restTemplate.setRequestFactory(this.clientHttpRequestFactory());
    }
}
