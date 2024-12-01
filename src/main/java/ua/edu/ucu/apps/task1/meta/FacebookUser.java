package ua.edu.ucu.apps.task1.meta;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter @AllArgsConstructor
public class FacebookUser {
    private String userEmail;
    private String country;
    private LocalDate lastActiveTime;
}
