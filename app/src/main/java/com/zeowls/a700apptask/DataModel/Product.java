package com.zeowls.a700apptask.DataModel;

/*
 * Created by root on 8/2/17.
 */

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmModel;
import io.realm.annotations.PrimaryKey;

public class Product implements RealmModel, Parcelable {


    @PrimaryKey
    @SerializedName("ProductID")
    private int ID;
    @SerializedName("CategoryID")
    private int CategoryID;
    @SerializedName("BrandID")
    private int BrandID;
    @SerializedName("ProductNameAr")
    private String NameAR;
    @SerializedName("ProductName")
    private String Name;
    @SerializedName("ProductNameEn")
    private String NameEN;
    @SerializedName("BrandName")
    private String BandName;
    @SerializedName("ProductPicture")
    private String Picture;
    @SerializedName("ImagePath")
    private String ImagePath;
    @SerializedName("ProductUrl")
    private String Url;
    @SerializedName("BrandImage")
    private String BrandImage;
    @SerializedName("ProductPrice")
    private Double Price;
    @SerializedName("CreateDate")
    private String CreateDate;
    @SerializedName("IsFavourite")
    private int IsFavourite;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getCategoryID() {
        return CategoryID;
    }

    public void setCategoryID(int categoryID) {
        CategoryID = categoryID;
    }

    public int getBrandID() {
        return BrandID;
    }

    public void setBrandID(int brandID) {
        BrandID = brandID;
    }

    public String getNameAR() {
        return NameAR;
    }

    public void setNameAR(String nameAR) {
        NameAR = nameAR;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getNameEN() {
        return NameEN;
    }

    public void setNameEN(String nameEN) {
        NameEN = nameEN;
    }

    public String getBandName() {
        return BandName;
    }

    public void setBandName(String bandName) {
        BandName = bandName;
    }

    public String getPicture() {
        return Picture;
    }

    public void setPicture(String picture) {
        Picture = picture;
    }

    public String getImagePath() {
        return ImagePath;
    }

    public void setImagePath(String imagePath) {
        ImagePath = imagePath;
    }

    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        Url = url;
    }

    public String getBrandImage() {
        return BrandImage;
    }

    public void setBrandImage(String brandImage) {
        BrandImage = brandImage;
    }

    public Double getPrice() {
        return Price;
    }

    public void setPrice(Double price) {
        Price = price;
    }

    public String getCreateDate() {
        return CreateDate;
    }

    public void setCreateDate(String createDate) {
        CreateDate = createDate;
    }

    public int getFavourite() {
        return IsFavourite;
    }

    public void setFavourite(int favourite) {
        IsFavourite = favourite;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.ID);
        dest.writeInt(this.CategoryID);
        dest.writeInt(this.BrandID);
        dest.writeString(this.NameAR);
        dest.writeString(this.Name);
        dest.writeString(this.NameEN);
        dest.writeString(this.BandName);
        dest.writeString(this.Picture);
        dest.writeString(this.ImagePath);
        dest.writeString(this.Url);
        dest.writeString(this.BrandImage);
        dest.writeValue(this.Price);
        dest.writeString(this.CreateDate);
        dest.writeInt(this.IsFavourite);
    }

    public Product() {
    }

    protected Product(Parcel in) {
        this.ID = in.readInt();
        this.CategoryID = in.readInt();
        this.BrandID = in.readInt();
        this.NameAR = in.readString();
        this.Name = in.readString();
        this.NameEN = in.readString();
        this.BandName = in.readString();
        this.Picture = in.readString();
        this.ImagePath = in.readString();
        this.Url = in.readString();
        this.BrandImage = in.readString();
        this.Price = (Double) in.readValue(Double.class.getClassLoader());
        this.CreateDate = in.readString();
        this.IsFavourite = in.readInt();
    }

    public static final Parcelable.Creator<Product> CREATOR = new Parcelable.Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel source) {
            return new Product(source);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };
}
