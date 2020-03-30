package com.parndt.zehntausend.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class Player implements Parcelable, Serializable {

    private static int idGenerator = 1;

    private int id;
    private String name;

    public Player(Parcel in) {
        id = in.readInt();
        name = in.readString();
    }

    public static final Creator<Player> CREATOR = new Creator<Player>() {
        @Override
        public Player createFromParcel(Parcel in) {
            return new Player(in);
        }

        @Override
        public Player[] newArray(int size) {
            return new Player[size];
        }
    };

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Player(String name) {
        this.id = idGenerator;
        idGenerator++;

        this.name = name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
    }
}
