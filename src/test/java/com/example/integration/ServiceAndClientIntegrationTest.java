package com.example.integration;

import com.example.exception.BadRequestException;
import com.example.exception.ResourceNotFoundException;
import com.example.model.Amenity;
import com.example.model.Hotel;
import com.example.service.AmenityService;
import com.example.service.HotelService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
@ContextConfiguration(locations = "/context.xml")
@WebAppConfiguration
public class ServiceAndClientIntegrationTest {

    private static Hotel testHotel = new Hotel();
    private static Long createdHotelId = 0L;

    private static Amenity testAmenity = new Amenity();
    private static Long createdAmenityId = 0L;

    @Autowired
    HotelService hotelService;

    @Autowired
    AmenityService amenityService;

    @BeforeEach
    void setup() {
        testHotel.setId(10100L);
        testHotel.setName("Hotel from REST TEST");
        testHotel.setAddress("Av testing, Col. Test. Testable, Test");
        testHotel.setRating(2);
        createdHotelId = hotelService.createHotel(testHotel).getId();

        testAmenity.setId(10101L);
        testAmenity.setName("Amenity from REST TEST");
        testAmenity.setDescription("Cool test");
        createdAmenityId = amenityService.createAmenity(testAmenity).getId();
    }

    @AfterEach
    void unmount(TestInfo info) {
        if(info.getDisplayName().equals("deleteHotel()")) return;

        amenityService.deleteById(createdAmenityId);
        hotelService.deleteById(createdHotelId);
    }

    @Test
    public void createHotel(){
        assertNotNull((hotelService.createHotel(testHotel)));
    }

    @Test
    public void createHotel_badRequest() throws BadRequestException {
        assertThrows(BadRequestException.class,()->{
            testHotel.setName("");
            hotelService.createHotel(testHotel);
        });
    }

    @Test
    public void getHotelById(){
        assertNotNull(hotelService.getById(createdHotelId));
    }

    @Test
    public void editHotel(){
        testHotel.setId(createdHotelId);
        testHotel.setName("Hotel from REST TEST edited");
        hotelService.editHotel(testHotel);
        assertEquals("Hotel from REST TEST edited", hotelService.getById(createdHotelId).getName());
    }

    @Test
    public void editHotel_badRequest() throws BadRequestException{
        assertThrows(BadRequestException.class,()->{
            testHotel.setId(createdHotelId);
            testHotel.setAddress("");
            hotelService.editHotel(testHotel);
        });
    }

    @Test
    public void editHotelNotFound() throws ResourceNotFoundException {
        assertThrows(ResourceNotFoundException.class,()->{
            testHotel.setId(1000L);
            hotelService.editHotel(testHotel);
        });
    }

    @Test
    public void deleteHotel(){
        hotelService.deleteById(createdHotelId);
    }

    @Test
    public void deleteHotelNotFound() throws ResourceNotFoundException{
        assertThrows(ResourceNotFoundException.class,()->{
            hotelService.deleteById(1000L);
        });
    }

    @Test
    public void createAmenity(){
        assertNotNull((amenityService.createAmenity(testAmenity)));
    }

    @Test
    public void createAmenity_badRequest() throws BadRequestException {
        assertThrows(BadRequestException.class,()->{
            testAmenity.setName("");
            amenityService.createAmenity(testAmenity);
        });
    }

}
