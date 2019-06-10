package com.ibt.complaintmanagement.modal;

import com.ibt.complaintmanagement.modal.user.UserData;

public class User {

    public static UserData user;

    public static UserData getUser() {
        return user;
    }

    public static void setUser(UserData user) {
        User.user = user;
    }
}
