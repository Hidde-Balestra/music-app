package teamx.musiccollabbackend.domain.User;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import teamx.musiccollabbackend.domain.Label.LabelModel;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@Table(name = "User")
public class UserModel implements UserDetails {

    /*
     * START OF FIELDS CONTAINED IN DATABASE
     */

    @Id()
    @Column(name = "idUser")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int idUser;

    @Column(name = "email")
    String email;

    @Column(name="password")
    String password;

    @Column(name="first_name")
    String firstName;

    @Column(name="last_name")
    String lastName;

    @Column(name="phonenumber")
    String phoneNumber;

    @Column(name="contact_info")
    String contactInfo;

    @Column(name="occupation")
    String occupation;

    @Column(name="country")
    String country;

    @Column(name="description")
    String description;

    @Column(name="account_type")
    @Enumerated(EnumType.STRING)
    AccountType accountType;

    @Column(name="active")
    int active;

    @CreatedDate
    @Column(name="date_created")
    LocalDateTime dateCreated;

    @LastModifiedDate
    @Column(name="date_modified")
    LocalDateTime dateModified;

    public UserModel(String mail, String password) {
        this.email = mail;
        this.password = password;
    }

    public UserModel(String takenEmail) {
        this.email = takenEmail;
    }

    public UserModel() {}

    /*
     * END OF FIELDS CONTAINED IN DATABASE
     */


    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList();
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    public void setActive(int active) {
        this.active = active;
    }

    //    TODO: add real code instead of fixed vars

    @JsonIgnore
    public List<UserReport> getReports() {
        return reports;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @ManyToMany
    @JoinTable(
        name = "User_has_Label",
        joinColumns = @JoinColumn(name = "idUser"),
        inverseJoinColumns = @JoinColumn(name = "idLabel")
    )
    private List<LabelModel> labels;

    @OneToMany(mappedBy = "reportedUser")
    private List<UserReport> reports;


    public enum AccountType {
        ARTIST,
        MANAGER,
        ADMIN
    }

    public String toString(){
        return("Person: " + firstName + " " + lastName + " from " + country + " created at: " + dateCreated);//+ " with role: " + accountType.toString());
    }
}
