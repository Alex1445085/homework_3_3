package hogwartsschoolhw33.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private int age;


    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "faculty_id")
    private Faculty faculty;

    public Student() {}

    public Faculty getFaculty() { return faculty; }
    public Long getId() { return id; }
    public String getName() { return name; }
    public int getAge() { return age; }
    public void setId(Long id) { this.id = id; }
    public void setName(String name) { this.name = name;}
    public void setAge(int age) { this.age = age; }
    public void setFaculty(Faculty faculty) { this.faculty = faculty; }

    @Override
    public String toString() { return "Student : id = " + id + ", name = " + name + ", age = " + age; }

    public boolean equalName(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(this.name, student.name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return this.age == student.age &&
                Objects.equals(this.id, student.id) &&
                Objects.equals(this.name, student.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, age);
    }
}