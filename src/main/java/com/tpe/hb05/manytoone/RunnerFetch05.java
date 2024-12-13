package com.tpe.hb05.manytoone;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Arrays;
import java.util.List;

public class RunnerFetch05 {

    public static void main(String[] args) {

        Configuration config=new Configuration().configure().
                addAnnotatedClass(Student05.class).addAnnotatedClass(University.class);
        SessionFactory sf=config.buildSessionFactory();
        Session session =sf.openSession();

        //id:1002 olan öğrenciyi getirelim
        Student05 student = session.get(Student05.class,1002);
        System.out.println(student);


        //id:11 olan üniversitenin tüm öğrencilerini listeleyelim
        String hql ="SELECT s FROM Student05 s WHERE s.university=11";
        List<Student05>resultList = session.createQuery(hql,Student05.class).getResultList();
        for (Student05 s:resultList){
                   System.out.println(s);
        }


       //university.getStudents()--tek yönlü ilişki : ancak sorgu yazarak yapabiliyoruz

       //öğrencilerin isimlerini, notlarını ve
       // üniversitelerinin isimlerini yazdıralım


        String hql2 = "SELECT s.name,s.grade,s.university.name FROM Student05 s";
        List<Object[]> resultList1 = session.createQuery(hql2).getResultList();
        for (Object[] o: resultList1){
            System.out.println(Arrays.toString(o));
        }





        session.close();
        sf.close();
    }
}
