package com.example.wsclient.config;

import com.example.wsclient.AmenityClient;
import com.example.wsclient.HotelClient;
import com.example.wsclient.SoapClientInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.client.support.interceptor.ClientInterceptor;

@Configuration
public class SoapClientConfig {
    @Bean
    public Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("com.hotels");
        return marshaller;
    }

    @Bean
    public HotelClient hotelClient(Jaxb2Marshaller marshaller) {
        HotelClient client = new HotelClient();
        client.setDefaultUri("http://localhost:8081/ws");
        client.setMarshaller(marshaller);
        client.setUnmarshaller(marshaller);
        ClientInterceptor[] clientInterceptors = { new SoapClientInterceptor() };
        client.setInterceptors(clientInterceptors);
        return client;
    }

    @Bean
    public AmenityClient amenityClient(Jaxb2Marshaller marshaller) {
        AmenityClient client = new AmenityClient();
        client.setDefaultUri("http://localhost:8081/ws");
        client.setMarshaller(marshaller);
        client.setUnmarshaller(marshaller);
        ClientInterceptor[] clientInterceptors = { new SoapClientInterceptor() };
        client.setInterceptors(clientInterceptors);
        return client;
    }


}