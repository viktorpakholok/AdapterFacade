package ua.edu.ucu.apps.task1.myuser;

import java.time.LocalDate;

public interface User {
    String getEmail();
    String getCountry();
    LocalDate getLastActiveTime();
}
