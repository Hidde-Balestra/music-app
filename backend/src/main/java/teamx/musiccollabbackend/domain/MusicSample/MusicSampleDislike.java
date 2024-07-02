package teamx.musiccollabbackend.domain.MusicSample;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDateTime;
@Entity
@Table(name = "MusicSampleDislike")
@IdClass(MusicSampleUserId.class)
public class MusicSampleDislike {

    // Fields

    @Id
    @ManyToOne
    @JoinColumn(name = "idMusicSample")
    @JsonIgnore
    private MusicSampleModel musicSample;

    @Id
    @Column(name = "idUser")
    private int userId;

    @Column(name = "date_dislike")
    private LocalDateTime dateDisliked;

    // Setters

    public void setMusicSample(MusicSampleModel musicSample) {
        this.musicSample = musicSample;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setDateDisliked(LocalDateTime dateDisliked) {
        this.dateDisliked = dateDisliked;
    }

    // Getters

    public MusicSampleModel getMusicSample() {
        return musicSample;
    }

    public int getUserId() {
        return userId;
    }

    public LocalDateTime getDateDisliked() {
        return dateDisliked;
    }

}
