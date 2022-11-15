package ru.hogwarts.school.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import java.util.Objects;

@Entity
public class Student {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private Long age;
    @ManyToOne
    @JoinColumn(name = "faculty_id")
    private Faculty faculty;
    public Student (){

    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                "Fid=" + faculty.getId() +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return id == student.id
                && age == student.age
                && faculty.getId() == student.faculty.getId()
                && Objects.equals(name, student.name)
                ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, age, faculty.getId());
    }

    public Long getId() {
        return id;
    }

    public Long getFacultyId() {
        return faculty.getId();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getAge() {
        return age;
    }

    public void setAge(Long age) {
        this.age = age;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFaculty(Faculty f) {
        faculty = f;
    }

}
