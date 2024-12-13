package com.tpe.hb02.embeddable;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;


public class RunnerSave02 {
    public static void main(String[] args) {

        Student02 student01 = new Student02(1001,"Ali Can",99);
        Student02 student02 = new Student02(1002,"Ali Han",99);
        Student02 student03 = new Student02(1003,"Veli Can",99);

        Address address1=new Address("Orange street","London","UK","1234");
        Address address2=new Address("Apple Street","NewYork","USA","9876");

        student01.setAddress(address1);
        student02.setAddress(address2);
        student03.setAddress(address1);

        Configuration config=new Configuration().configure().addAnnotatedClass(Student02.class);
        SessionFactory sf=config.buildSessionFactory();
        Session session =sf.openSession();

        Transaction transaction = session.beginTransaction();//değişiklik yapıcam icin başlattım

        session.save(student01);
        session.save(student02);
        session.save(student03);

        transaction.commit();
        session.close();
        sf.close();

    }
}
