package teamx.musiccollabbackend.domain.MusicSample;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "MusicSamplePlay")
@IdClass(MusicSampleUserId.class)
public class MusicSamplePlay {

    // Fields

    @Id
    @ManyToOne
    @JoinColumn(name = "idMusicSample")
    @JsonIgnore
    private MusicSampleModel musicSample;

    @Id
    @Column(name = "idUser")
    private int userId;

    @Column(name = "date_played")
    private LocalDateTime datePlayed;

    // Setters

    public void setMusicSample(MusicSampleModel musicSample) {
        this.musicSample = musicSample;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setDatePlayed(LocalDateTime datePlayed) {
        this.datePlayed = datePlayed;
    }

    // Getters

    public MusicSampleModel getMusicSample() {
        return musicSample;
    }

    public int getUserId() {
        return userId;
    }

    public LocalDateTime getDatePlayed() {
        return datePlayed;
    }

}
