package com.tpe.hb12.get_load;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class RunnerSave12 {

    public static void main(String[] args) {


        Student12 student1=new Student12("Fred",90);
        Student12 student2=new Student12("Barnie",90);
        Student12 student3=new Student12("Betty",90);




        Configuration config=new Configuration().configure().
                addAnnotatedClass(Student12.class);
        SessionFactory sf=config.buildSessionFactory();
        Session session =sf.openSession();
        Transaction transaction=session.beginTransaction();


        session.save(student1);
        session.save(student2);
        session.save(student3);


        transaction.commit();
        session.close();
        sf.close();







    }
}
