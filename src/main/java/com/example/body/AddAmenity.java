package com.example.body;

public class AddAmenity {
    private long id_amenity;

    public AddAmenity() {
    }

    public AddAmenity(long id_hotel) {
        this.id_amenity = id_hotel;
    }

    public long getId_amenity() {
        return id_amenity;
    }

    public void setId_amenity(long id_amenity) {
        this.id_amenity = id_amenity;
    }
}
