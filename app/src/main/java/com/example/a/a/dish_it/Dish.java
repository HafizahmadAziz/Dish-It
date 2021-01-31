package com.example.a.a.dish_it;

public class Dish {

    public String dishid;
    public String dishName;
    public String dishDescription;
    public String dishprice;
    public String chef;
    public String userEmail;
    public String qty;


    public Dish(){

    }

    public Dish(String dishid, String dishName, String dishDescription, String dishprice, String chef, String userEmail,String qty) {
        this.dishid = dishid;
        this.dishName = dishName;
        this.dishDescription = dishDescription;
        this.dishprice = dishprice;
        this.chef = chef;
        this.userEmail = userEmail;
        this.qty = qty;


    }


    public String getDishId(){
        return dishid;
    }

    public String setDishId(String dishid){
        return this.dishid =dishid;
    }

    public String getDishName(){
        return dishName;
    }

    public String setDishName(String dishName){
        return this.dishName =dishName;
    }

    public String getDishDescription(){
        return dishDescription;
    }

    public String setDishDescription(String dishDescription){
        return this.dishDescription =dishDescription;
    }

    public String getDishPrice(){
        return dishprice;
    }

    public String setDishPrice(String dishprice){
        return this.dishprice =dishprice;
    }

    public String getChef(){
        return chef;
    }

    public String setChef(String chef){
        return this.chef =chef;
    }

    public String getuserEmail(){
        return userEmail;
    }

    public String setuserEmail(String userEmail){
        return this.userEmail =userEmail;
    }

    public String getQty(){
        return qty;
    }

    public String setQty(String qty){
        return this.qty =qty;
    }




}
