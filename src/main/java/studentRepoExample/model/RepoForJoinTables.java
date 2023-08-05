package studentRepoExample.model;


import jakarta.persistence.*;

import java.util.List;

public class RepoForJoinTables {
    private static EntityManagerFactory ENTITY_MANAGER_FACTORY =
            Persistence.createEntityManagerFactory("Eclipselink_JPA");

    public static void addStudent(String name, String email) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;
        try {
            et = em.getTransaction();
            et.begin();
            Student stud = new Student();

            stud.setName(name);
            stud.setEmail(email);
            em.persist(stud);
            et.commit();
        } catch (Exception ex) {
            if (et != null) {
                et.rollback();
            }
            ex.printStackTrace();
        } finally {
            em.close();
        }

    }

    public static void getStudent(int id) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        String query = "SELECT s FROM Student s WHERE s.id =: studID";
        TypedQuery<Student> tq = em.createQuery(query, Student.class);
        tq.setParameter("studID", id);
        Student stud = null;
        try {
            stud = tq.getSingleResult();
            System.out.println(stud.getName() + " " + stud.getEmail() +
                    "  The track list is: " + stud.getTracksAsCsv());

        } catch (NoResultException ex) {
            ex.printStackTrace();
        } finally {
            em.close();
        }

    }

    public static void getStudents() {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        String strQuery = "SELECT s FROM Student s WHERE s.id IS NOT NULL";
        TypedQuery<Student> tq = em.createQuery(strQuery, Student.class);
        List<Student> studentList;
        try {
            studentList = tq.getResultList();
            studentList.forEach(student -> System.out.println(student.getName() + " " +
                    student.getEmail() + ". The list of tracks is: " + student.getTracksAsCsv()));
        } catch (NoResultException ex) {
            ex.printStackTrace();
        } finally {
            em.close();
        }

    }

    public static void deleteStudent(int id) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;
        Student stud = null;
        try {
            et = em.getTransaction();
            et.begin();
            stud = em.find(Student.class, id);
            em.remove(stud);
            em.persist(stud);
            et.commit();
            System.out.println("deleted student with id:" + id);
        } catch (Exception ex) {
            if (et != null) {
                et.rollback();
            }
            ex.printStackTrace();
        } finally {
            em.close();
        }

    }

    public static void getTracks() {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        String strQuery = "SELECT t FROM Track t WHERE t.id IS NOT NULL";
        TypedQuery<Track> tq = em.createQuery(strQuery, Track.class);
        List<Track> trackList;
        try {
            trackList = tq.getResultList();
            trackList.forEach(track -> System.out.println(track.getName() + " " +
                    track.getAudio_link()));
        } catch (NoResultException ex) {
            ex.printStackTrace();
        } finally {
            em.close();
        }

    }


}
