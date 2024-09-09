package by.clevertec.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Person {

    private int id;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String email;
    private String gender;
    private int recruitmentGroup;
    private String city;
    private String occupation;

    public int getId() {
        return id;
    }

    public String getOccupation() {
        return occupation;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public int getRecruitmentGroup() {
        return recruitmentGroup;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getEmail() {
        return email;
    }

    public String getCity() {
        return city;
    }

    public String getLastName() {
        return lastName;
    }

    public String getGender() {
        return gender;
    }

}
