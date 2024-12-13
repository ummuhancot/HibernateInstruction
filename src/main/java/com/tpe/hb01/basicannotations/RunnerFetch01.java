package com.tpe.hb01.basicannotations;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

//uygulamaya veritabanından data cekme
public class RunnerFetch01 {
    public static void main(String[] args) {

/*
DB den data çekmek için:fetch işlemlerinde transactiona gerek yoktur.
          Task:id=1001/1002/1003 olan öğrenciyi tüm fieldlarıyla getirmek(fetch) istiyoruz.
*/

        Configuration configuration = new Configuration().configure().addAnnotatedClass(Student.class);
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();

        //SELECT * (FROM TABLO İSMİ) yerine
        /*
        1) sessionın metodu:get():en pratik ama kullanım alanı kısıtlı
           2) SQL : DB ce(database ce kullanım)
           3) HQL(Hibernate Query Language): Javaca

           HQL (Hibernate Query Language), Hibernate ORM tarafından sağlanan,
           nesne odaklı bir sorgulama dilidir. HQL, SQL'e benzer şekilde çalışır
           ancak doğrudan veritabanı tablolarıyla değil,
           Java sınıfları (entity'ler) ve onların özellikleriyle çalışır
         */


        //get
        Student student = session.get(Student.class,1001);
        System.out.println("-----------------------get methodu-----------------");
        System.out.println(student);


        //!!! Javaca konuşurken bu sınıfı belirtirken Student,--hql-HQL
        //SQLce konuşurken t_student kullanırız.--sql-SQL

        //SQL sorgusu yazarakta yaparız
        String sql = "SELECT * FROM t_student WHERE id=1002";
        Object[] student2 = (Object[]) session.createSQLQuery(sql).uniqueResult();//kestink yaptık //eger sorgunun sonucunda tek satır gelceni biliyorsanız uniqueResult kullanılır
        //uniqueResult():sorgunun tek satır getireceğini biliyorsak kullanılır
        //geriye bir satırdan birden fazla data geldiği icin data tipleri
        //farklı old icin Object[] icine alınır.
        System.out.println("----------------------SQL-----------------");

        System.out.println(Arrays.toString(student2));//[1002, 98, Harry Potter]
        //array icinde for la gezerek alabilirdik tek tek diğer secenek


        //HQL:javaca konuşucaz. sorguyu kendin yazabilirsin. sorgunu yazarken javaca konusabilirsin
        String hql = "FROM Student WHERE id=1003";//clastan direk cekmeti
        Student student3 =session.createQuery(hql,Student.class).uniqueResult();
        //uniqueResult():sorgunun tek satır getireceğini biliyorsak kullanılır
        System.out.println("----------------------HQL-----------------");
        System.out.println(student3);//Student{id=1003, name='Sherlock Holmes', grade=0}

        //tüm kayıtları çekelim
        //HQL-hql
        List<Student> studentList = session.createQuery("FROM Student",Student.class).getResultList();
        //getResultList birden fazla kayıt geleceği zaman kullanılır
        System.out.println("-------TÜM ÖGRENCİLER--------");
        for (Student s:studentList){
            System.out.println(s);
        }


        //SQL-sql ile tüm kayıtları cekme:exercise
        System.out.println("----------------SQL-----------------------");
        String sql2="SELECT * FROM t_student";
        List<Object[]> result=session.createSQLQuery(sql2).getResultList();
        for (Object[] objects:result){
            System.out.println(Arrays.toString(objects));
        }

        String sqlall = "SELECT * FROM Student";
        List<Student> students = session.createSQLQuery(sqlall).addEntity(Student.class).getResultList();
        for (Student s : students) {
            System.out.println(s);
        }

            // HQL ile grade degeri 98 olan ogrencilerin id ve name bilgilerini getirelim
            String hql2 = "SELECT s.id,s.name FROM Student s WHERE s.grade=98"; //sql deki isim değiştirdik as deki s yaptık student yerine s oldu
            //birden fazla class ile calışınca as ile calışmak garanti veri döndürür
            List<Object[]> resultList = session.createQuery(hql2).getResultList();
            //geriye dönen datalara göre List<Object[]> kullandık
            for (Object[] oa : resultList) {
                System.out.println("-----------HQL grade=98-----------");
                System.out.println(Arrays.toString(oa));
            }

            //practice:HQL ile
            //1-ismi Harry Potter olan öğrencileri getirelim
            //2-tüm öğrencilerin sadece isimlerini getirelim
            //SQL ile
            //1-tüm öğrencilerin sadece isimlerini getirelim

            //practice:HQL ile
            //1-ismi Harry Potter olan öğrencileri getirelim
            String hql3 = "SELECT s FROM Student s WHERE s.name='Harry Potter'";
            List<Student> resultList2 = session.createQuery(hql3, Student.class).getResultList();

            for (Student s0 : resultList2) {
                System.out.println(s0);
            }

            //2-tüm öğrencilerin sadece isimlerini getirelim
            String hql4 = "SELECT name FROM Student";
            List<String> resultList3 = session.createQuery(hql4, String.class).getResultList();
            System.out.println("----------------------HQL-----------------");
            for (String s2 : resultList3) {
                System.out.println(s2);
            }
            //SQL ile
            //1-tüm öğrencilerin sadece isimlerini getirelim
            String sql3 = "SELECT student_name FROM t_student";
            List<String> isimler = session.createSQLQuery(sql3).getResultList();
            System.out.println("----------------SQL-----------------------");
            for (String s3 : isimler) {
                System.out.println(s3);
            }

        //1-güvenlik 2-memoriy korunması icin kapattık
        session.close();
        sessionFactory.close();


    }
}
