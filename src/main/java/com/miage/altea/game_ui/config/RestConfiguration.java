package com.miage.altea.game_ui.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.support.BasicAuthenticationInterceptor;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Configuration
public class RestConfiguration {
    private String user;

    private String password;

    @Value("${trainer.service.username}")
    void setUser(String user) {
        this.user = user;
    }

    @Value("${trainer.service.password}")
    void setPassword(String password) {
        this.password = password;
    }

    @Autowired
    public void configureLocalInterceptor(RestTemplate restTemplate){
        restTemplate.setInterceptors(List.of((httpRequest, bytes, clientHttpRequestExecution) -> {
            httpRequest.getHeaders().add(HttpHeaders.ACCEPT_LANGUAGE, LocaleContextHolder.getLocale().toLanguageTag());
            return clientHttpRequestExecution.execute(httpRequest,bytes);
        }));
    }

    @Bean
    RestTemplate trainerApiRestTemplate(){
        RestTemplate template = new RestTemplate();

        //Create and initialize the interceptor
        List<ClientHttpRequestInterceptor> interceptors = new ArrayList<ClientHttpRequestInterceptor>();
        interceptors.add( new BasicAuthenticationInterceptor( user, password ) );
        template.setInterceptors( interceptors );
        return template;
    }

    @Bean
    RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
