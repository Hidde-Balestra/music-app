package teamx.musiccollabbackend.domain.dto.sample;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import teamx.musiccollabbackend.domain.Label.LabelModel;
import teamx.musiccollabbackend.domain.MusicSample.*;
import teamx.musiccollabbackend.domain.User.UserModel;

import java.util.Date;
import java.util.List;

public class Base64MusicSample {
    private int idMusicSample;

    @ManyToOne
    @JoinColumn(name = "idUser", referencedColumnName = "idUser", insertable = false, updatable = false)
    private UserModel user;

    private String sampleName;
    private String sampleExtension;
    private int sampleLength;
    private int bpm;
    private String sampleDescription;
    private int active;
    private Date dateCreated;
    private Date dateModified;
    private List<LabelModel> labels;
    private int likes;
    private int dislikes;
    private int plays;
    private List<MusicSampleLike> likesObj;
    private List<MusicSampleDislike> dislikesObj;
    private List<MusicSamplePlay> playsObj;

    private String base64Encoded;


    // Default constructor
    public Base64MusicSample() {
    }

    // Constructor with MusicSampleModel parameter
    public Base64MusicSample(MusicSampleModel musicSample, String base64Encoded) {
        this.idMusicSample = musicSample.getIdMusicSample();
        this.user = musicSample.getUser();
        this.sampleName = musicSample.getSampleName();
        this.sampleExtension = musicSample.getSampleExtension();
        this.sampleLength = musicSample.getSampleLength();
        this.bpm = musicSample.getBpm();
        this.sampleDescription = musicSample.getSampleDescription();
        this.active = musicSample.getActive();
        this.dateCreated = musicSample.getDateCreated();
        this.dateModified = musicSample.getDateModified();
        this.labels = musicSample.getLabels();
        this.likes = musicSample.getLikes();
        this.dislikes = musicSample.getDislikes();
        this.plays = musicSample.getPlays();
        this.likesObj = musicSample.getLikesObj();
        this.dislikesObj = musicSample.getDislikesObj();
        this.playsObj = musicSample.getPlaysObj();
        this.base64Encoded = base64Encoded;
    }


    public int getIdMusicSample() {
        return idMusicSample;
    }

    public void setIdMusicSample(int idMusicSample) {
        this.idMusicSample = idMusicSample;
    }


    public UserModel getUser() {
        return user;
    }


    public void setUser(UserModel user) {
        this.user = user;
    }

    public String getSampleName() {
        return sampleName;
    }

    public void setSampleName(String sampleName) {
        this.sampleName = sampleName;
    }

    public String getSampleExtension() {
        return sampleExtension;
    }

    public void setSampleExtension(String sampleExtension) {
        this.sampleExtension = sampleExtension;
    }

    public int getSampleLength() {
        return sampleLength;
    }

    public void setSampleLength(int sampleLength) {
        this.sampleLength = sampleLength;
    }

    public int getBpm() {
        return bpm;
    }

    public void setBpm(int bpm) {
        this.bpm = bpm;
    }

    public String getSampleDescription() {
        return sampleDescription;
    }

    public void setSampleDescription(String sampleDescription) {
        this.sampleDescription = sampleDescription;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getDateModified() {
        return dateModified;
    }

    public void setDateModified(Date dateModified) {
        this.dateModified = dateModified;
    }

    public List<LabelModel> getLabels() {
        return labels;
    }

    public void setLabels(List<LabelModel> labels) {
        this.labels = labels;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getDislikes() {
        return dislikes;
    }

    public void setDislikes(int dislikes) {
        this.dislikes = dislikes;
    }

    public int getPlays() {
        return plays;
    }

    public void setPlays(int plays) {
        this.plays = plays;
    }

    public List<MusicSampleLike> getLikesObj() {
        return likesObj;
    }

    public void setLikesObj(List<MusicSampleLike> likesObj) {
        this.likesObj = likesObj;
    }

    public List<MusicSampleDislike> getDislikesObj() {
        return dislikesObj;
    }

    public void setDislikesObj(List<MusicSampleDislike> dislikesObj) {
        this.dislikesObj = dislikesObj;
    }

    public List<MusicSamplePlay> getPlaysObj() {
        return playsObj;
    }

    public void setPlaysObj(List<MusicSamplePlay> playsObj) {
        this.playsObj = playsObj;
    }

    public String getBase64Encoded() {
        return base64Encoded;
    }

    public void setBase64Encoded(String base64Encoded) {
        this.base64Encoded = base64Encoded;
    }
}
