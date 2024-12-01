package ua.edu.ucu.apps.task1.twitter;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter @AllArgsConstructor
public class TwitterUser {
    private String userMail;
    private TwitterCountry country;
    private LocalDate lastActiveTime;
}
