package com.example.unit.service;

import com.example.model.Amenity;
import com.example.model.Hotel;
import com.example.service.HotelServiceImpl;
import com.example.wsclient.HotelClient;
import com.hotels.AddHotelDetailsResponse;
import com.hotels.EditHotelDetailsResponse;
import com.hotels.GetAllHotelDetailsResponse;
import com.hotels.GetHotelDetailsResponse;
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

import static com.example.helper.HotelHelper.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
@ContextConfiguration(locations = "/hotel-context.xml")
@WebAppConfiguration
public class HotelServiceTest {
    @Mock
    private HotelClient hotelClient;

    @InjectMocks
    private HotelServiceImpl underTest;

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
    void getHotelById() {
        GetHotelDetailsResponse testResponse = new GetHotelDetailsResponse();
        testResponse.setHotelDetails(toHotelDetails(testHotel));
        doReturn(testResponse).when(hotelClient).getById(10100L);
        underTest.getById(10100L);
        verify(hotelClient).getById(10100L);
    }

    @Test
    void createHotel() {
        AddHotelDetailsResponse testResponse = new AddHotelDetailsResponse();
        testResponse.setHotelDetails(toHotelDetails(testHotel));
        doReturn(testResponse).when(hotelClient).addHotel(any());
        underTest.createHotel(testHotel);
        verify(hotelClient).addHotel(any());
    }

    @Test
    void editHotel() {
        EditHotelDetailsResponse testResponse = new EditHotelDetailsResponse();
        testResponse.setHotelDetails(toHotelDetails(testHotel));
        doReturn(testResponse).when(hotelClient).editHotel(testHotel);
        underTest.editHotel(testHotel);
        verify(hotelClient).editHotel(any());
    }

    @Test
    void listHotelsWithPagination(){
        GetAllHotelDetailsResponse testResponse = new GetAllHotelDetailsResponse();
        testResponse.getHotelDetails().add(toHotelDetails(testHotel));
        doReturn(testResponse).when(hotelClient).getAll(0,"");
        underTest.getAll(0,"");
        verify(hotelClient).getAll(0,"");
    }

    @Test
    void filterHotelsByName(){
        GetAllHotelDetailsResponse testResponse = new GetAllHotelDetailsResponse();
        testResponse.getHotelDetails().add(toHotelDetails(testHotel));
        doReturn(testResponse).when(hotelClient).getAll(0,"test");
        underTest.getAll(0,"test");
        verify(hotelClient).getAll(0,"test");
    }
}
