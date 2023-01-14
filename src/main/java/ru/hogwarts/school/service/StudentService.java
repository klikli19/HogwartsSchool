package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;

import java.util.*;

@Service
public class StudentService {
    private final HashMap<Long, Student> students = new HashMap<>();
    private long countId = 0;

    public Student creatStudent(Student student) {
        student.setId(++countId);
        students.put(countId, student);
        return student;
    }

    public Student findStudent(long countId) {
        return students.get(countId);
    }

    public Student editStudent(Student student) {
        if (students.containsKey(student.getId())) {
            students.put(student.getId(), student);
            return student;
        }
        return null;
    }

    public Student deleteStudent(long countId) {
        return students.remove(countId);
    }

    public Collection<Student> getAll() {
        return students.values();
    }

    public Collection<Student> getAge(int age) {
        Collection<Student> list = new ArrayList<>();
        for (long i = 0; i < students.size(); i++) {
            if (students.get(i).getAge() == age) {
                list.add(students.get(i));
            }
        }
        return list;
    }
}
