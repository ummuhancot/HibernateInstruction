package com.tpe.hb07.bi_onetomany;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Book07 {//many:ilişkinin sahibi

    @Id
    private Integer book_id;
    private String name;

    ///burası eklendi getter ve setter eklendi ek olarak
    @ManyToOne //ilişkiyi kurar : FK ekler book tablosuna
    //@JoinColumn opsiyoneldir suanda
    private Student07 student; //bu kitap hangi öğrenciye ait


    //const
    public Book07() {
    }

    public Book07(Integer book_id, String name) {
        this.book_id = book_id;
        this.name = name;
    }

    //getter-setter

    public Integer getBook_id() {
        return book_id;
    }

    public void setBook_id(Integer book_id) {
        this.book_id = book_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Student07 getStudent() {
        return student;
    }

    public void setStudent(Student07 student) {
        this.student = student;
    }
//toString

    @Override
    public String toString() {
        return "Book07{" +
                "book_id=" + book_id +
                ", name='" + name + '\'' +
                '}';
    }
}