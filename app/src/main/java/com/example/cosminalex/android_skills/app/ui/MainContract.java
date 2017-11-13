package com.example.cosminalex.android_skills.app.ui;

import com.example.cosminalex.android_skills.commons.models.User;

import java.util.List;

/**
 * Created by cosminalex on 13.11.2017.
 */

public interface MainContract {
    interface View{


        void loadUsers(List<User> items);

        void showError();

    }

    interface Presenter{

        void getUsers();
    }
}
