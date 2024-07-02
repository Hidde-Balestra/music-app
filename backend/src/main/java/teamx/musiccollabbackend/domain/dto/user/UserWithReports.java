package teamx.musiccollabbackend.domain.dto.user;

import teamx.musiccollabbackend.domain.MusicSample.MusicSampleReport;
import teamx.musiccollabbackend.domain.User.UserModel;
import teamx.musiccollabbackend.domain.Label.LabelModel;
import teamx.musiccollabbackend.domain.User.UserReport;

import java.time.LocalDateTime;
import java.util.List;

public class UserWithReports {

    private int idUser;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String contactInfo;
    private String occupation;
    private String country;
    private String description;
    private UserModel.AccountType accountType;
    private int active;
    private LocalDateTime dateCreated;
    private LocalDateTime dateModified;
    private List<LabelModel> labels;
    private List<UserReport> reports;

    // Default constructor
    public UserWithReports() {
    }

    // Constructor with UserModel parameter
    public UserWithReports(UserModel userModel) {
        this.idUser = userModel.getIdUser();
        this.email = userModel.getEmail();
        this.password = userModel.getPassword();
        this.firstName = userModel.getFirstName();
        this.lastName = userModel.getLastName();
        this.phoneNumber = userModel.getPhoneNumber();
        this.contactInfo = userModel.getContactInfo();
        this.occupation = userModel.getOccupation();
        this.country = userModel.getCountry();
        this.description = userModel.getDescription();
        this.accountType = userModel.getAccountType();
        this.active = userModel.getActive();
        this.dateCreated = userModel.getDateCreated();
        this.dateModified = userModel.getDateModified();
        this.labels = userModel.getLabels();
        this.reports = userModel.getReports();
    }

    // Getters and setters

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public UserModel.AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(UserModel.AccountType accountType) {
        this.accountType = accountType;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

    public LocalDateTime getDateModified() {
        return dateModified;
    }

    public void setDateModified(LocalDateTime dateModified) {
        this.dateModified = dateModified;
    }

    public List<LabelModel> getLabels() {
        return labels;
    }

    public void setLabels(List<LabelModel> labels) {
        this.labels = labels;
    }

    public List<UserReport> getReports() {
        return reports;
    }

    public void setReports(List<UserReport> labels) {
        this.reports = reports;
    }

}