package com.example.wsclient;

import com.example.model.Amenity;
import com.example.model.Hotel;
import com.hotels.*;

import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class HotelClient extends WebServiceGatewaySupport {

    public List<Hotel> getAll() {
        List<Hotel> hotels = new ArrayList<>();

        GetAllHotelDetailsRequest request = new GetAllHotelDetailsRequest();

        GetAllHotelDetailsResponse response = (GetAllHotelDetailsResponse) getWebServiceTemplate().marshalSendAndReceive(request);

        Optional.ofNullable(response.getHotelDetails())
                .orElseGet(Collections::emptyList)
                .stream().forEach(hotelDetails -> hotels.add(toHotel(hotelDetails)));

        return hotels;
    }

    public List<Hotel> filterByName(String name) {
        List<Hotel> hotels = new ArrayList<>();

        GetAllHotelDetailsByNameRequest request = new GetAllHotelDetailsByNameRequest();

        request.setName(name);

        GetAllHotelDetailsByNameResponse response = (GetAllHotelDetailsByNameResponse) getWebServiceTemplate().marshalSendAndReceive(request);

        Optional.ofNullable(response.getHotelDetails())
                .orElseGet(Collections::emptyList)
                .stream().forEach(hotelDetails -> hotels.add(toHotel(hotelDetails)));

        return hotels;
    }

    public Hotel getById(long id) {
        GetHotelDetailsRequest request = new GetHotelDetailsRequest();

        request.setId(id);

        GetHotelDetailsResponse response = (GetHotelDetailsResponse) getWebServiceTemplate().marshalSendAndReceive(request);

        return toHotel(response.getHotelDetails());
    }

    public Hotel save(Hotel hotel) {
        SaveHotelDetailsRequest request = new SaveHotelDetailsRequest();

        request.setHotelDetails(toHotelDetails(hotel));

        SaveHotelDetailsResponse response = (SaveHotelDetailsResponse) getWebServiceTemplate().marshalSendAndReceive(request);

        return toHotel(response.getHotelDetails());
    }

    public Hotel addAmenityToHotel(long id_hotel, long id_amenity) {
        AddAmenityDetailsToHotelDetailsRequest request = new AddAmenityDetailsToHotelDetailsRequest();

        request.setIdHotel(id_hotel);
        request.setIdAmenity(id_amenity);

        AddAmenityDetailsToHotelDetailsResponse response = (AddAmenityDetailsToHotelDetailsResponse) getWebServiceTemplate().marshalSendAndReceive(request);

        return toHotel(response.getHotelDetails());
    }

    public Hotel removeAmenityToHotel(long id_hotel, long id_amenity) {
        RemoveAmenityDetailsToHotelDetailsRequest request = new RemoveAmenityDetailsToHotelDetailsRequest();

        request.setIdHotel(id_hotel);
        request.setIdAmenity(id_amenity);

        RemoveAmenityDetailsToHotelDetailsResponse response = (RemoveAmenityDetailsToHotelDetailsResponse) getWebServiceTemplate().marshalSendAndReceive(request);

        return toHotel(response.getHotelDetails());
    }

    public boolean deleteById(long id) {
        DeleteHotelDetailsRequest request = new DeleteHotelDetailsRequest();

        request.setId(id);

        DeleteHotelDetailsResponse response = (DeleteHotelDetailsResponse) getWebServiceTemplate().marshalSendAndReceive(request);

        return response.getServiceStatus().getStatusCode().equals("SUCCESS");
    }

    private Hotel toHotel(HotelDetails hotelDetails) {
        Hotel hotel = new Hotel();

        List<Amenity> amenities = new ArrayList<>();

        hotel.setId(hotelDetails.getId());
        hotel.setName(hotelDetails.getName());
        hotel.setAddress(hotelDetails.getAddress());
        hotel.setRating(hotelDetails.getRating());

        Optional.ofNullable(hotelDetails.getAmenityDetails())
                .orElseGet(Collections::emptyList)
                .stream().forEach(amenityDetails -> amenities.add(toAmenity(amenityDetails)));

        hotel.setAmenities(amenities);
        return hotel;
    }

    private Amenity toAmenity(AmenityDetails amenityDetails) {
        Amenity amenity = new Amenity();

        amenity.setId(amenityDetails.getId());
        amenity.setName(amenityDetails.getName());
        amenity.setDescription(amenityDetails.getDescription());

        return amenity;
    }

    private HotelDetails toHotelDetails(Hotel hotel) {
        HotelDetails hotelDetails = new HotelDetails();

        hotelDetails.setId(hotel.getId());
        hotelDetails.setName(hotel.getName());
        hotelDetails.setAddress(hotel.getAddress());
        hotelDetails.setRating(hotel.getRating());

        return hotelDetails;
    }


}
