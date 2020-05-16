package com.e.onlineshopingcart;

import android.graphics.Bitmap;

public class SetImagesClass {
    Bitmap image;
    String img_name;
    int price;

    public SetImagesClass(Bitmap image, String img_name, int price) {
        //String imagehj = new String(image);
        this.image = image;
        this.img_name = img_name;
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
