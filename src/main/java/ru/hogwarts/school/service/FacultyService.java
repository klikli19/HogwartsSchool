package ru.hogwarts.school.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.repositories.FacultyRepository;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;


@Service
public class FacultyService {
    private final FacultyRepository facultyRepository;


    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    private final Logger logger = LoggerFactory.getLogger(FacultyService.class);
    public Faculty createFaculty(Faculty faculty) {
        logger.info("Request to create faculty {}", faculty);
        return facultyRepository.save(faculty);
    }

    public Faculty findFaculty(Long id) {
        logger.info("Request to getting faculty by id {}", id);
        return facultyRepository.findById(id).orElse(null);
    }

    public Faculty editFaculty(Faculty faculty) {
        logger.info("Request to edit faculty {}", faculty);
        if (facultyRepository.existsById(faculty.getId())) {
            return facultyRepository.save(faculty);
        }
        logger.error("Request faculty not find");
        return null;
    }

    public void deleteFaculty(long id) {
        logger.info("Request to delete faculty by id {}", id);
        facultyRepository.deleteById(id);
    }

    public Collection<Faculty> getAll() {
        logger.info("Request to getting all faculties");
        return facultyRepository.findAll();
    }

    public List<Faculty> findByColor (String color) {
        logger.info("Request to getting faculty by color {}", color);
        return facultyRepository.findByColor(color);
    }

    public Collection<Faculty> getFacultyByNameOrColor(String name, String color) {
        logger.info("Request to getting faculty by name {} or color {}", name, color);
        return facultyRepository.findByNameIgnoreCaseOrColorIgnoreCase(name, color);
    }

    public Collection<Faculty> getStudents(Long id) {
        logger.info("Request to getting students by id {}", id);
        return facultyRepository.findFacultyByStudentId(id);
    }

    public Collection<Faculty> getFacultyByNameAndColor(String name, String color) {
        logger.info("Request to getting faculty by name {} and color {}", name, color);
        return facultyRepository.findFacultyByNameAndColorIgnoreCase(name, color);
    }

    public String findLongestName() {
        logger.info("Request to getting longest name of faculty");
        return facultyRepository.findAll()
                .stream()
                .max(Comparator.comparingLong(f ->f.getName().length()))
                .map(Faculty::getName)
                .orElseThrow();
    }
}
