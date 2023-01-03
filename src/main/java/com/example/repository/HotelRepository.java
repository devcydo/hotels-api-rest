package com.example.repository;

import com.example.wsclient.HotelClient;
import com.example.wsclient.config.SoapClientConfig;
import com.example.model.Hotel;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class HotelRepository {


    public Hotel findById(long id) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SoapClientConfig.class);
        HotelClient client = context.getBean(HotelClient.class);
        Hotel hotel = client.getById(id);

        return hotel;
    }

    public List<Hotel> filterByName(String name) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SoapClientConfig.class);
        HotelClient client = context.getBean(HotelClient.class);
        List<Hotel> hotels = client.filterByName(name);

        return hotels;
    }

    public List<Hotel> findAll() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SoapClientConfig.class);
        HotelClient client = context.getBean(HotelClient.class);
        List<Hotel> hotels = client.getAll();

        return hotels;
    }

    public Hotel save(Hotel hotel){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SoapClientConfig.class);
        HotelClient client = context.getBean(HotelClient.class);
        return client.save(hotel);
    }

    public Hotel addAmenityToHotel(long id_hotel, long id_amenity) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SoapClientConfig.class);
        HotelClient client = context.getBean(HotelClient.class);
        return client.addAmenityToHotel(id_hotel, id_amenity);
    }

    public Hotel removeAmenityToHotel(long id_hotel, long id_amenity) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SoapClientConfig.class);
        HotelClient client = context.getBean(HotelClient.class);
        return client.removeAmenityToHotel(id_hotel, id_amenity);
    }

    public boolean deleteById(long id) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SoapClientConfig.class);
        HotelClient client = context.getBean(HotelClient.class);
        return client.deleteById(id);
    }

}
