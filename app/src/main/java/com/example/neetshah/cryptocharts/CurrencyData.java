package com.example.neetshah.cryptocharts;

import java.io.Serializable;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Getter
@Builder
@Setter
public class CurrencyData implements Serializable{
    private String name, fullName;
    private double price;
    private boolean isFavourite;

    public void toggleIsFavourite() {
        this.isFavourite = !this.isFavourite;
    }
}
