package ru.hogwarts.school.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.service.FacultyService;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("faculties")
public class FacultyController {
    private final FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;}


    @PostMapping
    public Faculty createFaculty(@RequestBody Faculty faculty ) {
        return facultyService.createFaculty(faculty);
    }

    @GetMapping("{id}")
    public ResponseEntity<Faculty> getFaculty(@PathVariable Long id) {
        Faculty findFaculties = facultyService.findFaculty(id);
        if (findFaculties == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(findFaculties);
    }

    @PutMapping
    public ResponseEntity<Faculty> editFaculty(@RequestBody Faculty faculty) {
        Faculty editFaculties = facultyService.editFaculty(faculty);
        if (editFaculties == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(editFaculties);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteFaculty(@PathVariable long id) {
        facultyService.deleteFaculty(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<Collection<Faculty>> getAll() {
        return ResponseEntity.ok(facultyService.getAll());
    }

    @GetMapping("/filter/{color}")
    public ResponseEntity<List<Faculty>> getColor(@PathVariable String color) {
        return ResponseEntity.ok(facultyService.findByColor(color));
    }

    @GetMapping("colorOrName")
    public ResponseEntity<Collection<Faculty>> getByNameOrColor(@RequestParam(required = false) String name,
                                                                @RequestParam(required = false) String color) {
        return ResponseEntity.ok(facultyService.getFacultyByNameOrColor(name, color));
    }

    @GetMapping("students/{id}")
    public ResponseEntity<Collection<Faculty>> getStudents(@PathVariable Long id) {
        return ResponseEntity.ok(facultyService.getStudents(id));
    }

    @GetMapping("/faculty/{name}/{color}")
    public ResponseEntity<Collection<Faculty>> getFacultyByNameAndColor(@PathVariable("name") String name,
                                                                        @PathVariable("color") String color) {
        Collection<Faculty> faculties = facultyService.getFacultyByNameAndColor(name, color);
        return ResponseEntity.ok(faculties);
    }

    @GetMapping("/lognest-name")
    public ResponseEntity<String> findLongestName() {
        return ResponseEntity.ok(facultyService.findLongestName());
    }
}
