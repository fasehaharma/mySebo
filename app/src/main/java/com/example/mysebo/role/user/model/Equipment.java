package com.example.mysebo.role.user.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Equipment implements Parcelable {
    private String id;
    private String item;
    private int quantity;
    private boolean isNew = true;

    public Equipment() {
    }

    protected Equipment(Parcel in) {
        id = in.readString();
        item = in.readString();
        quantity = in.readInt();
        isNew = in.readByte() != 0;
    }

    public static final Creator<Equipment> CREATOR = new Creator<Equipment>() {
        @Override
        public Equipment createFromParcel(Parcel in) {
            return new Equipment(in);
        }

        @Override
        public Equipment[] newArray(int size) {
            return new Equipment[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public boolean isNew() {
        return isNew;
    }

    public void setNew(boolean aNew) {
        isNew = aNew;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(item);
        dest.writeInt(quantity);
        dest.writeByte((byte) (isNew ? 1 : 0));
    }
}
