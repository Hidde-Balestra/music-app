package teamx.musiccollabbackend.domain.InviteMatch;

import jakarta.persistence.*;
import teamx.musiccollabbackend.domain.User.UserModel;

import java.util.Date;

/**
 * This class creates an InviteMatch entity for collaboration invitations between music artists.
 * It contains necessary information such as invite status, message, sender, recipient, etc.
 *
 * @author Djangesh Sardjoe
 */
@Entity
@Table(name = "InviteMatch")
public class InviteMatchModel {

    /*
     * START OF COLUMNS CONTAINED IN DATABASE
     */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idInviteMatch")
    private int idInviteMatch;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private InviteStatus status;

    @Column(name = "message")
    private String message;

    @Column(name = "id_sender")
    private int idSender;

    @Column(name = "id_recipient")
    private int idRecipient;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date_created")
    private Date dateCreated;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date_modified")
    private Date dateModified;

    /*
     * END OF COLUMNS CONTAINED IN DATABASE
     */

    /*
     * START OF SETTERS
     */
    public void setIdInviteMatch(int idInviteMatch) {
        this.idInviteMatch = idInviteMatch;
    }

    public void setStatus(InviteStatus status) {
        this.status = status;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setIdSender(int idSender) {
        this.idSender = idSender;
    }

    public void setIdRecipient(int idRecipient) {
        this.idRecipient = idRecipient;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public void setDateModified(Date dateModified) {
        this.dateModified = dateModified;
    }

    /*
     * END OF SETTERS
     */

    /*
     * START OF GETTERS
     */
    public int getIdInviteMatch() {
        return idInviteMatch;
    }

    public InviteStatus getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public int getIdSender() {
        return idSender;
    }

    public int getIdRecipient() {
        return idRecipient;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public Date getDateModified() {
        return dateModified;
    }

    /*
     * END OF GETTERS
     */

    // Enum for InviteStatus
    public enum InviteStatus {
        PENDING, MATCH, DECLINED
    }
}
