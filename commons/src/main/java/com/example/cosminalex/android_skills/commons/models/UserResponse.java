package com.example.cosminalex.android_skills.commons.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cosminalex on 13.11.2017.
 */

public class UserResponse {
    public long getExpiringTime() {
        return expiringTime;
    }

    public void setExpiringTime(long expiringTime) {
        this.expiringTime = expiringTime;
    }

    private long expiringTime;

    public List<User> getItems() {
        return items;
    }

    public void setItems(List<User> items) {
        this.items = items;
    }

    private List<User> items = new ArrayList<>();
}
