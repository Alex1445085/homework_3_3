package hogwartsschoolhw33.service;

import hogwartsschoolhw33.Exception.FacultyNotFoundException;
import hogwartsschoolhw33.model.Faculty;
import hogwartsschoolhw33.model.Student;
import hogwartsschoolhw33.repository.FacultyRepository;
import hogwartsschoolhw33.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class FacultyService {
    private final StudentRepository studentRepository;
    private final FacultyRepository facultyRepository;
    public FacultyService(StudentRepository studentRepository, FacultyRepository facultyRepository) {
        this.studentRepository = studentRepository;
        this.facultyRepository = facultyRepository;
    }
    public Faculty addFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    public Faculty findFaculty(Long facultyId) {
        return facultyRepository.findById(facultyId).
                orElseThrow(() -> new FacultyNotFoundException("Not found"));
    }

    public Faculty editFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    public void deleteFaculty(Long facultyId) {
        facultyRepository.deleteById(facultyId);
    }

    public Collection<Faculty> facultiesByColor(String color) {
        Set<Faculty> res = facultyRepository.findByColorIgnoreCase(color);
        return Collections.unmodifiableCollection(res);
    }

    public Collection<Faculty> findByNameOrColor(String name, String color) {
        return facultyRepository.findByNameIgnoreCaseOrColorIgnoreCase(name, color);
    }

    public Collection<Student> studentsByIdOfFaculty(Long id) {
        return studentRepository.findAllByFaculty_id(id);
    }
}