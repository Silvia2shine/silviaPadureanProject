package studentRepoExample.model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@ToString
@Entity
@Table(name = "tracks")
public class Track implements Serializable {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private int id;
//    private String name;
//    private String audio_link;
//    @ManyToMany(mappedBy = "tracksList")
//
//    private List<Student> studentList;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name", length = 50, nullable = false)
    private String name;
    @Column(name = "audio_link", length = 50, nullable = false)
    private String audio_link;
    @ManyToMany(cascade ={CascadeType.ALL}, fetch = FetchType.EAGER)
    @JoinTable(name = "students_tracks", joinColumns = {@JoinColumn(name = "student_id")},
            inverseJoinColumns = {@JoinColumn(name = "track_id")})
    private List<Student> studentList;

    public Track(int id, String name, String audio_link) {
        this.id = id;
        this.name = name;
        this.audio_link = audio_link;
    }

    public Track() {

    }

    public Track(int id) {
        this.id = id;
    }

    public Track(String name, String audio_link) {
        this.name = name;
        this.audio_link = audio_link;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAudio_link(String audio_link) {
        this.audio_link = audio_link;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAudio_link() {
        return audio_link;
    }

    @Override
    public String toString() {
        return "Track{" +
                "name='" + name + '\'' +
                ", audio_link='" + audio_link + '\'' +
                '}';
    }

    public String getStudentsAsCsv() {
        if (studentList != null && !studentList.isEmpty()) {
            return this.studentList.stream().map(student -> student.getName()).collect(Collectors.joining(", "));
        }
        return "Is empty";
    }

    public String getAudioLink(){
        return this.getAudio_link();
    }
}
