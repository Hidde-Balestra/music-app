package teamx.musiccollabbackend.domain.Label;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name="Label")
public class LabelModel {

    // Fields

    @Id
    @Column(name="idLabel")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(name="label_name")
    String name;

    @Column(name="label_description")
    String description;

    enum LabelType {
        GENRE,
        INSTRUMENT,
    }

    @Column(name="label_type")
    @Enumerated(EnumType.STRING)
    LabelType type;

    @Column(name="date_created")
    LocalDateTime dateCreated;

    @Column(name="date_modified")
    LocalDateTime  dateModified;

    // Setters

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setType(LabelType type) {
        this.type = type;
    }

    public void setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

    public void setDateModified(LocalDateTime dateModified) {
        this.dateModified = dateModified;
    }

    // Getters

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public LabelType getType() {
        return type;
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public LocalDateTime getDateModified() {
        return dateModified;
    }

    // Equals and HashCode methods
    // Custom equals method to ensure we properly compare two LabelModels to each other.
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || o.getClass() != LabelModel.class) return false;
        LabelModel that = (LabelModel) o;
        return id == that.id &&
                Objects.equals(name, that.name) &&
                type == that.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, type);
    }
}
