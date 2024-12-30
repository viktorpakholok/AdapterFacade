package ua.edu.ucu.apps.task3;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor @ToString @Getter @Setter
public class Company {
    private String name;
    private String description;
    private String logo;

    public Company() {
        name = "NaN";
        description = "NaN";
        logo = "NaN";
    }

    public Company(Company otherCompany) {
        name = otherCompany.name;
        description = otherCompany.description;
        logo = otherCompany.logo;
    }
}
