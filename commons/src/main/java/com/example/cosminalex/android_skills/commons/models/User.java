package com.example.cosminalex.android_skills.commons.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by cosminalex on 13.11.2017.
 */

public class User implements  Parcelable{
    @SerializedName("badge_counts")
    @Expose
    private BadgeCounts badgeCounts;
    @SerializedName("account_id")
    @Expose
    private long accountId;
    @SerializedName("is_employee")
    @Expose
    private boolean isEmployee;
    @SerializedName("last_modified_date")
    @Expose
    private long lastModifiedDate;
    @SerializedName("last_access_date")
    @Expose
    private long lastAccessDate;
    @SerializedName("age")
    @Expose
    private long age;
    @SerializedName("reputation_change_year")
    @Expose
    private long reputationChangeYear;
    @SerializedName("reputation_change_quarter")
    @Expose
    private long reputationChangeQuarter;
    @SerializedName("reputation_change_month")
    @Expose
    private long reputationChangeMonth;
    @SerializedName("reputation_change_week")
    @Expose
    private long reputationChangeWeek;
    @SerializedName("reputation_change_day")
    @Expose
    private long reputationChangeDay;
    @SerializedName("reputation")
    @Expose
    private long reputation;
    @SerializedName("creation_date")
    @Expose
    private long creationDate;
    @SerializedName("user_type")
    @Expose
    private String userType;
    @SerializedName("user_id")
    @Expose
    private long userId;
    @SerializedName("accept_rate")
    @Expose
    private long acceptRate;
    @SerializedName("location")
    @Expose
    private String location;
    @SerializedName("website_url")
    @Expose
    private String websiteUrl;
    @SerializedName("link")
    @Expose
    private String link;
    @SerializedName("profile_image")
    @Expose
    private String profileImage;
    @SerializedName("display_name")
    @Expose
    private String displayName;

    protected User(Parcel in) {
        badgeCounts = in.readParcelable(BadgeCounts.class.getClassLoader());
        accountId = in.readLong();
        isEmployee = in.readByte() != 0;
        lastModifiedDate = in.readLong();
        lastAccessDate = in.readLong();
        age = in.readLong();
        reputationChangeYear = in.readLong();
        reputationChangeQuarter = in.readLong();
        reputationChangeMonth = in.readLong();
        reputationChangeWeek = in.readLong();
        reputationChangeDay = in.readLong();
        reputation = in.readLong();
        creationDate = in.readLong();
        userType = in.readString();
        userId = in.readLong();
        acceptRate = in.readLong();
        location = in.readString();
        websiteUrl = in.readString();
        link = in.readString();
        profileImage = in.readString();
        displayName = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(badgeCounts, 0);
        dest.writeLong(accountId);
        dest.writeByte((byte) (isEmployee ? 1 : 0));
        dest.writeLong(lastModifiedDate);
        dest.writeLong(lastAccessDate);
        dest.writeLong(age);
        dest.writeLong(reputationChangeYear);
        dest.writeLong(reputationChangeQuarter);
        dest.writeLong(reputationChangeMonth);
        dest.writeLong(reputationChangeWeek);
        dest.writeLong(reputationChangeDay);
        dest.writeLong(reputation);
        dest.writeLong(creationDate);
        dest.writeString(userType);
        dest.writeLong(userId);
        dest.writeLong(acceptRate);
        dest.writeString(location);
        dest.writeString(websiteUrl);
        dest.writeString(link);
        dest.writeString(profileImage);
        dest.writeString(displayName);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    public BadgeCounts getBadgeCounts() {
        return badgeCounts;
    }

    public void setBadgeCounts(BadgeCounts badgeCounts) {
        this.badgeCounts = badgeCounts;
    }

    public User withBadgeCounts(BadgeCounts badgeCounts) {
        this.badgeCounts = badgeCounts;
        return this;
    }

    public long getAccountId() {
        return accountId;
    }

    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }

    public User withAccountId(long accountId) {
        this.accountId = accountId;
        return this;
    }

    public boolean isIsEmployee() {
        return isEmployee;
    }

