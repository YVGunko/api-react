package com.yg.apireact;

import java.util.function.Supplier;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import org.zalando.logbook.httpclient.LogbookHttpRequestInterceptor;
import org.zalando.logbook.httpclient.LogbookHttpResponseInterceptor;


@Configuration
public class RestTemplateConfiguration {
    private final LogbookHttpRequestInterceptor logbookHttpRequestInterceptor;
    private final LogbookHttpResponseInterceptor logbookHttpResponseInterceptor;

    public RestTemplateConfiguration(LogbookHttpRequestInterceptor logbookHttpRequestInterceptor,
            LogbookHttpResponseInterceptor logbookHttpResponseInterceptor) {
        this.logbookHttpRequestInterceptor = logbookHttpRequestInterceptor;
        this.logbookHttpResponseInterceptor = logbookHttpResponseInterceptor;
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder) {
        return restTemplateBuilder
                .requestFactory(new MyRequestFactorySupplier())
                .build();
    }

    class MyRequestFactorySupplier implements Supplier<ClientHttpRequestFactory> {
        @Override
        public ClientHttpRequestFactory get() {
            // Using Apache HTTP client
            CloseableHttpClient client = HttpClientBuilder.create()
                    .addInterceptorFirst(logbookHttpRequestInterceptor)
                    .addInterceptorFirst(logbookHttpResponseInterceptor)
                    .build();
            return new HttpComponentsClientHttpRequestFactory(client);
        }
    }
}
