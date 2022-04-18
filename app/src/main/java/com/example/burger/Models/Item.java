package com.example.burger.Models;

import java.io.Serializable;

public class Item implements Serializable {
    private int image;
    private String name;
    private String price;
    private String mealPrice;
    private int count;

    public Item(int image, String name, String price, String mealPrice, int count) {
        this.image = image;
        this.name = name;
        this.price = price;
        this.mealPrice = mealPrice;
        this.count = count;
    }

    public Item(int image, String name, String price, int count) {
        this.image = image;
        this.name = name;
        this.price = price;
        this.count = count;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getMealPrice() {
        return mealPrice;
    }

    public void setMealPrice(String mealPrice) {
        this.mealPrice = mealPrice;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
