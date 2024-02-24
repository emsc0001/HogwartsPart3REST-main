package edu.hogwarts.studentadmin;

import edu.hogwarts.studentadmin.models.*;
import edu.hogwarts.studentadmin.repositories.CourseRepository;
import edu.hogwarts.studentadmin.repositories.HouseRepository;
import edu.hogwarts.studentadmin.repositories.StudentRepository;
import edu.hogwarts.studentadmin.repositories.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {
    private CourseRepository courseRepository;
    private StudentRepository studentRepository;
    private TeacherRepository teacherRepository;
    private HouseRepository houseRepository;

    public DataInitializer() {
    }

    @Autowired
    public DataInitializer(CourseRepository courseRepository, StudentRepository studentRepository,
                           TeacherRepository teacherRepository, HouseRepository houseRepository) {
        this.courseRepository = courseRepository;
        this.studentRepository = studentRepository;
        this.teacherRepository = teacherRepository;
        this.houseRepository = houseRepository;
    }

    public void run(String...args) {
        House gryffindor = new House("Gryffindor","Godric Gryffindor",
                List.of(new HouseColor("Red"), new HouseColor("Gold")));
        House hufflepuff = new House("Hufflepuff","Helga Hufflepuff",
                List.of(new HouseColor("Yellow"), new HouseColor("Black")));
        House ravenclaw = new House("Ravenclaw","Rowena Ravenclaw",
                List.of(new HouseColor("Blue"), new HouseColor("Bronze")));
        House slytherin = new House("Slytherin","Salazar Slytherin",
                List.of(new HouseColor("Green"), new HouseColor("Silver")));

        houseRepository.save(gryffindor);
        houseRepository.save(hufflepuff);
        houseRepository.save(ravenclaw);
        houseRepository.save(slytherin);

        Student harryPotter = new Student("Harry", "James", "Potter",
                LocalDate.of(1980, 7, 31),
                true, 1991, 0, false, gryffindor, 4);

        Student cedricDiggory = new Student("Cedric", "", "Diggory",
                LocalDate.of(1977, 9, 1),
                false, 1989, 0, false, hufflepuff, 4);

        Student lunaLovegood = new Student("Luna", "Lovegood", "",
                LocalDate.of(1981, 2, 13),
                false, 1992, 0, false, ravenclaw, 4);

        Student dracoMalfoy = new Student("Draco", "Lucius", "Malfoy",
                LocalDate.of(1980, 6, 5),
                false, 1991, 0, false, slytherin, 4);

        Student dracoBadboy = new Student("my name is hulla bulla ballula",
                LocalDate.of(1980, 6, 5),
                false, 1991, 0, false, slytherin, 2);

        studentRepository.save(harryPotter);
        studentRepository.save(cedricDiggory);
        studentRepository.save(dracoMalfoy);
        studentRepository.save(lunaLovegood);
        studentRepository.save(dracoBadboy);

        Teacher minervaMcGonagall = new Teacher("Minerva", "", "McGonagall", LocalDate.of(1925, 10, 4),
                true, EmploymentType.TENURED, LocalDate.of(1956, 9, 1), null, gryffindor);

        Teacher pomonaSprout = new Teacher("Pomona", "", "Sprout", LocalDate.of(1941, 5, 15),
                true, EmploymentType.TENURED, LocalDate.of(1971, 9, 1), null, hufflepuff);

        Teacher flitwick = new Teacher("Filius", "", "Flitwick", LocalDate.of(1958, 10, 17),
                true, EmploymentType.TENURED, LocalDate.of(1981, 9, 1), null, ravenclaw);

        Teacher severusSnape = new Teacher("Severus", "James", "Snape", LocalDate.of(1960, 1, 9),
                true, EmploymentType.TENURED, LocalDate.of(1981, 9, 1), null, slytherin);

        teacherRepository.save(minervaMcGonagall);
        teacherRepository.save(pomonaSprout);
        teacherRepository.save(flitwick);
        teacherRepository.save(severusSnape);

        Course transfigurationCourse = new Course(minervaMcGonagall, "Transfiguration", 2024, true,
                List.of(harryPotter, dracoMalfoy, lunaLovegood, cedricDiggory));

        Course herbologyCourse = new Course(minervaMcGonagall, "Herbology", 2024, true,
                List.of(harryPotter, dracoMalfoy, lunaLovegood, cedricDiggory));

        Course charmsCourse = new Course(flitwick, "Charms", 2024, true,
                List.of(harryPotter, dracoMalfoy, lunaLovegood, cedricDiggory));

        Course potionsCourse = new Course(severusSnape, "Potions", 2024, true, null);

        courseRepository.save(transfigurationCourse);
        courseRepository.save(herbologyCourse);
        courseRepository.save(charmsCourse);
        courseRepository.save(potionsCourse);
    }
}
