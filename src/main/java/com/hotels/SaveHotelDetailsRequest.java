//
// This file was generated by the Eclipse Implementation of JAXB, v2.3.6 
// See https://eclipse-ee4j.github.io/jaxb-ri 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2022.12.30 at 09:32:59 AM CST 
//


package com.hotels;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="HotelDetails" type="{http://hotels.com/soap}HotelDetails"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "hotelDetails"
})
@XmlRootElement(name = "SaveHotelDetailsRequest")
public class SaveHotelDetailsRequest {

    @XmlElement(name = "HotelDetails", required = true)
    protected HotelDetails hotelDetails;

    /**
     * Gets the value of the hotelDetails property.
     * 
     * @return
     *     possible object is
     *     {@link HotelDetails }
     *     
     */
    public HotelDetails getHotelDetails() {
        return hotelDetails;
    }

    /**
     * Sets the value of the hotelDetails property.
     * 
     * @param value
     *     allowed object is
     *     {@link HotelDetails }
     *     
     */
    public void setHotelDetails(HotelDetails value) {
        this.hotelDetails = value;
    }

}
