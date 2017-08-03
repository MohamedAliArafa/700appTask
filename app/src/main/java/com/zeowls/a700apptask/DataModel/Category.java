package com.zeowls.a700apptask.DataModel;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmList;
import io.realm.RealmModel;
import io.realm.annotations.PrimaryKey;

/**
 * Created by root on 8/2/17.
 */

public class Category implements RealmModel {
    @PrimaryKey
    @SerializedName("CategoryID")
    private int ID;
    @SerializedName("UserID")
    private String UserID;
    @SerializedName("CategoryNameAr")
    private String NameAr;
    @SerializedName("CategoryName")
    private String Name;
    @SerializedName("CategoryNameEn")
    private String NameEn;
    @SerializedName("CategoryImage")
    private String Image;
    @SerializedName("ImagePath")
    private String ImagePath;
    @SerializedName("language")
    private int language;
    @SerializedName("Products")
    private RealmList<Product> Products;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getUserID() {
        return UserID;
    }

    public void setUserID(String userID) {
        UserID = userID;
    }

    public String getNameAr() {
        return NameAr;
    }

    public void setNameAr(String nameAr) {
        NameAr = nameAr;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getNameEn() {
        return NameEn;
    }

    public void setNameEn(String nameEn) {
        NameEn = nameEn;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getImagePath() {
        return ImagePath;
    }

    public void setImagePath(String imagePath) {
        ImagePath = imagePath;
    }

    public int getLanguage() {
        return language;
    }

    public void setLanguage(int language) {
        this.language = language;
    }

    public RealmList<Product> getProducts() {
        return Products;
    }

    public void setProducts(RealmList<Product> products) {
        Products = products;
    }
}
