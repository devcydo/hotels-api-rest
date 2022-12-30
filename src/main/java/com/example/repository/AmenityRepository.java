package com.example.repository;

import com.example.client.AmenityClient;
import com.example.client.HotelClient;
import com.example.client.config.SoapClientConfig;
import com.example.model.Amenity;
import com.example.model.Hotel;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AmenityRepository {
    public List<Amenity> findAll() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SoapClientConfig.class);
        AmenityClient client = context.getBean(AmenityClient.class);
        List<Amenity> amenities = client.getAll();

        return amenities;
    }

    public List<Amenity> findByHotelId(long id_hotel) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SoapClientConfig.class);
        AmenityClient client = context.getBean(AmenityClient.class);
        List<Amenity> amenities = client.getByHotelId(id_hotel);

        return amenities;
    }

    public Amenity save(Amenity amenity) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SoapClientConfig.class);
        AmenityClient client = context.getBean(AmenityClient.class);
        return client.save(amenity);
    }

    public boolean deleteById(long id) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SoapClientConfig.class);
        AmenityClient client = context.getBean(AmenityClient.class);
        return client.deleteById(id);
    }
}
