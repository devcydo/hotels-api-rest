package com.example.service;

import com.example.model.Amenity;
import com.example.repository.AmenityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AmenityService {

    @Autowired
    private AmenityRepository amenityRepository;

    public List<Amenity> getAll() { return amenityRepository.findAll(); }

    public List<Amenity> getByHotelId(long id_hotel) { return amenityRepository.findByHotelId(id_hotel); }

    public Amenity save(Amenity amenity) { return amenityRepository.save(amenity); }

    public boolean deleteById(long id) { return amenityRepository.deleteById(id); }

}
