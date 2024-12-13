package com.tpe.hb04.bi_onetoone;

import com.tpe.hb03.uni_onetoone.Student03;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Diary04 {//ONE

    @Id
    private Integer id;
    private String name;

    @OneToOne //birebir ilişkisi kurdu
    //diary ile t_student03 arasında 1-1 ilişki kurulmasını sağlar
    //bunun için diary e FK ekler: default ismi : student_id
    @JoinColumn(name = "std_id",unique = true)//OPSİYONEL(isim değiştirme, constraint(kısıtlama) ekleme vs is yapılır) :kullanmazsak sıkıntı yok
    private Student04 student;//ONE--günlük hangi öğrenciye ait onu gösteriyor 1 tane ögrenciye ait olabilir

    //getter - setter


    public Student04 getStudent() {
        return student;
    }

    public void setStudent(Student04 student) {
        this.student = student;
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

    //toString

    @Override
    public String toString() {
        return "Diary{" +
                "id=" + id +
                ", name='" + name + '\'' +
                //"student : "+student+ --> burda hata alıyoruz
                '}';
    }
}
