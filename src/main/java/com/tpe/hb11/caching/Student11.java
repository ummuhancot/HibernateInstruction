package com.tpe.hb11.caching;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

@Entity
///2.seviye önbellekten tutulmasına izin veriyorum bu kısmı belirtmeniz gerekir.
@Cacheable//2.seviye önbellekte tutulmasına izin veriyorum
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE )

public class Student11 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "student_name",nullable = false)
    private String name;

    private int grade;

    public Student11() {
    }

    public Student11(String name, int grade) {
        this.name = name;
        this.grade = grade;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "Student11{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", grade=" + grade +
                '}';
    }
}