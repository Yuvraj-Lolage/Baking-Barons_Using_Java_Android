package com.example.bakingbarons;

public class CakeModel {
    private String cakeName;
    private String cakeImageUrl;
    private  String cakePrice;
    private String cakeFlavour;
    private String category;

    public CakeModel(){}

    public CakeModel(String cakeName, String cakeImageUrl, String cakePrice, String cakeFlavour, String category) {
        this.cakeName = cakeName;
        this.cakeImageUrl = cakeImageUrl;
        this.cakePrice = cakePrice;
        this.cakeFlavour = cakeFlavour;
        this.category = category;
    }

    public String getCakeName() {
        return cakeName;
    }

    public void setCakeName(String cakeName) {
        this.cakeName = cakeName;
    }

    public String getCakeImageUrl() {
        return cakeImageUrl;
    }

    public void setCakeImageUrl(String cakeImageUrl) {
        this.cakeImageUrl = cakeImageUrl;
    }

    public String getCakePrice() {
        return cakePrice;
    }

    public void setCakePrice(String cakePrice) {
        this.cakePrice = cakePrice;
    }

    public String getCakeFlavour() {
        return cakeFlavour;
    }

    public void setCakeFlavour(String cakeFlavour) {
        this.cakeFlavour = cakeFlavour;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
