package com.example.neetshah.cryptocharts;

import java.io.Serializable;

public class CurrencyData implements Serializable{
    private String name, fullName;
    private double price;
    private boolean isFavourite;

    public void toggleIsFavourite() {
        this.isFavourite = !this.isFavourite;
    }

    public boolean isFavourite() {
        return isFavourite;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getName() {
        return this.name;
    }

    public String getFullName() {
        return this.fullName;
    }

    public double getPrice() {
        return price;
    }
}
