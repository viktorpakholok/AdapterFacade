package ua.edu.ucu.apps.task1.myuser;

import java.time.LocalDate;

import ua.edu.ucu.apps.task1.twitter.TwitterUser;

public class MyTwitterUser implements User {
    private TwitterUser twitterUser;
    
        public MyTwitterUser(TwitterUser twitterUser) {
            this.twitterUser = twitterUser;
    }

    @Override
    public String getEmail() {
        return twitterUser.getUserMail();
    }

    @Override
    public String getCountry() {
        return twitterUser.getCountry().toString();
    }

    @Override
    public LocalDate getLastActiveTime() {
        return twitterUser.getLastActiveTime();
    }

}
