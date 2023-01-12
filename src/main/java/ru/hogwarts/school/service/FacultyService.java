package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;

import java.util.Collection;
import java.util.HashMap;

@Service
public class FacultyService {
    private final HashMap<Long, Faculty> faculties = new HashMap<>();
    private long countId = 0;

    public Faculty creatFaculty(Faculty faculty) {
        faculty.setId(++countId);
        faculties.put(countId, faculty);
        return faculty;
    }

    public Faculty findFaculty(long countId) {
        return faculties.get(countId);
    }

    public Faculty editFaculty(Faculty faculty) {
        if (faculties.containsKey(faculty.getId())){
            faculties.put(faculty.getId(), faculty);
            return faculty;
        }
        return null;
    }

    public Faculty deleteFaculty(long countId) {
        return faculties.remove(countId);
    }

    public Collection<Faculty> getAll() {
        return faculties.values();
    }
}
