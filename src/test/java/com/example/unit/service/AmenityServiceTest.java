package com.example.unit.service;

import com.example.model.Amenity;
import com.example.service.AmenityServiceImpl;
import com.example.wsclient.AmenityClient;
import com.hotels.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

import static com.example.helper.AmenityHelper.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
@ContextConfiguration(locations = "/hotel-context.xml")
@WebAppConfiguration
public class AmenityServiceTest {
    @Mock
    private AmenityClient amenityClient;

    @InjectMocks
    private AmenityServiceImpl underTest;

    private static Amenity testAmenity = new Amenity();

    @BeforeEach
    void setup() {
        testAmenity.setId(112L);
        testAmenity.setName("WiFi");
        testAmenity.setDescription("Free");
    }

    @Test
    void createAmenity() {
        AddAmenityDetailsResponse testResponse = new AddAmenityDetailsResponse();
        testResponse.setAmenityDetails(toAmenityDetails(testAmenity));
        doReturn(testResponse).when(amenityClient).addAmenity(any());
        underTest.createAmenity(testAmenity);
        verify(amenityClient).addAmenity(any());
    }

    @Test
    void editAmenity() {
        EditAmenityDetailsResponse testResponse = new EditAmenityDetailsResponse();
        testResponse.setAmenityDetails(toAmenityDetails(testAmenity));
        doReturn(testResponse).when(amenityClient).editAmenity(testAmenity);
        underTest.editAmenity(testAmenity);
        verify(amenityClient).editAmenity(any());
    }

    @Test
    void listAmenities(){
        GetAllAmenityDetailsResponse testResponse = new GetAllAmenityDetailsResponse();
        testResponse.getAmenityDetails().add(toAmenityDetails(testAmenity));
        doReturn(testResponse).when(amenityClient).getAll();
        underTest.getAll();
        verify(amenityClient).getAll();
    }
}
