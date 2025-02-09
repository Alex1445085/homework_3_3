package hogwartsschoolhw33.service;

import hogwartsschoolhw33.Exception.FacultyNotFoundException;
import hogwartsschoolhw33.model.Faculty;
import hogwartsschoolhw33.model.Student;
import hogwartsschoolhw33.repository.FacultyRepository;
import hogwartsschoolhw33.repository.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private final Logger logger = LoggerFactory.getLogger(FacultyService.class);

    public Faculty addFaculty(Faculty faculty) {
        logger.debug("request for add faculty {}", faculty);
        return facultyRepository.save(faculty);
    }

    public Faculty findFaculty(Long facultyId) {
        logger.debug("request for find faculty by id = {}", facultyId);
        return facultyRepository.findById(facultyId).
                orElseThrow(() -> new FacultyNotFoundException("Not found"));
    }

    public Faculty editFaculty(Faculty faculty) {
        logger.debug("request for edit {}", faculty);
        return facultyRepository.save(faculty);
    }

    public void deleteFaculty(Long facultyId) {
        logger.debug("request for delete facultyId {}", facultyId);
        facultyRepository.deleteById(facultyId);
    }

    public Collection<Faculty> facultiesByColor(String color) {
        Set<Faculty> res = facultyRepository.findByColorIgnoreCase(color);
        logger.debug("finding faculties by color {} are : {}", color, res);
        return Collections.unmodifiableCollection(res);
    }

    public Collection<Faculty> findByNameOrColor(String name, String color) {//
        Collection<Faculty> res = facultyRepository.findByNameIgnoreCaseOrColorIgnoreCase(name, color);
        logger.debug("faculty finding by name {} or color {} is : {}", name, color, res);
        return res;
    }

    public Collection<Student> studentsByIdOfFaculty(Long id) {
         Collection<Student> res = studentRepository.findAllByFaculty_id(id);
         logger.debug("list of students of faculty with id {} : {}", id, res);
         return res;
    }
}