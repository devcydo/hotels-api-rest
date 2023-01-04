package com.example.unit.repository;

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

import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
@ContextConfiguration(locations = "/test-context.xml")
@WebAppConfiguration
public class HotelControllerTest {

    @Mock
    private HotelServiceImpl hotelService;

    @InjectMocks
    private HotelController hotelController;

    private static Hotel testHotel = new Hotel();

    @BeforeEach
    void setup() {
        testHotel.setId(10002L);
        testHotel.setName("Sleep Inn Guadalajara Galerias Test");
        testHotel.setAddress("AVENIDA VALLARTA, ESQUINA JOSE CLEMENTE OROZCO, ZAPOPAN, JAL, 45018, MX");
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

    }
}
