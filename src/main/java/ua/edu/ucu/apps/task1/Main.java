package ua.edu.ucu.apps.task1;

import java.time.LocalDate;

import ua.edu.ucu.apps.task1.meta.FacebookUser;
import ua.edu.ucu.apps.task1.myuser.MyFacebookUser;
import ua.edu.ucu.apps.task1.myuser.User;
import ua.edu.ucu.apps.task1.myuser.MyXUser;
import ua.edu.ucu.apps.task1.twitter.XCountry;
import ua.edu.ucu.apps.task1.twitter.XUser;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        User xUser = new MyXUser(new XUser("dobosevych.gmail.com", XCountry.UKRAINE, LocalDate.now()));
        User facebookUser = new MyFacebookUser(new FacebookUser("dobosevych.gmail.com", "Ukraine", LocalDate.now()));
    }

    public static void processInformation(User user) {
        System.out.println(user.getEmail());
    }
}