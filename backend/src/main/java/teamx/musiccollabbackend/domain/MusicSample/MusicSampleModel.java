package teamx.musiccollabbackend.domain.MusicSample;


import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import teamx.musiccollabbackend.domain.Label.LabelModel;
import teamx.musiccollabbackend.domain.User.UserModel;


import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * This class creates a MusicSample entity which artists will use to display their work.
 * It contains necessary information about the music sample, such as a user ID, sample name, length, etc.
 *
 * @author Djangesh Sardjoe
 */
@Entity
@Table(name = "MusicSample")
public class MusicSampleModel {

    /*
     * START OF COLUMNS CONTAINED IN DATABASE
     */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idMusicSample")
    private int idMusicSample;

    @ManyToOne
    @JoinColumn(name = "idUser", referencedColumnName = "idUser", insertable = false, updatable = false)
    private UserModel user;

    @Column(name="idUser")
    private int idUser;

    @Column(name = "sample_name")
    private String sampleName;

    @Column(name = "sample_extension")
    private String sampleExtension;

    @Column(name = "sample_length")
    private int sampleLength;

    @Column(name = "bpm")
    private int bpm;

    @Column(name = "sample_description")
    private String sampleDescription;

    @Column(name="active")
    int active;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date_created")
    private Date dateCreated;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date_modified")
    private Date dateModified;

    /*
     * END OF COLUMNS CONTAINED IN DATABASE
     */

    // Relationships

    @OneToMany(mappedBy = "musicSample", cascade = CascadeType.ALL)
    private List<MusicSampleLike> likes;

    @OneToMany(mappedBy = "musicSample", cascade = CascadeType.ALL)
    private List<MusicSampleDislike> dislikes;

    @OneToMany(mappedBy = "musicSample")
    private List<MusicSamplePlay> plays;

    @OneToMany(mappedBy = "musicSample")
    private List<MusicSampleReport> reports;

    /*
     * START OF SETTERS
     */
    public void setIdMusicSample(int idMusicSample) {
        this.idMusicSample = idMusicSample;
    }

    public void setUser(UserModel user) {
        this.user = user;
        this.idUser = user.getIdUser();
    }

    public void setSampleName(String sampleName) {
        this.sampleName = sampleName;
    }

    public void setSampleExtension(String sampleExtension) {
        this.sampleExtension = sampleExtension;
    }

    public void setSampleLength(int sampleLength) {
        this.sampleLength = sampleLength;
    }

    public void setBpm(int bpm) {
        this.bpm = bpm;
    }

    public void setSampleDescription(String sampleDescription) {
        this.sampleDescription = sampleDescription;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public void setDateModified(Date dateModified) {
        this.dateModified = dateModified;
    }

    public void setLabels(List<LabelModel> toSet){this.labels = toSet;}
    /*
     * END OF SETTERS
     */

    /*
     * START OF GETTERS
     */

    public int getIdMusicSample() {
        return idMusicSample;
    }

    public UserModel getUser() {
        return user;
    }

    public int getIdUser() {
        return idUser;
    }

    public String getSampleName() {
        return sampleName;
    }

    public String getSampleExtension() {
        return sampleExtension;
    }

    public int getSampleLength() {
        return sampleLength;
    }

    public int getBpm() {
        return bpm;
    }

    public String getSampleDescription() {
        return sampleDescription;
    }

    public int getActive(){
        return active;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public Date getDateModified() {
        return dateModified;
    }

    public List<LabelModel> getLabels() {return labels;}


    public int getLikes() {
        return likes.size();
    }

    public int getDislikes() {
        return dislikes.size();
    }

    public int getPlays() {
        return plays.size();
    }

    // Getters - not included by default

    @JsonIgnore
    public List<MusicSampleLike> getLikesObj() {
        return likes;
    }

    @JsonIgnore
    public List<MusicSampleDislike> getDislikesObj() {
        return dislikes;
    }

    @JsonIgnore
    public List<MusicSamplePlay> getPlaysObj() {
        return plays;
    }

    @JsonIgnore
    public List<MusicSampleReport> getReports() {
            return reports;
        }

    /*
     * END OF GETTERS
     */

    @ManyToMany
    @JoinTable(
            name = "Label_has_MusicSample",
            joinColumns = @JoinColumn(name = "idMusicSample"),
            inverseJoinColumns = @JoinColumn(name = "idLabel")
    )
    private List<LabelModel> labels;
}
