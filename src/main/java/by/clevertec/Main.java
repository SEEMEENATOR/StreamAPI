package by.clevertec;

import by.clevertec.model.Animal;
import by.clevertec.model.Car;
import by.clevertec.model.Examination;
import by.clevertec.model.Flower;
import by.clevertec.model.House;
import by.clevertec.model.Person;
import by.clevertec.model.Student;
import by.clevertec.util.Util;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        task1();
        task2();
        task3();
        task4();
        task5();
        task6();
        task7();
        task8();
        task9();
        task10();
        task11();
        task12();
        task13();
        task14();
        task15();
        task16();
        task17();
        task18();
        task19();
        task20();
        task21();
        task22();
    }

    public static void task1() {
        List<Animal> animals = Util.getAnimals();

        animals.stream()
                .filter(animal -> animal.getAge() >=10 && animal.getAge() <= 20)
                .sorted(Comparator.comparingInt(Animal::getAge))
                .skip(2 * 7l)
                .limit(7)
                .forEach(System.out::println);


    }

    public static void task2() {
        List<Animal> animals = Util.getAnimals();
       /* Отобрать всех животных из Японии (Japanese) и записать породу UPPER_CASE в если пол
        Female преобразовать к строкам породы животных и вывести в консоль*/
        animals.stream()
                .filter(animal -> "Japanese".equals(animal.getOrigin()))
                .map(animal -> {
                    String breed = "Female".equals(animal.getGender()) ? animal.getBread().toUpperCase() : animal.getBread();
                    return breed;
                })
                .forEach(System.out::println);
    }

    public static void task3() {
        List<Animal> animals = Util.getAnimals();
        /*Отобрать всех животных старше 30 лет и вывести все
        страны происхождения без дубликатов начинающиеся на "A"*/
        animals.stream()
                .filter(animal -> animal.getAge() > 30)
                .map(Animal::getOrigin)
                .distinct()
                .filter(origin -> origin.startsWith("A"))
                .forEach(System.out::println);
    }

    public static void task4() {
        List<Animal> animals = Util.getAnimals();
//        Подсчитать количество всех животных пола = Female. Вывести в консоль
        long femaleCount = animals.stream()
                .filter(animal -> "Female".equals(animal.getGender()))
                .count();
        System.out.print(femaleCount + "\n");
    }

    public static void task5() {
        List<Animal> animals = Util.getAnimals();
/*  Взять всех животных возрастом 20 - 30 лет. Есть ли среди нах хоть
        один из страны Венгрия (Hungarian)? Ответ вывести в консоль*/
        boolean hasHungrianAniml = animals.stream()
                .filter(animal -> animal.getAge() >= 20 && animal.getAge() <= 30)
                .anyMatch(animal -> "Hungarian".equals(animal.getOrigin()));
        System.out.print("Есть ли животное из Венгрии от 20 до 30 лет? " + (hasHungrianAniml ? "Да" : "Нет") + "\n");


    }

    public static void task6() {
        List<Animal> animals = Util.getAnimals();
        /*Взять всех животных. Все ли они пола Male и Female ? Ответ вывести в консоль*/
        boolean allOrigin = animals.stream()
                .allMatch(animal -> "Male".equals(animal.getGender()) || "Female".equals(animal.getGender()));
        System.out.println("Все ли они пола Male и Female? " + (allOrigin ? "Да" : "Нет") + "\n");
    }

    public static void task7() {
        List<Animal> animals = Util.getAnimals();
   /*    Взять всех животных. Узнать что ни одно из них не имеет
      страну происхождения Oceania. Ответ вывести в консоль*/
        boolean hasOceaniaOrigin =  animals.stream()
                .noneMatch(animal -> "Oceania".equals(animal.getOrigin()));

        System.out.println("Ни одно животное не имеет страну происхождения Oceania? " + (hasOceaniaOrigin ? "Да" : "Нет"));
    }

    public static void task8() {
        List<Animal> animals = Util.getAnimals();
      /*  Взять всех животных. Отсортировать их породу в стандартном порядке и взять первые 100.
        Вывести в консоль возраст самого старого животного*/
        int oldestAnimal = animals.stream()
                .sorted(Comparator.comparing(Animal::getBread))
                .limit(100)
                .max(Comparator.comparing(Animal::getAge))
                .map(Animal::getAge)
                .orElse(-1);

        System.out.println("Возраст самого старого животного: " + (oldestAnimal == -1 ? "Нет доступных животных." : oldestAnimal));

    }

    public static void task9() {
        List<Animal> animals = Util.getAnimals();
        int shortestLength = animals.stream()
                .map(animal -> animal.getBread().toCharArray())
                .mapToInt(charArray -> charArray.length)
                .min()
                .orElse(0);
        System.out.println("Самый краткий массив " + shortestLength);
    }

    public static void task10() {
        List<Animal> animals = Util.getAnimals();
//        Взять всех животных. Подсчитать суммарный возраст всех животных. Вывести результат в консоль
        int sumAge = animals.stream()
                .mapToInt(Animal::getAge)
                .sum();
        System.out.println("Суммарный возраст всех животных" + sumAge);
    }

    public static void task11() {
        List<Animal> animals = Util.getAnimals();
//        Взять всех животных. Подсчитать средний возраст
//        всех животных из индонезии (Indonesian). Вывести результат в консоль
        OptionalDouble averageAge = animals.stream()
                .filter(animal -> "Indonesian".equals(animal.getOrigin()))
                .mapToInt(Animal::getAge)
                .average();
        System.out.println("Средний возраст животных: " + averageAge.getAsDouble());
    }

    public static void task12() {
        List<Person> persons = Util.getPersons();
           /* Во Французский легион принимают людей со всего света, но есть отбор по полу (только мужчины)
        возраст от 18 до 27 лет. Преимущество отдаётся людям военной категории 1,
        на втором месте - военная категория 2, и на третьем месте военная категория 3.
         Отсортировать всех подходящих кандидатов в порядке их приоритета по военной категории.
         Однако взять на обучение академия может только 200 человек. Вывести их в консоль.*/
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        persons.stream().
                filter(person -> "Male".equals(person.getGender()))
                .filter(person -> {
                    LocalDate birthDate = person.getDateOfBirth();
                    int age = Period.between(birthDate,LocalDate.now()).getYears();
                    return age>=18 && age<=27;
                })
                .sorted((p1,p2) -> Integer.compare(p1.getRecruitmentGroup(),p2.getRecruitmentGroup()))
                .limit(200)
                .collect(Collectors.toList())
                .forEach(System.out::println);
    }

    public static void task13() {
        List<House> houses = Util.getHouses();
        List<Person> evacuees = houses.stream()
                .flatMap(house -> house.getPersonList().stream())
                .filter(person -> Period.between(person.getDateOfBirth(), LocalDate.now()).getYears() < 18 ||
                        Period.between(person.getDateOfBirth(), LocalDate.now()).getYears() >= 60 ||
                        person.getRecruitmentGroup() == 1)
                .sorted(Comparator
                        .comparingInt((Person p) -> p.getRecruitmentGroup() == 1 ? 0 : 1)
                        .thenComparing(p -> Period.between(p.getDateOfBirth(), LocalDate.now()).getYears() < 18 ? 0 : 1)
                        .thenComparing(p -> Period.between(p.getDateOfBirth(), LocalDate.now()).getYears() >= 60 ? 0 : 1)
                        .thenComparing(Person::getDateOfBirth))
                .limit(500)
                .collect(Collectors.toList());

        evacuees.forEach(person -> System.out.println(person.getFirstName() + " " + person.getLastName()));

    }

    public static void task14() {
        List<Car> cars = Util.getCars();

        double turkmenistanMass = cars.stream()
                .filter(car -> car.getCarMake().equals("Jaguar") || car.getColor().equals("White"))
                .mapToDouble(Car::getMass)
                .sum();

        double uzbekistanMass = cars.stream()
                .filter(car -> car.getMass() <= 1500 &&
                        (car.getCarMake().equals("BMW") || car.getCarMake().equals("Lexus") ||
                                car.getCarMake().equals("Chrysler") || car.getCarMake().equals("Toyota")))
                .mapToDouble(Car::getMass)
                .sum();

        double kazakhstanMass = cars.stream()
                .filter(car -> (car.getColor().equals("Black") && car.getMass() > 4000) ||
                        car.getCarMake().equals("GMC") || car.getCarMake().equals("Dodge"))
                .mapToDouble(Car::getMass)
                .sum();

        double kyrgyzstanMass = cars.stream()
                .filter(car -> car.getReleaseYear() < 1982 ||
                        car.getCarModel().equals("Civic") || car.getCarModel().equals("Cherokee"))
                .mapToDouble(Car::getMass)
                .sum();

        double russiaMass = cars.stream()
                .filter(car -> (!car.getColor().equals("Yellow") && !car.getColor().equals("Red") &&
                        !car.getColor().equals("Green") && !car.getColor().equals("Blue")) ||
                        car.getPrice() > 40000)
                .mapToDouble(Car::getMass)
                .sum();

        double mongoliaMass = cars.stream()
                .filter(car -> car.getVin().contains("59"))
                .mapToDouble(Car::getMass)
                .sum();

        Map<String, Double> transportCosts = new HashMap<>();
        transportCosts.put("Туркменистан", turkmenistanMass * 7.14 / 1000);
        transportCosts.put("Узбекистан", uzbekistanMass * 7.14 / 1000);
        transportCosts.put("Казахстан", kazakhstanMass * 7.14 / 1000);
        transportCosts.put("Кыргызстан", kyrgyzstanMass * 7.14 / 1000);
        transportCosts.put("Россия", russiaMass * 7.14 / 1000);
        transportCosts.put("Монголия", mongoliaMass * 7.14 / 1000);

        transportCosts.forEach((country, cost) -> System.out.println("Транспортные расходы для " + country + ": $" + cost));

        double totalRevenue = transportCosts.values().stream().mapToDouble(Double::doubleValue).sum();
        System.out.println("Общая выручка логистической кампании: $" + totalRevenue);

    }

    public static void task15() {
        List<Flower> flowers = Util.getFlowers();
        double waterCostPerCubicMeter = 1.39;

        double totalCost = flowers.stream()
                .sorted(Comparator.comparing(Flower::getOrigin).reversed()
                        .thenComparing(Flower::getPrice)
                        .thenComparing(Comparator.comparingDouble(Flower::getWaterConsumptionPerDay).reversed()))
                .filter(flower -> {
                    String name = flower.getCommonName().toUpperCase();
                    return name.compareTo("C") >= 0 && name.compareTo("S") <= 0;
                })
                .filter(Flower::isShadePreferred)
                .filter(flower -> flower.getFlowerVaseMaterial().stream()
                        .anyMatch(material -> Arrays.asList("Glass", "Aluminum", "Steel")
                                .contains(material)))
                .mapToDouble(flower -> flower.getPrice() +
                        ((flower.getWaterConsumptionPerDay() * 365 / 1000) * waterCostPerCubicMeter * 5))
                .sum();

        System.out.printf("Общая стоимость обслуживания всех растений: %.2f $\n", totalCost);

    }

    public static void task16() {
        List<Student> students = Util.getStudents();
//Вывод списка студентов младше 18 лет в алфавитном порядке с указанием возраста
        students.stream()
                .filter(student -> student.getAge() < 18 )
                .sorted(Comparator.comparing(Student::getSurname))
                .collect(Collectors.toList())
                .forEach(student ->
                        System.out.println(student.getSurname() + " - " + student.getAge() + " years old")
                );
    }

    public static void task17() {
        List<Student> students = Util.getStudents();
        //Вывод списка групп (без повторений).
        students.stream()
                .map(Student::getGroup)
                .collect(Collectors.toSet())
                .forEach(System.out::println);

    }

    public static void task18() {
        List<Student> students = Util.getStudents();
        List<Examination> examinations = Util.getExaminations();
//       Определение среднего возраста студентов для каждого факультета.
//       Выводить название факультета и средний возраст в порядке убывания возраста.
        Map<String, Double> averageAgeByFaculty = students.stream()
                .collect(Collectors.groupingBy(
                        Student::getFaculty,
                        Collectors.averagingInt(Student::getAge)
                ));

        List<Map.Entry<String, Double>> sortedFaculties = averageAgeByFaculty.entrySet().stream()
                .sorted(Map.Entry.<String, Double>comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toList());

        sortedFaculties.forEach(entry ->
                System.out.println(entry.getKey() + " - " + String.format("%.2f", entry.getValue()) + " years")
        );
    }

    public static void task19() {
        List<Student> students = Util.getStudents();
        List<Examination> examinations = Util.getExaminations();

        Map<Integer, String> studentGroupMap = students.stream()
                .collect(Collectors.toMap(Student::getId, Student::getGroup));

        List<Integer> studentsWithAllExamsPassed = examinations.stream()
                .filter(exam -> exam.getExam1() > 4 && exam.getExam2() > 4 && exam.getExam3() > 4)
                .map(Examination::getStudentId)
                .distinct()
                .collect(Collectors.toList());

        Map<String, Long> studentCount = students.stream()
                .filter(student -> studentsWithAllExamsPassed.contains(student.getId()))
                .collect(Collectors.groupingBy(Student::getGroup, Collectors.counting()));

        studentCount.forEach((group, count) -> System.out.println("Группа " + group + ": " + count + " студент(ов)"));
    }

    public static void task20() {
        List<Student> students = Util.getStudents();
        List<Examination> exams = Util.getExaminations();

        Map<Integer, Integer> studentIdToExam1Score = exams.stream()
                .collect(Collectors.toMap(Examination::getStudentId, Examination::getExam1));

        Map<String, Double> averageScoresByFaculty = students.stream()
                .collect(Collectors.groupingBy(
                        Student::getFaculty,
                        Collectors.averagingDouble(student -> studentIdToExam1Score.getOrDefault(student.getId(), 0))
                ));

        Optional<Map.Entry<String, Double>> maxEntry = averageScoresByFaculty.entrySet().stream()
                .max(Map.Entry.comparingByValue());

        maxEntry.map(Map.Entry::getKey)
                .ifPresent(faculty -> System.out.println("Факультет с максимальной средней оценкой по первому экзамену: " + faculty));
    }

    public static void task21() {
        List<Student> students = Util.getStudents();
        Map<String, Long> studentCount =  students.stream()
                .collect(Collectors.groupingBy(Student::getGroup, Collectors.counting()));
        studentCount.forEach((group, count) -> System.out.println("Группа " + group + ": " + count + " студент(ов)"));

    }

    public static void task22() {
        List<Student> students = Util.getStudents();
        Map<String, Integer> minAgeByFaculty = students.stream()
                .collect(Collectors.groupingBy(
                        Student::getFaculty,
                        Collectors.collectingAndThen(
                                Collectors.minBy(Comparator.comparingInt(Student::getAge)),
                                student -> student.map(Student::getAge).orElse(null)
                        )
                ));


        minAgeByFaculty.forEach((faculty, minAge) ->
                System.out.println("Факультет " + faculty + ": минимальный возраст " + minAge)
        );
    }
}
