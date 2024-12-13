package com.tpe.hb03.uni_onetoone;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class RunnerFetch03 {
    public static void main(String[] args) {


        Configuration config=new Configuration().configure().
                addAnnotatedClass(Student03.class).addAnnotatedClass(Diary.class);
        SessionFactory sf=config.buildSessionFactory();
        Session session =sf.openSession();

        //id:1001 olan öğrenciyi getirelim
        Student03 student=session.get(Student03.class,1001);
        System.out.println("****************STUDENT**************");
        System.out.println(student);

        //id:11 olan günlügü getirelim
        Diary diary = session.get(Diary.class,11);
        System.out.println("*****************DİARY*************");
        System.out.println(diary);

        //günlük kime ati
        System.out.println("******************************");
        System.out.println(diary.getStudent());//Tekrar DB ye gitmeye gerek yok zaten yanında ögreciyi getirmiş

        //id:1002 olan öğrencinin günlügü hangisidir
        Student03 student02 = session.get(Student03.class,1002);
        //student02.getDiary(); //Java kodları ile ulaşamıyoruz. ögrenciden günlüge ulaşamıyorum

        //fakat DB den ulaşabiliriz
        String diaryname= (String) session.createSQLQuery("SELECT name FROM diary WHERE std_id=1002").uniqueResult();
        System.out.println(diaryname);
        //PROBLEM: sorgu yazmadan diaryden studenta , studentdan diarye ulaşmak istersem?
        //uni_directional(tek yönlü) : Diary  -> Student
        //bi_directional (çift yönlü) : Diary <-> Student(veritabanında birşey değişmez)

        session.close();
        sf.close();


    }
}
