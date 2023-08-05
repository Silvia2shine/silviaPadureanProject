package studentRepoExample.repository;

import com.siit.studentRepoExample.model.Track;
import com.siit.studentRepoExample.model.Student;
import jakarta.persistence.*;

import java.util.List;

public class TracksRepository {
    private final EntityManager entityManager;

    public TracksRepository() {
        EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("Eclipselink_JPA");
        this.entityManager = emFactory.createEntityManager();
    }
    public List<Track> getAllTracks() {
        TypedQuery<Track> typedQuery = entityManager.createQuery("select t from Track t", Track.class);
        List<Track> tracks = typedQuery.getResultList();
        entityManager.close();

        return tracks;
    }

    public List<Student> getAllStudentsOneLine() {
        return entityManager.createQuery("from Student").getResultList();
    }

    public boolean addTrack(Track track) {
        if (track.getName().equals("Name...") || (track.getName().isBlank())){
            return false;
        }
        if (track.getAudio_link().equals("Audio_link...") || (track.getAudio_link().isBlank())){
            return false;
        }
        entityManager.getTransaction().begin();
        entityManager.persist(track);
        entityManager.getTransaction().commit();
        entityManager.close();
        return true;
    }

    public boolean deleteTrackById(int id) {
//        entityManager.getTransaction( ).begin( );
//        Track track = entityManager.find( Track.class, id );
//        if (track != null) {
//            entityManager.remove(track);
//        }
//
//        entityManager.getTransaction( ).commit( );
//        entityManager.close( );
//
//

            entityManager.getTransaction().begin();

            Track track = entityManager.find(Track.class, id);
            if (track == null) {
                return false;
            }

            entityManager.remove(track);
            entityManager.getTransaction().commit();


            return true;
        }

    public void deleteTrackByName(String name) {
        entityManager.getTransaction( ).begin( );

        List<Track> tracks = entityManager.createQuery(
                        "SELECT t FROM Track t WHERE t.name = :name", Track.class)
                .setParameter("name", name)
                .getResultList();

        if (!tracks.isEmpty()) {
            for (Track track : tracks) {
                entityManager.remove(track);
            }
        }
        entityManager.getTransaction( ).commit( );
        entityManager.close( );


    }

    public void findTrackByName(String name) {
      Track track = entityManager.find(Track.class, name);

        System.out.println("track ID = " + track.getId());
        System.out.println("track audio_link = " + track.getAudio_link());
    }

    public void findTrackById(int id) {
        Track track = entityManager.find(Track.class, id);

        System.out.println("track name = " + track.getName());
        System.out.println("Track audio_link  = " + track.getAudio_link());

    }

    public boolean deleteAllTracks() {
        EntityManager entityManager = null;
        try {

            entityManager.getTransaction().begin();
            List<Track> tracks = entityManager.createQuery("SELECT t from Track t", Track.class)
                    .getResultList();
            for (Track track : tracks) {
                entityManager.remove((track));
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

}
