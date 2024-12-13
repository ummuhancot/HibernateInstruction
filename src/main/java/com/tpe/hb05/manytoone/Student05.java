package com.tpe.hb05.manytoone;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "t_student03")
public class Student05 {//MANY

    @Id
    private Integer id;

    @Column(name = "student_name",nullable = false,unique = true,length = 50)//default : varchar(255)
    private String name;

    private int grade;

    private LocalDateTime creatOn;

    @ManyToOne//t_student05 ile university tablosu arasında M-1 ilişki kurar
              //t_student05 tablosuna FK ekleyerek ilişkiyi kurar: university_id
    private University university;//ONE

   // private String mesajgönder;//DENEME

    //const
    public Student05() {
    }

    public Student05(Integer id, String name, int grade) {
        this.id = id;
        this.name = name;
        this.grade = grade;
    }

    //getter-setter
/*
    //DENE-----PrePersist ekleyerek sout u setter e nasıl koycam scanner kullanmayı dene
    @PrePersist //kayıt etmeden önce konsola yazdıracak mı acaba
    public String getMesajgönder() {

        return mesajgönder;
    }

    public void setMesajgönder() {
        String başarılı= "Ögrenci kayıtt ediliyor";
        System.out.println(başarılı);
        this.mesajgönder = başarılı;
    }
    //BURAYI----
*/

    public University getUniversity() {
        return university;
    }

    public void setUniversity(University university) {
        this.university = university;
    }

    public LocalDateTime getCreatOn() {
        return creatOn;
    }

    @PrePersist//kalıcı hala getirmeden önce bu method cağrılacak demek
    // bu metod student objem veritabanına kayıt edilmeden hemen önce cağrılsın
    public void setCreatOn() {
        this.creatOn = LocalDateTime.now();
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
        return "Student05{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", grade=" + grade +
                '}';
    }
}