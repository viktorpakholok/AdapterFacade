package ua.edu.ucu.apps.task1;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;

import ua.edu.ucu.apps.task1.myuser.User;

public class MessageSender {

    public void send(String text, List<User> users, String country) {
        for (User user : users) {
            if (Duration.between(LocalDate.now(), user.getLastActiveTime()).toSeconds() > 3600) {
                continue;
            }

            if (!user.getCountry().equalsIgnoreCase(country)) {
                continue;
            }

            System.out.println(
                "Sent message this messsage: " 
                + text + " to " + user.getEmail()
            );
        }
    }   
}