    public void setIsEmployee(boolean isEmployee) {
        this.isEmployee = isEmployee;
    }

    public User withIsEmployee(boolean isEmployee) {
        this.isEmployee = isEmployee;
        return this;
    }

    public long getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(long lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public User withLastModifiedDate(long lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
        return this;
    }

    public long getLastAccessDate() {
        return lastAccessDate;
    }

    public void setLastAccessDate(long lastAccessDate) {
        this.lastAccessDate = lastAccessDate;
    }

    public User withLastAccessDate(long lastAccessDate) {
        this.lastAccessDate = lastAccessDate;
        return this;
    }

    public long getAge() {
        return age;
    }

    public void setAge(long age) {
        this.age = age;
    }

    public User withAge(long age) {
        this.age = age;
        return this;
    }

    public long getReputationChangeYear() {
        return reputationChangeYear;
    }

    public void setReputationChangeYear(long reputationChangeYear) {
        this.reputationChangeYear = reputationChangeYear;
    }

    public User withReputationChangeYear(long reputationChangeYear) {
        this.reputationChangeYear = reputationChangeYear;
        return this;
    }

    public long getReputationChangeQuarter() {
        return reputationChangeQuarter;
    }

    public void setReputationChangeQuarter(long reputationChangeQuarter) {
        this.reputationChangeQuarter = reputationChangeQuarter;
    }

    public User withReputationChangeQuarter(long reputationChangeQuarter) {
        this.reputationChangeQuarter = reputationChangeQuarter;
        return this;
    }

    public long getReputationChangeMonth() {
        return reputationChangeMonth;
    }

    public void setReputationChangeMonth(long reputationChangeMonth) {
        this.reputationChangeMonth = reputationChangeMonth;
    }

    public User withReputationChangeMonth(long reputationChangeMonth) {
        this.reputationChangeMonth = reputationChangeMonth;
        return this;
    }

    public long getReputationChangeWeek() {
        return reputationChangeWeek;
    }

    public void setReputationChangeWeek(long reputationChangeWeek) {
        this.reputationChangeWeek = reputationChangeWeek;
    }

    public User withReputationChangeWeek(long reputationChangeWeek) {
        this.reputationChangeWeek = reputationChangeWeek;
        return this;
    }

    public long getReputationChangeDay() {
        return reputationChangeDay;
    }

    public void setReputationChangeDay(long reputationChangeDay) {
        this.reputationChangeDay = reputationChangeDay;
    }

    public User withReputationChangeDay(long reputationChangeDay) {
        this.reputationChangeDay = reputationChangeDay;
        return this;
    }

    public long getReputation() {
        return reputation;
    }

    public void setReputation(long reputation) {
        this.reputation = reputation;
    }

    public User withReputation(long reputation) {
        this.reputation = reputation;
        return this;
    }

    public long getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(long creationDate) {
        this.creationDate = creationDate;
    }

    public User withCreationDate(long creationDate) {
        this.creationDate = creationDate;
        return this;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public User withUserType(String userType) {
        this.userType = userType;
        return this;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public User withUserId(long userId) {
        this.userId = userId;
        return this;
    }

    public long getAcceptRate() {
        return acceptRate;
    }

    public void setAcceptRate(long acceptRate) {
        this.acceptRate = acceptRate;
    }

    public User withAcceptRate(long acceptRate) {
        this.acceptRate = acceptRate;
        return this;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public User withLocation(String location) {
        this.location = location;
        return this;
    }

    public String getWebsiteUrl() {
        return websiteUrl;
    }

    public void setWebsiteUrl(String websiteUrl) {
        this.websiteUrl = websiteUrl;
    }

    public User withWebsiteUrl(String websiteUrl) {
        this.websiteUrl = websiteUrl;
        return this;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public User withLink(String link) {
        this.link = link;
        return this;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public User withProfileImage(String profileImage) {
        this.profileImage = profileImage;
        return this;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public User withDisplayName(String displayName) {
        this.displayName = displayName;
        return this;
    }

}