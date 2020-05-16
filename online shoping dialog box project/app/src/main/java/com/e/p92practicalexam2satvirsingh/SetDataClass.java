package com.e.p92practicalexam2satvirsingh;

import android.graphics.Bitmap;

public class SetDataClass {

    Bitmap image;
    String img_name;
    String description;
    int price;

    public SetDataClass(Bitmap image, String img_name, String description, int price) {
        this.image = image;
        this.img_name = img_name;
        this.description = description;
        this.price = price;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public String getImg_name() {
        return img_name;
    }

    public void setImg_name(String img_name) {
        this.img_name = img_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
