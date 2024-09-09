package by.clevertec.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class House {

    private int id;
    private String buildingType;
    private List<Person> personList;

    public int getId() {
        return id;
    }

    public List<Person> getPersonList() {
        return personList;
    }

    public String getBuildingType() {
        return buildingType;
    }
}
