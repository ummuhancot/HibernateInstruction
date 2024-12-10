package com.tpe.hb01.basicannotations;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class RunnerSave01 {
    public static void main(String[] args) {

        Student student1= new Student();
        student1.setId(1001);
        student1.setName("Jack Sparrow");
        student1.setGrade(99);

        Student student2 = new Student();
        student2.setId(1002);
        student2.setName("Harry Potter");
        student2.setGrade(98);

        Student student3 = new Student();
        student3.setId(1003);
        student3.setName("Sherlock Holmes");
        //student3.setGrade(97);

        //configure(database ayarları) metoduna parametre girilmezse defaultta "hibernate.cfg.xml"
        // dosyasına göre konfig. yapar.
        Configuration config = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class).addAnnotatedClass(Employee.class);//işaretlemeler yaptığım class ı yazdık

        SessionFactory sessionFactory= config.buildSessionFactory();

        Session session = sessionFactory.openSession();


        //hibernote de defauld olarak auto-commit : false
        //db de işlemlerin kalıcı olması icin transaction başlatılıp onaylanması gerekir

        Transaction transaction = session.beginTransaction();
        //transaction(database de atomik işlem birimi) başlatıldı.

        //student1 i tabloya ekleyelim

        session.save(student1); //"INSERT İNTO t_student VALUES(......)"
        session.save(student2);
        session.save(student3);
        //database ye tabloyu kayıt eder

        transaction.commit();//onayla methodu //transactionı onarylar ve sonlandırır
        session.close();//database yi kapatırız.
        sessionFactory.close();//database de işimiz bittiğinde kapatırız uygulama durduğunda




    }
}