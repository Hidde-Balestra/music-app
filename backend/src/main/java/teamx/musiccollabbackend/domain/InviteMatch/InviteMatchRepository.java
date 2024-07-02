package teamx.musiccollabbackend.domain.InviteMatch;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for managing InviteMatch records in the database.
 * Provides CRUD operations and custom query methods for handling collaboration invitations.
 *
 * @author Djangesh Sardjoe
 * @version 1.0
 * @since 2023-12-10
 */
@Repository
public interface InviteMatchRepository extends CrudRepository<InviteMatchModel, Integer> {
    // Inherits CrudRepository functionalities

    // Custom queries
    // Custom query to check if an invite already exists with the given sender and recipient IDs
    boolean existsByIdSenderAndIdRecipient(int idSender, int idRecipient);

    // Find InviteMatch by sender and recipient
    InviteMatchModel findByIdSenderAndIdRecipient(int idSender, int idRecipient);

    List<InviteMatchModel> findAllByIdRecipient( int idRecipient);

    // Find all InviteMatches
    List<InviteMatchModel> findAll();
}
