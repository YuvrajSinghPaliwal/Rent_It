package com.example.rentit;


public class Houses {
    private String imgHouse;
    private String name;
    private String city;
    private String price;
    private String type;
    private String address;
    private String number;

    public Houses(String imgHouse, String name, String city, String price, String type, String address,String number) {
        this.imgHouse = imgHouse;
        this.name = name;
        this.city = city;
        this.price = price;
        this.type = type;
        this.address = address;
        this.number = number;
    }
    public Houses(){}

    public String getImgHouse() {
        return imgHouse;
    }

    public void setImgHouse(String imgHouse) {
        this.imgHouse = imgHouse;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
