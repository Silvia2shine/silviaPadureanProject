package studentRepoExample.model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;


import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@Getter
@Setter
@ToString
@Entity
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String email;
    private String password;
    private String rol;

    @ManyToMany(cascade ={CascadeType.PERSIST}, fetch = FetchType.EAGER)
    @JoinTable(name = "students_tracks", joinColumns = {@JoinColumn(name = "student_id")},
            inverseJoinColumns = {@JoinColumn(name = "track_id")})
 //  @Fetch(FetchMode.JOIN)
    private List<Track> tracksList;


    public Student(int id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password= password;
    }

    public int getId(){
        return this.id;
    }

    public Student() {
    }
    public Student(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password= password;

    }

    public Student(String name, String email) {
        this.name = name;
        this.email = email;

    }


    public Student(int id, String name, String email, String password, String rol, List<Track> tracksList) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.rol = rol;
        this.tracksList = tracksList;
    }

    public String getTracksAsCsv() {
        if (tracksList != null && !tracksList.isEmpty()) {
            return this.tracksList.stream().map(track -> track.getName()).collect(Collectors.joining(", "));
        }
        return "Is empty";
    }

//    public String getTracksAsList() {
//        if (tracksList != null && !tracksList.isEmpty()) {
//            return this.tracksList.stream().map(track -> track.getName()).collect(Collectors.joining(", "));
//        }
//        return "Is empty";
//    }


    public List<Track> getTracksList() {
        return tracksList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return id == student.id && email.equals(student.email) && password.equals(student.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, password);
    }
}
