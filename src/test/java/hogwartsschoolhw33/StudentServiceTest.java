package hogwartsschoolhw33;

import hogwartsschoolhw33.model.Student;
import hogwartsschoolhw33.repository.StudentRepository;
import hogwartsschoolhw33.service.StudentService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class StudentServiceTest {
    @Mock
    private StudentService studentService;
    @InjectMocks
    private StudentRepository studentRepository = new StudentRepository() {
        @Override
        public Set<Student> findByAge(int age) {return Set.of();}
        @Override
        public void flush() {}
        @Override
        public <S extends Student> S saveAndFlush(S entity) {return null;}
        @Override
        public <S extends Student> List<S> saveAllAndFlush(Iterable<S> entities) {return List.of();}
        @Override
        public void deleteAllInBatch(Iterable<Student> entities) {}
        @Override
        public void deleteAllByIdInBatch(Iterable<Long> longs) {}
        @Override
        public void deleteAllInBatch() {}
        @Override
        public Student getOne(Long aLong) {return null;}
        @Override
        public Student getById(Long aLong) {return null;}
        @Override
        public Student getReferenceById(Long aLong) {return null;}
        @Override
        public <S extends Student> List<S> findAll(Example<S> example) {return List.of();}
        @Override
        public <S extends Student> List<S> findAll(Example<S> example, Sort sort) {return List.of();}
        @Override
        public <S extends Student> List<S> saveAll(Iterable<S> entities) {return List.of();}
        @Override
        public List<Student> findAll() {return List.of();}
        @Override
        public List<Student> findAllById(Iterable<Long> longs) {return List.of();}
        @Override
        public <S extends Student> S save(S entity) {return null;}
        @Override
        public Optional<Student> findById(Long aLong) {return Optional.empty();}
        @Override
        public boolean existsById(Long aLong) {return false;}
        @Override
        public long count() {return 0;}
        @Override
        public void deleteById(Long aLong) {}
        @Override
        public void delete(Student entity) {}
        @Override
        public void deleteAllById(Iterable<? extends Long> longs) {}
        @Override
        public void deleteAll(Iterable<? extends Student> entities) {}
        @Override
        public void deleteAll() {}
        @Override
        public List<Student> findAll(Sort sort) {return List.of();}
        @Override
        public Page<Student> findAll(Pageable pageable) {return null;}
        @Override
        public <S extends Student> Optional<S> findOne(Example<S> example) {return Optional.empty();}
        @Override
        public <S extends Student> Page<S> findAll(Example<S> example, Pageable pageable) {return null;}
        @Override
        public <S extends Student> long count(Example<S> example) {return 0;}
        @Override
        public <S extends Student> boolean exists(Example<S> example) {return false;}
        @Override
        public <S extends Student, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {return null;}
    };

    @Test
    public void findStudent() {
        Optional<Student> expected = studentRepository.findById(1L);

        when(studentService.findStudent(1L)).thenReturn(expected);

        Optional<Student> actual = studentService.findStudent(1L);

        assertEquals(actual, expected);
        verify(studentService, times(1)).findStudent(1L);
    }
}