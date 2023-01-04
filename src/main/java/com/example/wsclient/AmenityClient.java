package com.example.wsclient;

import com.example.model.Amenity;
import static com.example.helper.AmenityHelper.*;

import com.hotels.*;

import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

public class AmenityClient extends WebServiceGatewaySupport {
    public GetAllAmenityDetailsResponse getAll() {
        GetAllAmenityDetailsRequest request = new GetAllAmenityDetailsRequest();
        return (GetAllAmenityDetailsResponse) getWebServiceTemplate().marshalSendAndReceive(request);
    }

    public GetAllAmenityDetailsByHotelResponse getByHotelId(long id_hotel) {
        GetAllAmenityDetailsByHotelRequest request = new GetAllAmenityDetailsByHotelRequest();

        request.setIdHotel(id_hotel);

        return (GetAllAmenityDetailsByHotelResponse) getWebServiceTemplate().marshalSendAndReceive(request);
    }

    public AddAmenityDetailsResponse addAmenity(Amenity amenity) {
        AddAmenityDetailsRequest request = new AddAmenityDetailsRequest();

        request.setAmenityDetails(toAmenityDetails(amenity));

        return (AddAmenityDetailsResponse) getWebServiceTemplate().marshalSendAndReceive(request);
    }

    public EditAmenityDetailsResponse editAmenity(Amenity amenity) {
        EditAmenityDetailsRequest request = new EditAmenityDetailsRequest();

        request.setAmenityDetails(toAmenityDetails(amenity));

        return (EditAmenityDetailsResponse) getWebServiceTemplate().marshalSendAndReceive(request);
    }

    public DeleteAmenityDetailsResponse deleteById(long id) {
        DeleteAmenityDetailsRequest request = new DeleteAmenityDetailsRequest();

        request.setId(id);

        return (DeleteAmenityDetailsResponse) getWebServiceTemplate().marshalSendAndReceive(request);
    }


}
