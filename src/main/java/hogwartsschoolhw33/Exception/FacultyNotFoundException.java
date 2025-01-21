package hogwartsschoolhw33.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Faculty not found")
public class FacultyNotFoundException extends RuntimeException {
    public FacultyNotFoundException(String note) {
        super("BadRequestException = " + note);
    }
}