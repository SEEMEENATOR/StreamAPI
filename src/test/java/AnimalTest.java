import by.clevertec.model.Animal;
import by.clevertec.model.Person;
import by.clevertec.model.Student;
import by.clevertec.util.Util;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.time.Period;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static by.clevertec.Main.task21;
import static org.junit.jupiter.api.Assertions.*;

public class AnimalTest {

    private List<Animal> animals;
    private List<Student> students;
    private List<Person> persons;

    @BeforeEach
    void setUp() {
        animals = Util.getAnimals();
        students = Util.getStudents();
        persons = Util.getPersons();
    }

    @Test
    void testTask1() {
        List<Animal> result = animals.stream()
                .filter(animal -> animal.getAge() >= 10 && animal.getAge() <= 20)
                .sorted(Comparator.comparingInt(Animal::getAge))
                .skip(2 * 7L)
                .limit(7)
                .toList();

        assertEquals(7, result.size());
        assertTrue(result.stream().allMatch(animal -> animal.getAge() >= 10 && animal.getAge() <= 20));

        for (int i = 1; i < result.size(); i++) {
            assertTrue(result.get(i - 1).getAge() <= result.get(i).getAge());
        }
    }

    @Test
    void testTask2() {
        List<Animal> animals = Util.getAnimals();

        List<String> japaneseBreeds = animals.stream()
                .filter(animal -> "Japanese".equals(animal.getOrigin()))
                .map(animal -> "Female".equals(animal.getGender()) ? animal.getBread().toUpperCase() : animal.getBread())
                .collect(Collectors.toList());

        assertNotNull(japaneseBreeds);
        assertFalse(japaneseBreeds.isEmpty());

        List<String> expectedBreeds = animals.stream()
                .filter(animal -> "Japanese".equals(animal.getOrigin()))
                .map(animal -> "Female".equals(animal.getGender()) ? animal.getBread().toUpperCase() : animal.getBread())
                .collect(Collectors.toList());

        assertEquals(expectedBreeds, japaneseBreeds);
    }


    @Test
    void testTask3() {
        List<String> result = animals.stream()
                .filter(animal -> animal.getAge() > 30)
                .map(Animal::getOrigin)
                .distinct()
                .filter(origin -> origin.startsWith("A"))
                .toList();

        assertTrue(result.stream().distinct().count() == result.size());

        assertTrue(result.stream().allMatch(origin -> origin.startsWith("A")));

        assertTrue(result.size() <= animals.size());
    }

    @Test
    void testTask4() {
        long femaleCount = animals.stream()
                .filter(animal -> "Female".equals(animal.getGender()))
                .count();
        assertTrue(femaleCount > 0);
        assertEquals(femaleCount, animals.stream().filter(animal -> "Female".equals(animal.getGender())).count());
    }

    @Test
    void testTask5() {
        boolean hasHungrianAnimal = animals.stream()
                .filter(animal -> animal.getAge() >= 20 && animal.getAge() <= 30)
                .anyMatch(animal -> "Hungarian".equals(animal.getOrigin()));
        assertNotNull(hasHungrianAnimal);
        assertFalse(animals.stream().noneMatch(animal -> animal.getAge() >= 20 && animal.getAge() <= 30 && "Hungarian".equals(animal.getOrigin())));
    }



    @Test
    void testTask7() {
        boolean hasOceaniaOrigin = animals.stream()
                .noneMatch(animal -> "Oceania".equals(animal.getOrigin()));
        assertTrue(hasOceaniaOrigin);
    }

    @Test
    void testTask8() {
        int oldestAnimal = animals.stream()
                .sorted(Comparator.comparing(Animal::getBread))
                .limit(100)
                .max(Comparator.comparing(Animal::getAge))
                .map(Animal::getAge)
                .orElse(-1);
        assertNotEquals(-1, oldestAnimal);
    }
    @Test
    void testTask9() {
        List<Animal> animals = Util.getAnimals();

        int shortestLength = animals.stream()
                .map(animal -> animal.getBread().toCharArray())
                .mapToInt(charArray -> charArray.length)
                .min()
                .orElse(0);

        assertNotEquals(0, shortestLength, "Список животных пуст или не содержит валидных данных.");

        int expectedShortestLength = 3;
        assertEquals(expectedShortestLength, shortestLength, "Минимальная длина 'bread' не соответствует ожидаемому значению.");
    }
    @Test
    void testTask17() {
        Set<String> uniqueGroups = students.stream()
                .map(Student::getGroup)
                .collect(Collectors.toSet());
        assertNotNull(uniqueGroups);
        assertTrue(uniqueGroups.size() > 0);
    }

    @Test
    void testTask16() {
        List<Student> students = Util.getStudents();
        List<Student> studentsUnder18 = students.stream()
                .filter(student -> student.getAge() < 18)
                .sorted(Comparator.comparing(Student::getSurname))
                .collect(Collectors.toList());

        assertNotNull(studentsUnder18, "The list of students under 18 should not be null.");
        assertTrue(studentsUnder18.isEmpty() || studentsUnder18.size() > 0, "The list should contain at least one student or be empty.");

        for (int i = 0; i < studentsUnder18.size() - 1; i++) {
            assertTrue(studentsUnder18.get(i).getSurname().compareTo(studentsUnder18.get(i + 1).getSurname()) <= 0,
                    "Students are not sorted alphabetically by surname.");
        }
    }

    @Test
    void testTask12() {
        List<Person> candidates = persons.stream()
                .filter(person -> "Male".equals(person.getGender()))
                .filter(person -> {
                    LocalDate birthDate = person.getDateOfBirth();
                    int age = Period.between(birthDate, LocalDate.now()).getYears();
                    return age >= 18 && age <= 27;
                })
                .sorted(Comparator.comparingInt(Person::getRecruitmentGroup))
                .limit(200)
                .collect(Collectors.toList());
        assertNotNull(candidates);
        assertTrue(candidates.size() <= 200);
        assertTrue(candidates.stream().allMatch(person -> "Male".equals(person.getGender())));
        assertTrue(candidates.stream().allMatch(person -> {
            int age = Period.between(person.getDateOfBirth(), LocalDate.now()).getYears();
            return age >= 18 && age <= 27;
        }));
        assertTrue(candidates.stream().sorted(Comparator.comparingInt(Person::getRecruitmentGroup)).collect(Collectors.toList()).equals(candidates));
    }
}
