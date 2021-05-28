package com.example.veritabaniodevi;

public class Categorys {

    private String categoryName;
    private String productPrice;

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    private String imageName;

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public Categorys(String categoryName, String imageName,String productPrice) {
        this.categoryName = categoryName;
        this.imageName = imageName;
        this.productPrice = productPrice;
    }

    public Categorys() {
    }
}
