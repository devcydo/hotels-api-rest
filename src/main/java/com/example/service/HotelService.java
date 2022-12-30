package com.example.service;

import com.example.model.Amenity;
import com.example.model.Hotel;
import com.example.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelService {

    @Autowired
    private HotelRepository hotelRepository;

    public List<Hotel> getAll() {
        return hotelRepository.findAll();
    }

    public List<Hotel> filterByName(String name) {
        return hotelRepository.filterByName(name);
    }

    public Hotel getById(long id) {
        return hotelRepository.findById(id);
    }

    public Hotel save(Hotel hotel) { return hotelRepository.save(hotel); }

    public boolean deleteById(long id) { return hotelRepository.deleteById(id); }

    public Hotel addAmenityToHotel(long id_hotel, long id_amenity) {
        return hotelRepository.addAmenityToHotel(id_hotel, id_amenity);
    }

    public Hotel removeAmenityToHotel(long id_hotel, long id_amenity) {
        return hotelRepository.removeAmenityToHotel(id_hotel, id_amenity);
    }
}
