package com.example.cosminalex.android_skills.commons.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BadgeCounts implements Parcelable {

    @SerializedName("bronze")
    @Expose
    private long bronze;
    @SerializedName("silver")
    @Expose
    private long silver;
    @SerializedName("gold")
    @Expose
    private long gold;

    protected BadgeCounts(Parcel in) {
        bronze = in.readLong();
        silver = in.readLong();
        gold = in.readLong();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(bronze);
        dest.writeLong(silver);
        dest.writeLong(gold);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<BadgeCounts> CREATOR = new Creator<BadgeCounts>() {
        @Override
        public BadgeCounts createFromParcel(Parcel in) {
            return new BadgeCounts(in);
        }

        @Override
        public BadgeCounts[] newArray(int size) {
            return new BadgeCounts[size];
        }
    };

    public long getBronze() {
        return bronze;
    }

    public void setBronze(long bronze) {
        this.bronze = bronze;
    }

    public BadgeCounts withBronze(long bronze) {
        this.bronze = bronze;
        return this;
    }

    public long getSilver() {
        return silver;
    }

    public void setSilver(long silver) {
        this.silver = silver;
    }

    public BadgeCounts withSilver(long silver) {
        this.silver = silver;
        return this;
    }

    public long getGold() {
        return gold;
    }

    public void setGold(long gold) {
        this.gold = gold;
    }

    public BadgeCounts withGold(long gold) {
        this.gold = gold;
        return this;
    }
}