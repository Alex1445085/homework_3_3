package hogwartsschoolhw33.service;

import hogwartsschoolhw33.model.Faculty;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class FacultyService {
    private final Map<Long, Faculty> facultyMap = new HashMap<>();
    Long id = 0L;

    public Faculty addFaculty(Faculty faculty) {
        faculty.setId(++id);
        facultyMap.put(faculty.getId(), faculty);
        return faculty;
    }

    public Faculty findFaculty(Long facultyId) {
        return facultyMap.get(facultyId);
    }

    public Faculty editFaculty(Long facultyId, Faculty faculty) {
        faculty.setId(facultyId);
        if (!facultyMap.containsKey(facultyId)) {
            return null;
        }
        facultyMap.put(facultyId, faculty);
        return faculty;
    }

    public void deleteFaculty(Long facultyId) {
        facultyMap.remove(facultyId);
    }

    public Collection<Faculty> facultiesByColor(String color) {
        Collection<Faculty> res = new HashSet<>();
        for (Faculty actual : facultyMap.values()) {
            if (color.equals(actual.getColor())) {
                res.add(actual);
            }
        }
        return Collections.unmodifiableCollection(res);
    }
}