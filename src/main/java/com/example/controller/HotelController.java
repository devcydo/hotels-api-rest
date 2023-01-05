package com.example.controller;

import com.example.body.AddAmenity;
import com.example.exception.BadRequestException;
import com.example.model.Hotel;
import com.example.service.HotelServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/hotels")
public class HotelController {

    private final HotelServiceImpl hotelService;

    public HotelController(HotelServiceImpl hotelService) {
        this.hotelService = hotelService;
    }

    @GetMapping
    public List<Hotel> getAll(Integer page, String filterByName) {
        return hotelService.getAll(Optional.ofNullable(page).orElse(0), Optional.ofNullable(filterByName).orElse(""));
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Hotel getById(@PathVariable long id) {
        return hotelService.getById(id);
    }

    @PostMapping
    public ResponseEntity<Hotel> create(@Valid @RequestBody Hotel hotel, BindingResult result) {
        Hotel h = hotelService.createHotel(hotel);
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
        Hotel h = hotelService.editHotel(hotel);
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
