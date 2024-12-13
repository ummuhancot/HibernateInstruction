package com.tpe.hb02.embeddable;

import javax.persistence.*;
/*
@Embeddable Anotasyonu
@Embeddable, gömülü bir bileşen sınıfını tanımlamak için kullanılır.
Bu sınıf bir entity değildir; yalnızca bir entity'nin bir parçası olarak kullanılır.
@Embeddable sınıfı bağımsız bir tablo oluşturmaz.
Veritabanında, bu sınıfın alanları ana entity'nin
alanlarıyla aynı tabloya eklenir.

@Embedded Anotasyonu
@Embedded, bir entity içinde @Embeddable sınıfın kullanılacağını belirtir.
Entity, @Embedded sınıfın özelliklerini kendi özellikleri gibi kabul eder
 ve veritabanı tablosunda bunları ayrı sütunlar olarak saklar.

Kullanım Senaryoları:
Bir entity'deki adres, iletişim bilgileri vb.
gibi bir grup alanı(field) düzenli bir şekilde modellemek için uygundur.
 */
@Entity
@Table(name = "t_Student02")
public class Student02 {

    @Id//id sütununa PK kısıtlamasının eklenmesini sağlar
    //@Column(name = "std_id")
    private Integer id;

    @Column(name = "student_name",nullable = false,unique = true,length = 50)//default : varchar(255)
    private String name;//not null
    private int grade;

    //---adres olarak alsam bu 4 ünü bunnun icin Address clasını actık
    /*
    private String street;//sokak demek
    private String city;
    private String country;
    private String zipcode;
    */

    @Embedded//gömülü demek:OPSİYONEL yazmasakda olur demek
    private Address address;

    //paramsiz const:hibernate fetch işlemlerinde default const kullanır.
    public Student02() {//bunu yazmazsak hata alırız
    }

    //paramli const
    public Student02(Integer id, String name, int grade) {
        this.id = id;
        this.name = name;
        this.grade = grade;
    }

    //getter-setter

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
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
        return "Student02{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", grade=" + grade +
                '}';
    }
}
