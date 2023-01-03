package com.example.wsclient;

import com.example.exception.BadRequestException;
import com.example.exception.ResourceNotFoundException;
import org.springframework.ws.WebServiceMessage;
import org.springframework.ws.client.WebServiceClientException;
import org.springframework.ws.client.support.interceptor.ClientInterceptor;
import org.springframework.ws.context.MessageContext;
import org.springframework.ws.soap.SoapBody;
import org.springframework.ws.soap.SoapEnvelope;
import org.springframework.ws.soap.SoapMessage;
import org.springframework.ws.soap.saaj.SaajSoapMessage;
import javax.xml.soap.*;
import java.util.Iterator;

public class SoapClientInterceptor implements ClientInterceptor {

    @Override
    public boolean handleRequest(MessageContext messageContext) throws WebServiceClientException {
        return true;
    }

    @Override
    public boolean handleResponse(MessageContext messageContext) throws WebServiceClientException {
        return true;
    }

    @Override
    public boolean handleFault(MessageContext messageContext) throws WebServiceClientException {
        WebServiceMessage message = messageContext.getResponse();
        SaajSoapMessage saajSoapMessage = (SaajSoapMessage) message;
        SOAPMessage soapMessage = saajSoapMessage.getSaajMessage();
        SOAPPart soapPart = soapMessage.getSOAPPart();
        try {
            SOAPEnvelope soapEnvelope = soapPart.getEnvelope();
            SOAPBody soapBody = soapEnvelope.getBody();
            SOAPFault soapFault = soapBody.getFault();
            Detail soapErrorDetail = soapFault.getDetail();
            Iterator detailEntries = soapErrorDetail.getDetailEntries();
            DetailEntry statusDetail = (DetailEntry) detailEntries.next();
            DetailEntry messageDetail = (DetailEntry) detailEntries.next();

            String serviceStatus = statusDetail.getValue();
            String errorMessage = messageDetail.getValue();

            if(serviceStatus.equals("NOT_FOUND")) {
                throw new ResourceNotFoundException(errorMessage);
            } else if (serviceStatus.equals("BAD_REQUEST")) {
                throw new BadRequestException(errorMessage);
            }
        } catch (SOAPException ex) {
            ex.printStackTrace();
        }
        return true;
    }

    @Override
    public void afterCompletion(MessageContext messageContext, Exception ex) throws WebServiceClientException {

    }

    private SoapBody getSoapBody(MessageContext messageContext) {
        SoapMessage soapMessage = (SoapMessage) messageContext.getResponse();
        SoapEnvelope soapEnvelope = soapMessage.getEnvelope();
        return soapEnvelope.getBody();
    }
}
