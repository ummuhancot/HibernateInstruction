package com.tpe.hb07.bi_onetomany;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class RunnerFetch07 {
    public static void main(String[] args) {
        Configuration config=new Configuration().configure().
                addAnnotatedClass(Student07.class).addAnnotatedClass(Book07.class);
        SessionFactory sf=config.buildSessionFactory();
        Session session =sf.openSession();

        /*
        //id:101 olan kitabı getirelim
        Book07 book = session.get(Book07.class,101);
        System.out.println("--------GET----------");
        System.out.println(book);
        //bu kitabın sahibi olan öğrenciyi görelim
        System.out.println("-----------KİTABIN SAHİBİ----------");
        System.out.println(book.getStudent());

        //id:1002 olan öğrencinin kitaplarını getirelim
        //HQL // SQL kullanmadan
        Student07 student = session.get(Student07.class,1002);
        System.out.println("-------GET-------");
        System.out.println(student);
        System.out.println("--------ÖGRENCİNİN KİTAPLARI--------");
        System.out.println(student.getBookList());
        */

        //database de değişikliklerin kalıcı olması icin transaction greklidir
        Transaction transaction = session.beginTransaction();

        //book07 tablosunda tüm kayıtları silelim
        //HQL ile kayıtları silme
        String hql = "DELETE FROM Book07";
        int deletedBooks = session.createQuery(hql).executeUpdate();
        System.out.println("---SİLİNEN KİTAP SAYISI---");
        System.out.println(deletedBooks);


        //student07 tablosunda tüm kayıtları silelim
        String hql2 = "DELETE FROM Student07";
        int deletedStudent = session.createQuery(hql2).executeUpdate();
        System.out.println("---SİLİNEN ÖĞRENCİ SAYISI---");
        System.out.println(deletedStudent);

        //İsmi Sefiller olan kitabı HQL ile silelim.
        String hql3 = "DELETE FROM Book07 b WHERE b.name='Sefiller'";
        int deleted = session.createQuery(hql3).executeUpdate();
        System.out.println("Silinen kayıt sayısı : "+deleted);







        transaction.commit();
        session.close();
        sf.close();
    }
}
