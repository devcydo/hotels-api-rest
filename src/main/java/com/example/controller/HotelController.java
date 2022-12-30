package com.example.controller;

import com.example.body.AddAmenity;
import com.example.model.Hotel;
import com.example.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotels")
public class HotelController {

    private HotelService hotelService;

    @Autowired
    public HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @GetMapping
    public List<Hotel> getAll() {
        List<Hotel> hotels = hotelService.getAll();
        return hotels;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Hotel getById(@PathVariable long id) {
        return hotelService.getById(id);
    }

    @GetMapping("/search/{name}")
    @ResponseStatus(HttpStatus.OK)
    public List<Hotel> filterByName(@PathVariable String name) {
        return hotelService.filterByName(name);
    }

    @PostMapping
    public ResponseEntity<Hotel> create(@RequestBody Hotel hotel) {
        Hotel h = hotelService.save(hotel);
        return new ResponseEntity<Hotel>(h, HttpStatus.CREATED);
    }

    @PostMapping("/{id}/amenities")
    public ResponseEntity<Hotel> addAmenity(@PathVariable long id, @RequestBody AddAmenity amenity) {
        Hotel h = hotelService.addAmenityToHotel(id, amenity.getId_amenity());
        return new ResponseEntity<Hotel>(h, HttpStatus.CREATED);
    }

    @DeleteMapping("/{idHotel}/amenities/{idAmenity}")
    public ResponseEntity<Hotel> removeAmenity(@PathVariable long idHotel, @PathVariable long idAmenity) {
        Hotel h = hotelService.removeAmenityToHotel(idHotel, idAmenity);
        return new ResponseEntity<Hotel>(h, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Hotel> edit(@PathVariable long id, @RequestBody Hotel hotel) {
        hotel.setId(id);
        Hotel h = hotelService.save(hotel);
        return new ResponseEntity<Hotel>(h, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable long id) {
        boolean deleted = hotelService.deleteById(id);
        if(deleted) {
            return new ResponseEntity<String>("Deleted", HttpStatus.OK);
        }
        return new ResponseEntity<String>("Error deleting", HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
