package ru.hogwarts.school.model;

import javax.persistence.*;
import java.util.Objects;
import java.util.Optional;

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

    @OneToOne
    @JoinColumn(name = "avatar_id")
    private Avatar avatar;
    public Student (){

    }

    public Student(String name, Long age) { // 21 11 2022 добавила для теста
        this(null, name, age, null, null);
    }

    public Student(Long id, String name, Long age, Faculty faculty, Avatar avatar) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.faculty = faculty;
        this.avatar = avatar;
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
        return id.equals(student.id)
                && age.equals(student.age)
                && faculty.getId().equals(student.faculty.getId())
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

//    public Long getFacultyId() {
//        return faculty.getId();
//    }

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

    public Optional<Faculty> getFaculty() {
        return Optional.ofNullable(faculty);
    }
    public Optional<Avatar> getAvatar() {
        return Optional.ofNullable(avatar);
    }
}
