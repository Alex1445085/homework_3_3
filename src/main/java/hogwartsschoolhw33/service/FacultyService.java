package hogwartsschoolhw33.service;

import hogwartsschoolhw33.Exception.FacultyNotFoundExcepyion;
import hogwartsschoolhw33.model.Faculty;
import hogwartsschoolhw33.repository.FacultyRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class FacultyService {

    private final FacultyRepository facultyRepository;
    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }
    public Faculty addFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    public Faculty findFaculty(Long facultyId) {
        return facultyRepository.findById(facultyId).orElseThrow(() -> new FacultyNotFoundExcepyion());
    }

    public Faculty editFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    public void deleteFaculty(Long facultyId) {
        facultyRepository.deleteById(facultyId);
    }

    public Collection<Faculty> facultiesByColor(String color) {
        Set<Faculty> res = facultyRepository.findByColor(color);
        return Collections.unmodifiableCollection(res);
    }
}