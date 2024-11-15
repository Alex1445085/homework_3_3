package hogwartsschoolhw33.controller;

import hogwartsschoolhw33.model.Faculty;
import hogwartsschoolhw33.service.FacultyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/faculty")
public class FacultyController {
    public final FacultyService servFaculty;

    public FacultyController(FacultyService servFaculty) {
        this.servFaculty = servFaculty;
    }

    @GetMapping("{id}")
    public ResponseEntity<Optional<Faculty>> findfaculty(@PathVariable Long id) {
        Optional faculty = servFaculty.findFaculty(id);
        if (faculty.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
//            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(faculty);
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
}
