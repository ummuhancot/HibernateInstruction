package com.tpe.hb13.entity_life_cycle;

import javax.persistence.*;
/*

	Entity State :

		1) Transient : Objenin newlenmiş hali, DB ile ilişkisi yok.
		 Bu durumda, nesne henüz bir Hibernate session'ına kaydedilmemiştir ve veritabanında bir karşılığı
		yoktur. Geçici durumdaki bir nesne, session'ın kapatılmasıyla birlikte kaybolur.

		2) Persisted or Managed : DB de row a karşılık geldiği durum, save(),get() vs. yapıldığı
		zamana denk geliyor. Bu durumda, nesne bir Hibernate session'ına kaydedilmiştir ve
		veritabanında bir karşılığı vardır. Kalıcı durumdaki bir nesne, session kapatılsa
		bile veritabanında kalır ve daha sonra yeniden kullanılabilir.

		3) Detached :  Bu durumda, nesne bir Hibernate session'ından ayrılmıştır. Artık bu
		session tarafından yönetilmiyor, ancak veritabanında hala bir karşılığı var. Ayrılmış
		durumdaki bir nesne, başka bir session'a bağlanarak kullanılabilir.

		4) Removed : obje remove yapıldığı zamanki durum.
*/

@Entity
public class Student13 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private Integer grade;

    public Student13() {
    }

    public Student13(String name, Integer grade) {
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

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "Student13{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", grade=" + grade +
                '}';
    }


    //log kaydı almak icin kullanıcaz
    //prepersist kayıt etmeden önce cağırır
    @PrePersist
    public  void prePersist(){
        System.out.println("----------------Ögrenci kayıt ediyor-----------");
    }

    //problem kısmını bulma
    //uygulamada saat isteyebiliriz
    //gözlem yapmak icin
    //test bakım ve onarım icin kullanılır.
    ///tabloya ekledikten sonra cağırır
    @PostPersist
    public void postPersist(){
        System.out.println("---------------Ögrenci kaydedildi--------------");
    }

    //update etmeden önce cagrır değişiklikten
    @PreUpdate
    private void preUpdate(){
        System.out.println("------------------Öğrenci güncelleniyor...");
    }

    //update ettikten sonra cağırır değişiklikten
    @PostUpdate
    private void postUpdate(){
        System.out.println("------------------Öğrenci güncellendi...");
    }

    //silinmeden önce remove cağırır
    @PreRemove
    private void preRemove(){
        System.out.println("------------------Öğrenci siliniyor...");
    }

    //silindikten sonra postremove cağırır
    @PostRemove
    private void postRemove(){
        System.out.println("------------------Öğrenci silindi...");
    }

    //uygulamaya bir data cektiğimde PostLoad cağrılır.
    @PostLoad
    public void posLoad(){
        System.out.println("Öğrenci yüklendi...");
    }
}
