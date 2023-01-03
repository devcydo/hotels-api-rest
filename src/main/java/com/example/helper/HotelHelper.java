package com.example.helper;

import com.example.model.Amenity;
import com.example.model.Hotel;
import com.hotels.AmenityDetails;
import com.hotels.HotelDetails;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
public class HotelHelper {
    public static Hotel toHotel(HotelDetails hotelDetails) {
        Hotel hotel = new Hotel();

        List<Amenity> amenities = new ArrayList<>();

        hotel.setId(hotelDetails.getId());
        hotel.setName(hotelDetails.getName());
        hotel.setAddress(hotelDetails.getAddress());
        hotel.setRating(hotelDetails.getRating());

        Optional.ofNullable(hotelDetails.getAmenityDetails())
                .orElseGet(Collections::emptyList)
                .stream().forEach(amenityDetails -> amenities.add(toAmenity(amenityDetails)));

        hotel.setAmenities(amenities);
        return hotel;
    }

    public static Amenity toAmenity(AmenityDetails amenityDetails) {
        Amenity amenity = new Amenity();

        amenity.setId(amenityDetails.getId());
        amenity.setName(amenityDetails.getName());
        amenity.setDescription(amenityDetails.getDescription());

        return amenity;
    }

    public static HotelDetails toHotelDetails(Hotel hotel) {
        HotelDetails hotelDetails = new HotelDetails();

        hotelDetails.setId(hotel.getId());
        hotelDetails.setName(hotel.getName());
        hotelDetails.setAddress(hotel.getAddress());
        hotelDetails.setRating(hotel.getRating());

        return hotelDetails;
    }
}
