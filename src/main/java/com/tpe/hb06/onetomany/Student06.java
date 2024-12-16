package com.tpe.hb06.onetomany;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "t_student06")
public class Student06 {//One

    @Id
    private Integer id;

    @Column(name = "student_name",nullable = false,unique = true,length = 50)//default : varchar(255)
    private String name;

    private int grade;

    ///private Student06 student; yerine list olarak tuttuk
    ///tablolar arasında ilişkinin kurulmasını saglar onetomany gibi ilişkiler
    ///burada 3 . tablo ilişkisini kurdukd
    @OneToMany//tablolar arasında ilişkinin kurulmasıı sağlar:ilişki tablosu oluşturur.
    ///3.tablonun oluşmasını engellemek icin JoinColumn anatosyonu birlikte kullanılınca 3 tabloyu iptal edip
    ///book tablosuna fk ekliyor normalde obsiyonel joincolumn ama bu şekilde kullanımda zorunlu hale gelir.

    @JoinColumn(name = "student_id")//kullanmazsak ne olura bakmak icin yoruma aldık
    //JOIN TABLE iptal, book tablosuna FK ekler:ZORUNLU
    //join column kullanılmazsa JOIN TABLE oluşturulur.
    private List<Book> bookList = new ArrayList<>();//bu öğrencinin kitaplmarı nelerdir.


    //const
    public Student06() {
    }

    public Student06(Integer id, String name, int grade) {
        this.id = id;
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

    public List<Book> getBookList() {
        return bookList;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }

    @Override
    public String toString() {
        return "Student06{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", grade=" + grade +
                '}';
    }
}
