package com.example.service;

import com.example.model.Amenity;
import com.example.wsclient.AmenityClient;
import org.springframework.stereotype.Service;

import static com.example.helper.AmenityHelper.*;


import java.util.List;

@Service
public class AmenityServiceImpl implements AmenityService {
    private final AmenityClient amenityClient;

    public AmenityServiceImpl(AmenityClient amenityClient) {
        this.amenityClient = amenityClient;
    }

    @Override
    public List<Amenity> getAll() { return toAmenities(amenityClient.getAll().getAmenityDetails()); }

    @Override
    public List<Amenity> getByHotelId(long id_hotel) { return toAmenities(amenityClient.getByHotelId(id_hotel).getAmenityDetails()); }

    @Override
    public Amenity createAmenity(Amenity amenity) { return toAmenity(amenityClient.addAmenity(amenity).getAmenityDetails()); }

    @Override
    public Amenity editAmenity(Amenity amenity) { return toAmenity(amenityClient.editAmenity(amenity).getAmenityDetails()); }

    @Override
    public boolean deleteById(long id) { return amenityClient.deleteById(id).getServiceStatus().getStatusCode().equals("SUCCESS"); }
}
