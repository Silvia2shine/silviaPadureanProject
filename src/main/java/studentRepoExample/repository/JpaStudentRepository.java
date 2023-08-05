package studentRepoExample.repository;

import com.siit.studentRepoExample.model.Student;
import com.siit.studentRepoExample.model.Track;
import com.siit.studentRepoExample.model.dto.CreateStudentDto;
import jakarta.persistence.*;

import java.util.List;
import java.util.regex.Pattern;

import static com.siit.studentRepoExample.model.MainClass.ENTITY_MANAGER_FACTORY;

public class JpaStudentRepository {

   private EntityManager entityManager ;

    public JpaStudentRepository() {
        this.entityManager =  ENTITY_MANAGER_FACTORY.createEntityManager();
    }

    public List<Student> getAllStudents() {

        TypedQuery<Student> typedQuery = entityManager.createQuery("select s from Student s", Student.class);
        List<Student> studentList = typedQuery.getResultList();
        return studentList;

    }


    public List<Student> getAllStudentsOneLine() {
        return entityManager.createQuery("from Student").getResultList();
    }

    public void addStudent(Student student) {
        if (!entityManager.isOpen())
        {
            entityManager =   ENTITY_MANAGER_FACTORY.createEntityManager();
        }
        entityManager.getTransaction().begin();
        entityManager.persist(student);
        entityManager.getTransaction().commit();
    }

    public boolean login(Student student){
        Student studentDB = findStudentById(student.getId());

        if (studentDB == null){
            return false;
        }


        return true;
    }

    public boolean deleteStudentById(int id) {
        entityManager.getTransaction().begin();

        Student student = entityManager.find(Student.class, id);
        if (student == null) {
            return false;
        }

        entityManager.remove(student);
        entityManager.getTransaction().commit();


        return true;
    }

    public boolean deleteStudentByName(String name) {
//        entityManager.getTransaction().begin();
//        try {
//
//
//            Student student = entityManager.find(Student.class, name);
//            if (student == null) {
//                return false;
//            }
//            entityManager.remove(student);
//            entityManager.getTransaction().commit();
//
//
//
//        } catch (Exception e) {
//            return false;
//        }
//
//
//        return true;
        entityManager.getTransaction().begin();
        try {
            TypedQuery<Student> query = entityManager.createQuery(
                    "SELECT s FROM Student s WHERE s.name = :name", Student.class
            );
            query.setParameter("name", name);
            Student student = query.getSingleResult();

            if (student == null) {
                return false;
            }

            entityManager.remove(student);
            entityManager.getTransaction().commit();
            return true;
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            return false;
        }

    }

    public void updateStudentEmail(int id, String email) {
        entityManager.getTransaction().begin();
        Student student = entityManager.find(Student.class, id);

        //before update
        System.out.println(student);
        student.setEmail(email);
        entityManager.getTransaction().commit();

        //after update
        System.out.println(student);



    }

    public Student findStudentByName(String name) {
        try {
        TypedQuery<Student> query = entityManager.createQuery(
                "SELECT s FROM Student s WHERE s.name = :name", Student.class
        );
        query.setParameter("name", name);
        Student student = query.getResultList().get(0);


            return student;
        } catch (Exception e) {
            return null;
        }

    }

    public Student findStudentById(int id) {
        Student student = entityManager.find(Student.class, id);
        if (student == null)
            return null;

        System.out.println("student name = " + student.getName());
        System.out.println("Student email  = " + student.getEmail());
        return student;

    }

    public boolean deleteAllStudents() {
        EntityManager entityManager = null;
        try {

            entityManager.getTransaction().begin();
            List<Student> students = entityManager.createQuery("SELECT s from Student s", Student.class)
                    .getResultList();
            for (Student student : students) {
                entityManager.remove((student));
            }
            entityManager.getTransaction().commit();
            return true;
        } catch (Exception e) {
            if (entityManager != null && entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            e.printStackTrace();
            return false;
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }


    public boolean addStudentWithPassword(Student student) {
        try{
            if (!validateStudentData(student))
                return false;

            this.addStudent(student);
            return true;
        }
        catch (Exception e){
            return  false;
        }

    }

    public boolean addTrackToStudent(String trackId, int studentId){

        System.out.println(trackId);
        System.out.println(studentId);
        return true;
    }

    public boolean validateStudentData(Student student) {
        if (existInDb(student) == false) {
            return false;
        } else {
            String emailValidationPattern = "^(.+)@(.+)$";
            boolean emailIsOK = student.getEmail() != null && Pattern.compile(emailValidationPattern)
                    .matcher(student.getEmail())
                    .matches();
            boolean nameIsOk = student.getName() != null && student.getName().length() < 50;
            boolean passwordIsOk = ((student.getPassword() != null) && (student.getPassword().length() < 50)
                    && (!student.getPassword().equals(" ")));

            return (emailIsOK && nameIsOk && passwordIsOk);
        }

    }

    public boolean existInDb(Student student) {


        List<Student> students = getAllStudents();
        for (Student stud : students) {
            if (stud.getEmail().equals(student.getEmail())) {
                return false;
            }

        }
        return true;
    }

    public Student existInDbWithPass(Student student) {
        if (student == null || student.getEmail() == null || student.getPassword() == null) {
            return null;
        }

        TypedQuery<Student> query = entityManager.createQuery(
                "SELECT s FROM Student s WHERE LOWER(s.email) = LOWER(:email) AND s.password = :password", Student.class
        );
        query.setParameter("email", student.getEmail().toLowerCase());
        query.setParameter("password", student.getPassword());

        try {
            return query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }




    }







