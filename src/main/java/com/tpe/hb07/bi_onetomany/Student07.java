package com.tpe.hb07.bi_onetomany;

import com.tpe.hb06.onetomany.Book;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "t_student07")
public class Student07 {//one

    @Id
    private Integer id;

    @Column(name = "student_name",nullable = false,unique = true,length = 50)//default : varchar(255)
    private String name;

    private int grade;

    /**
    orphanRemoval:sadece OneToMany anotasyonunda vardır.
    studentin kitap listesinden bir kitabı silersek veya null yaparsak
    Book tablosundan bu kitabı siler.
     */

    ///2.yol ) Cascade / orphanRemoval
    // parent tablodan(student) satır silmek istediğimizde önce ilişkili olduğu
    //satırları(bookList) diğer tablodan(book) siler, ardından parenttan silme işlemi yapar.
    //yani aşamalı(kademeli) silme işlemini otomatik yapar.
    ///cascade  kademeli olarak parint chilt ilişkisinde kaldırılmasını otomatik olarak sağlar
    //@OneToMany(mappedBy = "student",cascade = CascadeType.REMOVE)//ilişkiyi korar:JOIN table oluşturur --> diğer tarafta manytoone idi.gerek yok
    //diğer tarafta zaten kuruldu
    @OneToMany(mappedBy = "student",/*cascade = CascadeType.REMOVE*/orphanRemoval = true)//ilişki kurar:JOIN table oluşturur:gerek yok,diğer tarafta kuruluyor
    private List<Book07> bookList=new ArrayList<>();//bu öğrencinin kitapları nelerdir

    //const
    public Student07() {
    }

    public Student07(Integer id, String name, int grade) {
        this.id = id;
        this.name = name;
        this.grade = grade;
    }

    //getter-setter


    public List<Book07> getBookList() {
        return bookList;
    }

    public void setBookList(List<Book07> bookList) {
        this.bookList = bookList;
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

    //toString

    @Override
    public String toString() {
        return "Student07{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", grade=" + grade +
                '}';
    }
}