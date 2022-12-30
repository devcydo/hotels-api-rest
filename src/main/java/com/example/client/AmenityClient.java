package com.example.client;

import com.example.model.Amenity;
import com.example.model.Hotel;
import com.hotels.*;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

import java.util.*;

public class AmenityClient extends WebServiceGatewaySupport {
    public List<Amenity> getAll() {
        List<Amenity> amenities = new ArrayList<>();

        GetAllAmenityDetailsRequest request = new GetAllAmenityDetailsRequest();

        GetAllAmenityDetailsResponse response = (GetAllAmenityDetailsResponse) getWebServiceTemplate().marshalSendAndReceive(request);

        Optional.ofNullable(response.getAmenityDetails())
                .orElseGet(Collections::emptyList)
                .stream().forEach(amenityDetails -> amenities.add(toAmenity(amenityDetails)));

        return amenities;
    }

    public List<Amenity> getByHotelId(long id_hotel) {
        List<Amenity> amenities = new ArrayList<>();
        GetAllAmenityDetailsByHotelRequest request = new GetAllAmenityDetailsByHotelRequest();

        request.setIdHotel(id_hotel);

        GetAllAmenityDetailsByHotelResponse response = (GetAllAmenityDetailsByHotelResponse) getWebServiceTemplate().marshalSendAndReceive(request);

        Optional.ofNullable(response.getAmenityDetails())
                .orElseGet(Collections::emptyList)
                .stream().forEach(amenityDetails -> amenities.add(toAmenity(amenityDetails)));

        return amenities;
    }

    public Amenity save(Amenity amenity) {
        SaveAmenityDetailsRequest request = new SaveAmenityDetailsRequest();

        request.setAmenityDetails(toAmenityDetails(amenity));

        SaveAmenityDetailsResponse response = (SaveAmenityDetailsResponse) getWebServiceTemplate().marshalSendAndReceive(request);

        return toAmenity(response.getAmenityDetails());
    }

    public boolean deleteById(long id) {
        DeleteAmenityDetailsRequest request = new DeleteAmenityDetailsRequest();

        request.setId(id);

        DeleteAmenityDetailsResponse response = (DeleteAmenityDetailsResponse) getWebServiceTemplate().marshalSendAndReceive(request);

        return response.getServiceStatus().getStatusCode().equals("SUCCESS");
    }

    private Amenity toAmenity(AmenityDetails amenityDetails) {
        Amenity amenity = new Amenity();

        amenity.setId(amenityDetails.getId());
        amenity.setName(amenityDetails.getName());
        amenity.setDescription(amenityDetails.getDescription());

        return amenity;
    }

    private AmenityDetails toAmenityDetails(Amenity amenity) {
        AmenityDetails amenityDetails = new AmenityDetails();

        amenityDetails.setId(amenity.getId());
        amenityDetails.setName(amenity.getName());
        amenityDetails.setDescription(amenity.getDescription());

        return amenityDetails;
    }
}
