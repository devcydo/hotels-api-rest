package com.example.service;

import com.example.model.Hotel;

import java.util.List;

public interface HotelService {

    List<Hotel> getAll(Integer pageNumber, String filterByName);
    Hotel getById(long id);
    Hotel createHotel(Hotel hotel);
    Hotel editHotel(Hotel hotel);
    boolean deleteById(long id);
    Hotel addAmenityToHotel(long id_hotel, long id_amenity);
    Hotel removeAmenityToHotel(long id_hotel, long id_amenity);
}
