package ru.hogwarts.school.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.hogwarts.school.model.Student;

import java.util.Collection;
import java.util.List;

public interface StudentRepository extends JpaRepository<Student,Long> {
    List<Student> findByAge(int age);
    Collection<Student> findByAgeBetween(int ageMin, int ageMax);

    Collection<Student> findStudentsByFacultyId(Long id);

    @Query(value = "SELECT COUNT(*) as count FROM student", nativeQuery = true)
    Integer getAllStudents();

    @Query(value = "SELECT AVG(age) as count FROM student", nativeQuery = true)
    Double getAverageAge();

    @Query(value = "SELECT * FROM student ORDER BY  id DESC LIMIT 5", nativeQuery = true)
    List<Student> getLastStudent();


    List<Student> findStudentsByNameIgnoreCase(String name);
}
