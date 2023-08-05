package studentRepoExample.model;

import com.siit.studentRepoExample.repository.JpaStudentRepository;
import com.siit.studentRepoExample.repository.TracksRepository;
import jakarta.persistence.*;

import java.util.List;


public class MainClass {
    public static EntityManagerFactory ENTITY_MANAGER_FACTORY =
            Persistence.createEntityManagerFactory("Eclipselink_JPA");

    public static void main(String[] args) {
        System.out.println("Hello");

        JpaStudentRepository  rep = new JpaStudentRepository();

     //   rep.findStudentById(1);
      //  rep.deleteStudentByName("Art Morais");
        Student student =  new Student("asa","asfa@gmail.com","1234");
        rep.addStudent(student);

//        TracksRepository trk = new TracksRepository();
    //    trk.findTrackByName("Shakira");



        ENTITY_MANAGER_FACTORY.close();


    }

}

