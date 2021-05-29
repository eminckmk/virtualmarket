package com.example.veritabaniodevi;

public class BasketModel {
    private String productName;
    private String productPrice;
    private String productPiece;
    private String totalPiece;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductPiece() {
        return productPiece;
    }

    public void setProductPiece(String productPiece) {
        this.productPiece = productPiece;
    }

    public String getTotalPiece() {
        return totalPiece;
    }

    public void setTotalPiece(String totalPiece) {
        this.totalPiece = totalPiece;
    }

    public BasketModel() {
    }

    public BasketModel(String productName, String productPrice, String productPiece, String totalPiece) {
        this.productName = productName;
        this.productPrice = productPrice;
        this.productPiece = productPiece;
        this.totalPiece = totalPiece;
    }
}
