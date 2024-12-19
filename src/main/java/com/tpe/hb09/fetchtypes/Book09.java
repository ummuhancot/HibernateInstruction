package com.tpe.hb09.fetchtypes;

import javax.persistence.*;

@Entity
public class Book09 {
    @Id
    private Integer id;
    private String name;

    //cascade = CascadeType.ALL:bir kitabı DB ye kaydettiğimde bu kitabın
    // sahibi olan öğrenciyi de otomatik olarak kaydeder

    //lazy tembel ol diyoruz db giderken sadece kitapları al diyoruz
    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)//fk ekler //default fetchtype:eager yani heycanlı demek
    private Student09 student;

    public Book09() {
    }

    public Book09(Integer id, String name) {
        this.id = id;
        this.name = name;
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

    public Student09 getStudent() {
        return student;
    }

    public void setStudent(Student09 student) {
        this.student = student;
    }

    @Override
    public String toString() {
        return "Student09{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
