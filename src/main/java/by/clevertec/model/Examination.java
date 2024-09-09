package by.clevertec.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Examination {

    private int id;
    private int studentId;
    private int exam1;
    private int exam2;
    private int exam3;

    public int getId() {
        return id;
    }

    public int getExam1() {
        return exam1;
    }

    public int getExam2() {
        return exam2;
    }

    public int getExam3() {
        return exam3;
    }

    public int getStudentId() {
        return studentId;
    }
}
