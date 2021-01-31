package com.example.a.a.dish_it;

public class Delivery {

    public String id;
    public String userEmail;
    public String TotalPrice;
    public String Address;
    public String ContactNo;
    public String Deliver;





    public Delivery(String id, String userEmail,String TotalPrice, String Address, String ContactNo, String Deliver) {
        this.id = id;
        this.userEmail = userEmail;
        this.TotalPrice =TotalPrice;
        this.Address = Address;
        this.ContactNo = ContactNo;
        this.Deliver = Deliver;

    }
    public Delivery(){

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id=id;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail=userEmail;
    }

    public String getTotalPrice() {
        return TotalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        TotalPrice=totalPrice;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address=address;
    }

    public String getContactNo() {
        return ContactNo;
    }

    public void setContactNo(String contactNo) {
        ContactNo=contactNo;
    }

    public String getDeliver() {
        return Deliver;
    }

    public void setDeliver(String deliver) {
        Deliver=deliver;
    }



}
