package com.example.model;

import java.util.List;

public class Hotel {
    private long id;

    private String name;

    private String address;

    private int rating;

    private List<Amenity> amenities;

    public Hotel() {
    }

    public Hotel(String name, String address, int rating) {
        this.name = name;
        this.address = address;
        this.rating = rating;
    }


    public Hotel(long id, String name, String address, int rating, List<Amenity> amenities) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.rating = rating;
        this.amenities = amenities;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public List<Amenity> getAmenities() {
        return amenities;
    }

    public void setAmenities(List<Amenity> amenities) {
        this.amenities = amenities;
    }

    @Override
    public String toString() {
        return "Hotel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", rating=" + rating +
                ", amenities=" + amenities +
                '}';
    }
}
