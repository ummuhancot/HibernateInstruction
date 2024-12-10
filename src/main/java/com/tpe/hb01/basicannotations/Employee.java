package com.tpe.hb01.basicannotations;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class Employee {

    @Id
    private Long id;

    private String name;

    private Double salary;//50$

    @Transient //DB de bu fileda karşılık bir sutun oluşmasını engeller
    // uygulamada kullanıcam ama tabloya kayıt etmeyeceğim esgec demek
    private Double bonus;//50
    //bonusun tabloda yer almasına gerek yok


    //bu yöntem cok fazla tercih edilmez memory cok harcadığı icin
    //@Lob//Large Object: bu sütunda büyük boyutlu datalar saklanır:resim,video,ses...
    //private byte[] image; //long da tutulur sql de //foto


    //getter - setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public Double getBonus() {
        return bonus;
    }

    public void setBonus(Double bonus) {
        this.bonus = bonus;
    }
}
