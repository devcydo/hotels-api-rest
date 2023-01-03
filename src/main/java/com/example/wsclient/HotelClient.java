package com.example.wsclient;

import com.example.model.Hotel;
import com.hotels.*;

import static com.example.helper.HotelHelper.*;

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

}
