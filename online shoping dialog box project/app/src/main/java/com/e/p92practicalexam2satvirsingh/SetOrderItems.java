package com.e.p92practicalexam2satvirsingh;

public class SetOrderItems {
    String ProdName;
    int Price,Total,Quantity;

    public SetOrderItems(String prodName, int price, int total, int quantity) {
        ProdName = prodName;
        Price = price;
        Total = total;
        Quantity = quantity;
    }

    public String getProdName() {
        return ProdName;
    }

    public void setProdName(String prodName) {
        ProdName = prodName;
    }

    public int getPrice() {
        return Price;
    }

    public void setPrice(int price) {
        Price = price;
    }

    public int getTotal() {
        return Total;
    }

    public void setTotal(int total) {
        Total = total;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }
}
