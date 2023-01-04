package com.example.helper;

import com.example.model.Amenity;
import com.example.model.Hotel;
import com.hotels.AmenityDetails;
import com.hotels.HotelDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class AmenityHelper {
    public static Amenity toAmenity(AmenityDetails amenityDetails) {
        Amenity amenity = new Amenity();

        amenity.setId(amenityDetails.getId());
        amenity.setName(amenityDetails.getName());
        amenity.setDescription(amenityDetails.getDescription());

        return amenity;
    }

    public static List<Amenity> toAmenities(Collection<AmenityDetails> allAmenitiesDetails) {
        List<Amenity> amenities = new ArrayList<>();
        allAmenitiesDetails.forEach(amenityDetails -> amenities.add(toAmenity(amenityDetails)));
        return amenities;
    }

    public static AmenityDetails toAmenityDetails(Amenity amenity) {
        AmenityDetails amenityDetails = new AmenityDetails();

        amenityDetails.setId(amenity.getId());
        amenityDetails.setName(amenity.getName());
        amenityDetails.setDescription(amenity.getDescription());

        return amenityDetails;
    }
}
