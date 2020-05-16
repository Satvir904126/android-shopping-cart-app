package com.e.onlineshopingcart;

public class SetCartItems {

String itemName;
int totalPrice;

    public SetCartItems(String itemName, int totalPrice) {
        this.itemName = itemName;
        this.totalPrice = totalPrice;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }
}
