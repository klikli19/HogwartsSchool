package ru.hogwarts.school.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;


import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("students")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public Student createStudent(@RequestBody Student student) {
        return studentService.createStudent(student);
    }

    @GetMapping("{id}")
    public ResponseEntity<Student> getStudent(@PathVariable long id) {
        Student student = studentService.findStudent(id);
        if (student == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(student);
    }

    @PutMapping
    public ResponseEntity<Student> editStudent(@RequestBody Student student) {
        Student editStudent = studentService.editStudent(student);
        if (editStudent == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(editStudent);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<Collection<Student>> getAll() {
        return ResponseEntity.ok(studentService.getAll());
    }

    @GetMapping("/filter/{age}")
    public ResponseEntity<List<Student>> findByAge(@PathVariable int age) {
        return ResponseEntity.ok(studentService.findByAge(age));
    }

    @GetMapping("/age-between")
    public ResponseEntity<Collection<Student>> getStudentByAgeBetween(@RequestParam int ageMin,
                                                                      @RequestParam int ageMax) {
        return ResponseEntity.ok(studentService.getAllStudentByAgeBetween(ageMin, ageMax));
    }

    @GetMapping("/faculty/{id}")
    public ResponseEntity<Collection<Student>> getFaculty(@PathVariable Long id) {
        return ResponseEntity.ok(studentService.getFaculty(id));
    }

    @GetMapping("/all")
    public ResponseEntity<Integer> getAllStudents() {
        return ResponseEntity.ok(studentService.getAllStudents());
    }

    @GetMapping("/average-age")
    public ResponseEntity<Double> getAverageAge() {
        return ResponseEntity.ok(studentService.getAverageAge());
    }

    @GetMapping("/last-students")
    public ResponseEntity<List<Student>> getLastStudents() {
        return ResponseEntity.ok(studentService.getLastStudents());
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<List<Student>> getStudentsByName(@PathVariable("name") String name) {
        List<Student> students = studentService.getStudentsByName(name);
        return ResponseEntity.ok(students);
    }

    @GetMapping("filter-begin-letter-a")
    public ResponseEntity<List<String>> filterStudentsByNameBeginsWithLetterA() {
        return ResponseEntity.ok(studentService.filterStudentsByNameBeginsWithLetterA());
    }

    @GetMapping("time-request")
    public Integer timeRequest() {
        return studentService.timeRequest();
    }

    @GetMapping("test-thread")
    public void getStudentsName() {
        studentService.getStudentsNameTestThread();
    }

    @GetMapping("test-thread-synchronized")
    public void getStudentsNameSynchronized() {
        studentService.getStudentsNameTestThreadTwo();
    }

}
