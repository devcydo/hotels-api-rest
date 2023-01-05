package com.example.unit.controller;

import com.example.controller.HotelController;
import com.example.model.Amenity;
import com.example.model.Hotel;
import com.example.service.HotelServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
@ContextConfiguration(locations = "/hotel-context.xml")
@WebAppConfiguration
public class HotelControllerTest {

    @Mock
    private HotelServiceImpl hotelService;

    @InjectMocks
    private HotelController underTest;

    private static Hotel testHotel = new Hotel();

    @BeforeEach
    void setup() {
        testHotel.setId(10100L);
        testHotel.setName("Hotel from REST TEST");
        testHotel.setAddress("Av testing, Col. Test. Testable, Test");
        testHotel.setRating(2);

        Amenity firstAmenity = new Amenity();
        firstAmenity.setId(1L);
        firstAmenity.setName("WiFi");
        firstAmenity.setDescription("Free");
        Amenity secondAmenity = new Amenity();
        secondAmenity.setId(2L);
        secondAmenity.setName("Gym");
        secondAmenity.setDescription("Free");

        List<Amenity> amenities = new ArrayList<Amenity>();
        amenities.add(firstAmenity);
        amenities.add(secondAmenity);
        testHotel.setAmenities(amenities);
    }

    @Test
    void getHotels() {
        underTest.getAll(0, "");
        verify(hotelService).getAll(0, "");
    }

    @Test
    void getHotelById() {
        underTest.getById(10100L);
        verify(hotelService).getById(10100L);
    }

    @Test
    void createHotel() {
        underTest.create(testHotel);
        verify(hotelService).createHotel(testHotel);
    }

    @Test
    void updateHotel() {
        underTest.edit(10100L, testHotel);
        verify(hotelService).editHotel(testHotel);
    }

    @Test
    void deleteHotelById() {
        underTest.delete(10100L);
        verify(hotelService).deleteById(10100L);
    }
}
