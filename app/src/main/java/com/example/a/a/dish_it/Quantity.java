package com.example.a.a.dish_it;

import android.net.UrlQuerySanitizer;

public class Quantity {
    public String qty;

    public Quantity(String qty){
        this.qty =qty;
    }

    public String getQty(){
        return qty;
    }

    public String setQty(String qty){
        return this.qty =qty;
    }
}
