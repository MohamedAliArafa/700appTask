package com.zeowls.a700apptask.DataModel;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmList;
import io.realm.RealmModel;

/**
 * Created by root on 8/2/17.
 */

public class Result implements RealmModel {
    @SerializedName("result")
    private RealmList<Category> items;

    public RealmList<Category> getItems() {
        return items;
    }

    public void setItems(RealmList<Category> items) {
        this.items = items;
    }
}
