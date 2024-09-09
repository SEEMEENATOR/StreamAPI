package by.clevertec.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Car {

    private int id;
    private String vin;
    private String carMake;
    private String carModel;
    private int releaseYear;
    private String color;

    /**
     * Kilograms
     */
    private int mass;

    /**
     * Dollars ($)
     */
    private int price;

    public int getPrice() {
        return price;
    }

    public int getId() {
        return id;
    }

    public String getVin() {
        return vin;
    }

    public String getCarModel() {
        return carModel;
    }

    public String getCarMake() {
        return carMake;
    }

    public String getColor() {
        return color;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public int getMass() {
        return mass;
    }
}
