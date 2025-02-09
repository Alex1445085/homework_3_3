package hogwartsschoolhw33.controller;

import hogwartsschoolhw33.model.Faculty;
import hogwartsschoolhw33.model.Student;
import hogwartsschoolhw33.service.FacultyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/faculty")
public class FacultyController {
    public final FacultyService servFaculty;
    public FacultyController(FacultyService servFaculty) {
        this.servFaculty = servFaculty;
    }

    @GetMapping("{id}")
    public Faculty findfaculty(@PathVariable Long id) {
        return servFaculty.findFaculty(id);
    }

    @PostMapping
    public Faculty addFaculty(@RequestBody Faculty faculty) {
        return servFaculty.addFaculty(faculty);
    }

    @PutMapping
    public ResponseEntity<Faculty> editFaculty(@RequestBody Faculty faculty) {
        Faculty foundFac = servFaculty.editFaculty(faculty);
        if (foundFac == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(foundFac);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteFaculty(@PathVariable Long id) {
        servFaculty.deleteFaculty(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<Collection<Faculty>> facultyByColor(@RequestParam(required = false) String color) {
        if (color == null || color.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(servFaculty.facultiesByColor(color));
    }
    @GetMapping("/findByNameOrColor/{name}/{color}")
    public Collection<Faculty> findByNameOrColorIgnoreCase(@RequestParam(required = false) String name,
                                                           @RequestParam(required = false) String color) {
        return servFaculty.findByNameOrColor(name, color);
    }
    @GetMapping("getStudentsByIdOfFaculty/{id}")
    public Collection<Student> studentsByIdOfFaculty(@PathVariable Long id) {//@RequestParam
        return servFaculty.studentsByIdOfFaculty(id);
    }
}
