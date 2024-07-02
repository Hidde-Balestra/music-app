package teamx.musiccollabbackend.domain.User;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "UserReport")
public class UserReport {

    // Fields

    @Id
    @ManyToOne
    @JoinColumn(name = "idUserReported")
    @JsonIgnore
    private UserModel reportedUser;

    @Id
    @ManyToOne
    @JoinColumn(name = "idUserReporter")
    private UserModel reportingUser;

    @Column(name = "message")
    private String message;

    @Column(name = "date_report")
    private LocalDateTime dateReport;

    // Setters

    public void setReportedUser(UserModel reportedUser) {
        this.reportedUser = reportedUser;
    }

    public void setReportingUser(UserModel reportingUser) {
        this.reportingUser = reportingUser;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setDateReport(LocalDateTime dateReport) {
        this.dateReport = dateReport;
    }

    // Getters

    public UserModel getReportedUser() {
        return reportedUser;
    }

    public UserModel getReportingUser() {
        return reportingUser;
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getDateReport() {
        return dateReport;
    }

}
