package com.tpe.hb06.onetomany;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Arrays;
import java.util.List;

public class RunnerFetch06 {

    public static void main(String[] args) {


            Configuration  config=new Configuration().configure().
                    addAnnotatedClass(Student06.class).addAnnotatedClass(Book.class);
            SessionFactory sf=config.buildSessionFactory();
            Session session =sf.openSession();

            //id:101 olan kitabı getirelim
            Book book = session.get(Book.class,101);
            System.out.println("--------GET-----------");
            System.out.println(book);

            //Bu kitap kime ait? book.getStudent() metodu yok doğrudan kitaptan öğrenciye ulaşamıyoruz ama
            //SQL/HQL ile ulaşabiliriz

            ///SQL
            String sql = "SELECT s.id,s.student_name,s.grade FROM t_student06 s INNER JOIN book b ON s.id=b.student_id "+
                    "WHERE b.book_id=101";
            Object[] studentInfos = (Object[]) session.createSQLQuery(sql).uniqueResult();
            System.out.println("--------SQL----------");
            //1.yol
            System.out.println(Arrays.toString(studentInfos));
            //2.yol
            for (Object a:studentInfos){
                    System.out.println(a);
            }

            ///HQL:Javaca
            String hql ="SELECT s FROM Student06 s INNER JOIN s.bookList b WHERE b.book_id=101 ";
            Student06 student = session.createQuery(hql,Student06.class).uniqueResult();
            System.out.println("-----------HQL-----------");
            System.out.println(student);


            System.out.println("--------------------------------------------");

            //id: 1001 olan ögrenciyi getirelim
            Student06 student2 = session.get(Student06.class,1001);
            System.out.println("-------GET-------");
            System.out.println(student2);
            //Bu ögrencinin kitaplarını görmek istersek
            System.out.println(student2.getBookList());

            //book -> student icin SQL/HQL
            //student -> books sadece java kodları yeterli

            //name:'Jack' olan öğrencinin kitaplarını getirelim
            //hem student name hem book bilgilerine ulaşabilmem lazım
            String sql2="SELECT b.book_id, b.name FROM book b INNER JOIN t_student06 s " +
                    "ON b.student_id=s.id WHERE s.student_name='Jack'";

            String hql2="SELECT b.book_id, b.name FROM Student06 s INNER JOIN s.bookList b WHERE s.name='Jack' ";

            List<Object[]> books =session.createQuery(hql2).getResultList();
            for (Object[] o:books){
                    System.out.println(Arrays.toString(o));
            }

            session.close();
            sf.close();

    }
}
