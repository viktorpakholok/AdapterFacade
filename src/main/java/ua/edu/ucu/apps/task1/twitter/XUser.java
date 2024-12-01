package ua.edu.ucu.apps.task1.twitter;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter @AllArgsConstructor
public class XUser {
    private String email;
    private XCountry userCountry;
    private LocalDate userActiveTime;
}
