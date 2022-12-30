package com.example.controller;

import com.example.model.Amenity;
import com.example.service.AmenityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/amenities")
public class AmenityController {
    private AmenityService amenityService;

    @Autowired
    public AmenityController(AmenityService amenityService) { this.amenityService = amenityService; };

    @GetMapping
    public List<Amenity> getAll() {
        List<Amenity> amenities = amenityService.getAll();
        return amenities;
    }

    @GetMapping("/hotels/{id}")
    public List<Amenity> getByHotelId(@PathVariable long id) {
        List<Amenity> amenities = amenityService.getByHotelId(id);
        return amenities;
    }

    @PostMapping
    public ResponseEntity<Amenity> create(@RequestBody Amenity amenity) {
        Amenity a = amenityService.save(amenity);
        return new ResponseEntity<Amenity>(a, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Amenity> edit(@PathVariable long id, @RequestBody Amenity amenity) {
        amenity.setId(id);
        Amenity a = amenityService.save(amenity);
        return new ResponseEntity<Amenity>(a, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable long id) {
        boolean deleted = amenityService.deleteById(id);
        if(deleted) {
            return new ResponseEntity<String>("Deleted", HttpStatus.OK);
        }
        return new ResponseEntity<String>("Error while deleting", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
