package com.tpe.hb04.bi_onetoone;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Arrays;
import java.util.List;

public class RunnerFetch {
    public static void main(String[] args) {


        Configuration config=new Configuration().configure().
                addAnnotatedClass(Student04.class).addAnnotatedClass(Diary04.class);
        SessionFactory sf=config.buildSessionFactory();
        Session session =sf.openSession();

        //id:1001 olan öğrenciyi getirelim
        Student04 student04 = session.get(Student04.class,1001);
        System.out.println(student04);

        //id:11 olan günlügü getirelim
        Diary04 diary04 = session.get(Diary04.class,11);
        System.out.println(diary04);

        //günlügün sahibi kimdir?
        System.out.println(diary04.getName());

        //Ögrencinin günlügü hangisidir
        System.out.println(student04.getDiary());
        //sorgu yazmadan öğrenciden günlüge,günlüktende öğrenciye ulaştık

        //bi_directional : student <--> diary

        // !!! Task 1: Günlüğü olan öğrenci ve sahibi olan günlüklerin
        //student name ve diary name fieldlarını getirelim.

        //toStrink olarak Diary ve Student cağırmıştık onun yerine yazarız
        System.out.println("---------------------------------");
        String hql = " SELECT s.name,d.name FROM Student04 s INNER JOIN Diary04 d ON s.id=d.student";//de yazabiliriz --> d.student.id//SQL de ise--> s.id=d.std_id
        List<Object[]> resultList = session.createQuery(hql).getResultList();
        for (Object[] oa: resultList){
            System.out.println(Arrays.toString(oa));
        }

        // !!! Task 2: Tüm öğrencilerin
        //student name ve varsa diary name fieldlarını getirelim.
        System.out.println("--------------------------------");
        String hql1 = " SELECT s.name,d.name FROM Student04 s LEFT JOIN Diary04 d ON s.id=d.student";
        String hql2="SELECT s.name,d.name FROM  Diary04 d RIGHT JOIN Student04 s ON s.id=d.student";
        List<Object[]> resultList1 = session.createQuery(hql1).getResultList();
        for (Object[] oa: resultList1){
            System.out.println(Arrays.toString(oa));
        }


        // Task 3 : Butun gunlukler ve varsa gunlugun sahibi olan ogrenciler gelsin
        System.out.println("--------------------------------");
        String hql3="SELECT s.name,d.name FROM  Diary04 d RIGHT JOIN Student04 s ON s.id=d.student";
        List<Object[]> resultList2 = session.createQuery(hql3).getResultList();
        for (Object[] oa: resultList2){
            System.out.println(Arrays.toString(oa));
        }


        // Task 4 : tüm günlük ve öğrencilerin isimlerini getirelim.
        System.out.println("--------------------------------");
        String hql4="SELECT s.name,d.name FROM  Diary04 d FULL JOIN Student04 s ON s.id=d.student";
        List<Object[]> resultList3 = session.createQuery(hql4).getResultList();
        for (Object[] oa: resultList3){
            System.out.println(Arrays.toString(oa));
        }








    }
}
