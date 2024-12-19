package com.tpe.hb09.fetchtypes;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class RunnerFetch09 {

    public static void main(String[] args) {

        Configuration config=new Configuration().configure().
                addAnnotatedClass(Student09.class).addAnnotatedClass(Book09.class);
        SessionFactory sf=config.buildSessionFactory();
        Session session =sf.openSession();

        //student getirelim
        System.out.println("-----STUDENT-----");
        System.out.println("-----fetch = FetchType.EAGER------");
        Student09 student = session.get(Student09.class,1001);//coğu istediğimiz icin layz olur
        System.out.println(student.getBookList());//biz isteyince getirir

        System.out.println("-----BOOK------");
        System.out.println("-----fetch = FetchType.EAGER------");
        Book09 book = session.get(Book09.class,33);
        //System.out.println(book.getStudent());


        session.close();
        sf.close();
        System.out.println("--------CascadeType.ALL--------");
        //System.out.println(student.getBookList());//hata aldık session kapattığımız icin
        //System.out.println(book.getStudent());
        // /konsola yazdırmasak da kitabın tüm bilgileri var
        System.out.println("-----fetch = FetchType.EAGER------");
        System.out.println(student.getBookList());
        //System.out.println(book.getStudent());
        System.out.println("-------fetch = FetchType.LAZY------");
        System.out.println(student.getBookList());
        //System.out.println(book.getStudent());

    }
}
