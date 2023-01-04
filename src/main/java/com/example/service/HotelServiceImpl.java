package com.example.service;

import com.example.model.Hotel;
import com.example.wsclient.HotelClient;
import org.springframework.stereotype.Service;

import static com.example.helper.HotelHelper.*;

import java.util.List;

@Service
public class HotelServiceImpl implements HotelService {

    private final HotelClient hotelClient;

    public HotelServiceImpl(HotelClient hotelClient) {
        this.hotelClient = hotelClient;
    }

    @Override
    public List<Hotel> getAll(Integer pageNumber, String filterByName) {
        return toHotels(hotelClient.getAll(pageNumber, filterByName).getHotelDetails());
    }

    @Override
    public Hotel getById(long id) {
        return toHotel(hotelClient.getById(id).getHotelDetails());
    }

    @Override
    public Hotel createHotel(Hotel hotel) {
        return toHotel(hotelClient.addHotel(hotel).getHotelDetails());
    }

    @Override
    public Hotel editHotel(Hotel hotel) {
        return toHotel(hotelClient.editHotel(hotel).getHotelDetails());
    }

    @Override
    public boolean deleteById(long id) {
        return hotelClient.deleteById(id).getServiceStatus().getStatusCode().equals("SUCCESS");
    }

    @Override
    public Hotel addAmenityToHotel(long id_hotel, long id_amenity) {
        return toHotel(hotelClient.addAmenityToHotel(id_hotel, id_amenity).getHotelDetails());
    }

    @Override
    public Hotel removeAmenityToHotel(long id_hotel, long id_amenity) {
        return toHotel(hotelClient.removeAmenityToHotel(id_hotel, id_amenity).getHotelDetails());
    }
}
