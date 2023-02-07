package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repositories.StudentRepository;

import java.util.*;

@Service
public class StudentService {

   private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    public Student findStudent(long id) {
        return studentRepository.findById(id).orElse(null);
    }

    public Student editStudent(Student student) {
        if (studentRepository.existsById(student.getId())) {
            return studentRepository.save(student);
        }
        return null;
    }

    public void deleteStudent(long id) {
        studentRepository.deleteById(id);
    }

    public Collection<Student> getAll() {
        return studentRepository.findAll();
    }

    public List<Student> findByAge(int age){
        return studentRepository.findByAge(age);
    }

    public Collection<Student> getAllStudentByAgeBetween(int ageMin, int ageMax) {
        return studentRepository.findByAgeBetween(ageMin, ageMax);
    }

    public Collection<Student> getFaculty(Long id) {
        return studentRepository.findStudentsByFacultyId(id);
    }

    public Integer getAllStudents() {
        return studentRepository.getAllStudents();
    }

    public Double getAverageAge() {
        return studentRepository.getAverageAge( );
    }

    public List<Student> getLastStudents() {
        return studentRepository.getLastStudent();
    }

    public List<Student> getStudentsByName(String name) {
        return studentRepository.findStudentsByNameIgnoreCase(name);
    }
}
