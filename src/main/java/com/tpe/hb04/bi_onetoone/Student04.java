package com.tpe.hb04.bi_onetoone;

import com.tpe.hb03.uni_onetoone.Diary;

import javax.persistence.*;

@Entity
@Table(name = "t_student04")
public class Student04 {//one

    @Id
    private Integer id;

    @Column(name = "student_name",nullable = false,unique = true,length = 50)//default : varchar(255)
    private String name;

    private int grade;
    @OneToOne(mappedBy = "student")//bu ilişki zaten diary04 tarafında kuruldu
    //diary04 classındaki studentın değerine göre eşleştirme yapar
    //student tablosuna fazladan FK eklemez!!!
    //mappedBy kullanılmazsa her iki tabloda FK eklenir.
    private Diary04 diary;//one

    //const
    public Student04() {
    }

    public Student04(Integer id, String name, int grade) {
        this.id = id;
        this.name = name;
        this.grade = grade;
    }

    //getter-setter

    public Diary04 getDiary() {
        return diary;
    }

    public void setDiary(Diary04 diary) {
        this.diary = diary;
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
        return "Student04{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", grade=" + grade +
                //"diary : "+diary+ --> burda hata alırız
                '}';
    }
}