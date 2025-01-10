package hogwartsschoolhw33.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Student not found")
public class StudentNotFoundException extends RuntimeException {
    public StudentNotFoundException(String note) {
        super("BadRequestException = " + note);
    }
}
