package teamx.musiccollabbackend.domain.MusicSample;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "MusicSampleReport")
@IdClass(MusicSampleUserId.class)
public class MusicSampleReport {

    // Fields

    @Id
    @ManyToOne
    @JoinColumn(name = "idMusicSample")
    @JsonIgnore
    private MusicSampleModel musicSample;

    @Id
    @Column(name = "idUser")
    private int userId;

    @Column(name = "message")
    private String message;

    @Column(name = "date_report")
    private LocalDateTime dateReport;

    // Setters

    public void setMusicSample(MusicSampleModel musicSample) {
        this.musicSample = musicSample;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setDateReport(LocalDateTime dateReport) { // Change the method name to "setDateReport"
        this.dateReport = dateReport;
    }

    // Getters

    public MusicSampleModel getMusicSample() {
        return musicSample;
    }

    public int getUserId() {
        return userId;
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getDateReport() { // Change the method name to "getDateReport"
        return dateReport;
    }

}