package com.example.wsclient;

import com.example.model.Hotel;
import com.hotels.*;

import static com.example.helper.HotelHelper.*;

import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

public class HotelClient extends WebServiceGatewaySupport {

    public GetAllHotelDetailsResponse getAll(Integer pageNumber, String filterByName) {
        GetAllHotelDetailsRequest request = new GetAllHotelDetailsRequest();

        request.setPageNumber(pageNumber);
        request.setFilterByName(filterByName);

        return (GetAllHotelDetailsResponse) getWebServiceTemplate().marshalSendAndReceive(request);
    }

    public GetHotelDetailsResponse getById(long id) {
        GetHotelDetailsRequest request = new GetHotelDetailsRequest();

        request.setId(id);

        return (GetHotelDetailsResponse) getWebServiceTemplate().marshalSendAndReceive(request);
    }

    public AddHotelDetailsResponse addHotel(Hotel hotel) {
        AddHotelDetailsRequest request = new AddHotelDetailsRequest();

        request.setHotelDetails(toHotelDetails(hotel));

        return (AddHotelDetailsResponse) getWebServiceTemplate().marshalSendAndReceive(request);
    }

    public EditHotelDetailsResponse editHotel(Hotel hotel) {
        EditHotelDetailsRequest request = new EditHotelDetailsRequest();

        request.setHotelDetails(toHotelDetails(hotel));

        return (EditHotelDetailsResponse) getWebServiceTemplate().marshalSendAndReceive(request);
    }

    public AddAmenityDetailsToHotelDetailsResponse addAmenityToHotel(long id_hotel, long id_amenity) {
        AddAmenityDetailsToHotelDetailsRequest request = new AddAmenityDetailsToHotelDetailsRequest();

        request.setIdHotel(id_hotel);
        request.setIdAmenity(id_amenity);



        return (AddAmenityDetailsToHotelDetailsResponse) getWebServiceTemplate().marshalSendAndReceive(request);
    }

    public RemoveAmenityDetailsToHotelDetailsResponse removeAmenityToHotel(long id_hotel, long id_amenity) {
        RemoveAmenityDetailsToHotelDetailsRequest request = new RemoveAmenityDetailsToHotelDetailsRequest();

        request.setIdHotel(id_hotel);
        request.setIdAmenity(id_amenity);

        return (RemoveAmenityDetailsToHotelDetailsResponse) getWebServiceTemplate().marshalSendAndReceive(request);
    }

    public DeleteHotelDetailsResponse deleteById(long id) {
        DeleteHotelDetailsRequest request = new DeleteHotelDetailsRequest();

        request.setId(id);

        return (DeleteHotelDetailsResponse) getWebServiceTemplate().marshalSendAndReceive(request);
    }

}
