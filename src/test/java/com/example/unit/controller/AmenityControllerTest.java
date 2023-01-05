package com.example.unit.controller;

import com.example.controller.AmenityController;
import com.example.model.Amenity;
import com.example.service.AmenityServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
@ContextConfiguration(locations = "/amenity-context.xml")
@WebAppConfiguration
public class AmenityControllerTest {

    @Mock
    private AmenityServiceImpl amenityService;

    @InjectMocks
    private AmenityController underTest;

    private static Amenity testAmenity = new Amenity();

    @BeforeEach
    void setup() {
        testAmenity.setId(112L);
        testAmenity.setName("WiFi");
        testAmenity.setDescription("Free");
    }

    @Test
    void getAmenities() {
        underTest.getAll();
        verify(amenityService).getAll();
    }

    @Test
    void createAmenity() {
        underTest.create(testAmenity);
        verify(amenityService).createAmenity(testAmenity);
    }

    @Test
    void updateAmenity() {
        underTest.edit(112L, testAmenity);
        verify(amenityService).editAmenity(testAmenity);
    }

    @Test
    void deleteAmenityById() {
        underTest.delete(112L);
        verify(amenityService).deleteById(112L);
    }
}
