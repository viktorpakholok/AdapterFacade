package ua.edu.ucu.apps.task1.myuser;

import java.time.LocalDate;

import ua.edu.ucu.apps.task1.meta.FacebookUser;

public class MyFacebookUser implements User {
    private FacebookUser facebookUser;
    
        public MyFacebookUser(FacebookUser facebookUser) {
            this.facebookUser = facebookUser;
    }

    @Override
    public String getEmail() {
        return facebookUser.getEmail();
    }

    @Override
    public String getCountry() {
        return facebookUser.getUserCountry().toString();
    }

    @Override
    public LocalDate getLastActiveTime() {
        return facebookUser.getUserActiveTime();
    }

}
