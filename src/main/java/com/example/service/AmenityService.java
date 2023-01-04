package com.example.service;

import com.example.model.Amenity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AmenityService {
    List<Amenity> getAll();

    List<Amenity> getByHotelId(long id_hotel);

    Amenity createAmenity(Amenity amenity);
    Amenity editAmenity(Amenity amenity);

    boolean deleteById(long id);
}
