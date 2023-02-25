package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repositories.StudentRepository;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;


@Service
public class StudentService {

   private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    private final Logger logger = LoggerFactory.getLogger(StudentService.class);

    public Student createStudent(Student student) {
        logger.info("Request to create student {}", student);
        return studentRepository.save(student);
    }

    public Student findStudent(long id) {
        logger.info("Request to getting student by id {}", id);
        return studentRepository.findById(id).orElse(null);
    }

    public Student editStudent(Student student) {
        logger.info("Request to edit student {}", student);
        if (studentRepository.existsById(student.getId())) {
            return studentRepository.save(student);
        }
        logger.error("Request to student not find");
        return null;
    }

    public void deleteStudent(long id) {
        logger.info("Request to delete student");
        studentRepository.deleteById(id);
    }

    public Collection<Student> getAll() {
        logger.info("Request to getting all students");
        return studentRepository.findAll();
    }

    public List<Student> findByAge(int age){
        logger.info("Request to getting student by age {}", age);
        return studentRepository.findByAge(age);
    }

    public Collection<Student> getAllStudentByAgeBetween(int ageMin, int ageMax) {
        logger.debug("Request to getting students aged from {}, to {}", ageMax, ageMax);
        return studentRepository.findByAgeBetween(ageMin, ageMax);
    }

    public Collection<Student> getFaculty(Long id) {
        logger.info("Request to getting faculty of student id {}", id);
        return studentRepository.findStudentsByFacultyId(id);
    }

    public Integer getAllStudents() {
        logger.info("Request to getting number of students");
        return studentRepository.getAllStudents();
    }

    public Double getAverageAge() {
        logger.info("Request to getting average age of students");
        return studentRepository.findAll()
                .stream()
                .collect(Collectors.averagingDouble(Student::getAge));
    }

    public List<Student> getLastStudents() {
        logger.info("Request to getting last 5 students");
        return studentRepository.getLastStudent();
    }

    public List<Student> getStudentsByName(String name) {
        logger.info("Request to getting student by name {}", name);
        return studentRepository.findStudentsByNameIgnoreCase(name);
    }

    public List<String> filterStudentsByNameBeginsWithLetterA() {
        logger.info("Request to getting all students whose name begins with a letter A");
        return studentRepository.findAll()
                .stream()
                .filter(name -> (name.getName().startsWith("А")))
                .map(name -> name.getName().toUpperCase())
                .sorted()
                .collect(Collectors.toList());
    }

    public Integer timeRequest() {
        long start = System.currentTimeMillis();
//        int sum = Stream
//                .iterate(1, a -> a +1)
//                .limit(1_000_000)
//                .parallel()
//                .reduce(0, Integer::sum);
        int num = 1_000_000;

        int result = IntStream
                .range(1, num + 1)
                .sum();

        long timeRequest = System.currentTimeMillis() - start;

        logger.info("Request to getting time request: " + timeRequest + "ms");

        return result;
    }
}
