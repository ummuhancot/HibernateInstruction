package com.tpe.hb12.get_load;

import org.hibernate.ObjectNotFoundException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
/*
   get -> dönen objeye hemen ihtiyaç duyulursa get kullanılır.
       -> hemen db ye başvurur
       -> obje yoksa null döner
       -> id ile obje olduğuna emin değilsek get kullanılmalı

   load -> proxy(gölge) döner
        -> hemen db ye başvurmaz->ne zaman ihtiyaç duyulursa gerçek nesneyi döner
        -> obje yoksa not found exception fırlatır
        -> id ile obje olduğuna eminsek load kullanılmalı
        -> objeye reference olarak ihtiyaç duyulursa kullanılmalı

 */
public class RunnerFetch12 {

    public static void main(String[] args) {


        Configuration config=new Configuration().configure().
                addAnnotatedClass(Student12.class);
        SessionFactory sf=config.buildSessionFactory();
        Session session =sf.openSession();

        //get
        System.out.println("---------get methodundan önce--------");
        Student12 student = session.get(Student12.class,1);
        System.out.println("ID : "+student.getId());
        System.out.println("Ögrencinin adı : "+ student.getName());


        //load
        ///databaseden dataların uygulama icirisine yüklenmesini sağlar
        System.out.println("---------load methodundan önce--------");
        Student12 student2 = session.load(Student12.class,3);
        System.out.println("---------load methodundan sonra--------");

        //printStudent(Student12 student){
        // birçok kod
        //
        // student.name
        // };

        //obj.printStudent(student2);

        System.out.println("ID : "+student2.getId());
        System.out.println("Ögrencinin adı : "+ student2.getName());


        //tabloda olmayan bir kayıt icin kullanılırsa
        //get
        Student12 student3 = session.get(Student12.class,100);//null
        if(student3!=null){
            System.out.println(student3.getName());//NullPointerException
        }else {
            System.out.println("Ögrenci bulunamadı");
        }

        //tabloda olmayan bir kayıt icin kullanılırsa
        //load
        ///performans arttırır ama yazdırma anında exception bulunur
        ///tabloda kayıtlı olmayan bir veri istedimizde biz exception fırlattırız
        ///kendi o an anlamıyor isteyince fırlatıyor
        try {
            Student12 student4 = session.get(Student12.class,200);
            System.out.println("-------bu kısımda calıştığında bilmiyor olup olmasıdığını");
            System.out.println(student4);
            System.out.println("-------bu kısımda calıştığında artık exception fırlatır ");

        }catch (ObjectNotFoundException e){
            System.out.println("Ögrenci bulunamadı!");
        }

        session.close();
        sf.close();




    }
}
