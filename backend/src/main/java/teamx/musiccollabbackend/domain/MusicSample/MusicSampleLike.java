package teamx.musiccollabbackend.domain.MusicSample;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "MusicSampleLike")
@IdClass(MusicSampleUserId.class)
public class MusicSampleLike {

    // Fields

    @Id
    @ManyToOne
    @JoinColumn(name = "idMusicSample")
    @JsonIgnore
    private MusicSampleModel musicSample;

    @Id
    @Column(name = "idUser")
    private int userId;

    @Column(name = "date_liked")
    private LocalDateTime dateLiked;

    // Setters

    public void setMusicSample(MusicSampleModel musicSample) {
        this.musicSample = musicSample;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setDateLiked(LocalDateTime dateLiked) {
        this.dateLiked = dateLiked;
    }

    // Getters

    public MusicSampleModel getMusicSample() {
        return musicSample;
    }

    public int getUserId() {
        return userId;
    }

    public LocalDateTime getDateLiked() {
        return dateLiked;
    }

}