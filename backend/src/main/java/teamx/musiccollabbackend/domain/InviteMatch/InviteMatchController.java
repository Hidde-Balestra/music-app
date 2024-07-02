package teamx.musiccollabbackend.domain.InviteMatch;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.SftpException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import teamx.musiccollabbackend.domain.MusicSample.MusicSampleModel;

import java.util.Date;
import java.util.List;

/**
 * Controller class for handling InviteMatch HTTP requests.
 * This controller contains endpoints that perform several CRUD queries and thus manage invites that are being sent from one music creator to another.
 *
 * @author Djangesh Sardjoe
 */
@RestController
public class InviteMatchController {

    @Autowired
    InviteMatchRepository inviteMatchRepository;

    //Invites worden nooit verwijderd. Ze worden alleen op declined gezet.

    /**
     * Creates a new InviteMatch record in the database and sets its status to pending.
     * Checks for existing invites and cool down periods before creating a new invite.
     *
     * @param inviteMatch The InviteMatch to be created.
     * @return A ResponseEntity displaying the success or failure of the operation.
     */
    //For testing purposes the output is temporarily a ResponseEntity instead of a boolean.
    @PostMapping(value = "/invite/create")
    public ResponseEntity<String> createInviteMatch(@RequestBody InviteMatchModel inviteMatch) {
        try {
            // Check if an invite already exists with the given sender and recipient IDs
            if (inviteMatchRepository.existsByIdSenderAndIdRecipient(inviteMatch.getIdSender(), inviteMatch.getIdRecipient())) {
                InviteMatchModel dbInviteMatch = inviteMatchRepository.findByIdSenderAndIdRecipient(inviteMatch.getIdSender(), inviteMatch.getIdRecipient());

                //if declined and date modified is longer than a week ago update the message, status and dateModified
                if (dbInviteMatch.getStatus() == InviteMatchModel.InviteStatus.DECLINED && isMoreThanAWeekAgo(dbInviteMatch.getDateModified())) {
                    dbInviteMatch.setMessage(inviteMatch.getMessage());
                    dbInviteMatch.setStatus(InviteMatchModel.InviteStatus.PENDING);
                    dbInviteMatch.setDateModified(new Date());

                    inviteMatchRepository.save(dbInviteMatch);
                    return ResponseEntity.ok("InviteMatch successfully recreated with ID: " + dbInviteMatch.getIdInviteMatch());
                } else if (dbInviteMatch.getStatus() == InviteMatchModel.InviteStatus.DECLINED && !isMoreThanAWeekAgo(dbInviteMatch.getDateModified())){
                    return ResponseEntity.badRequest().body("Invite was previously declined by the recipient and the cool down time has not passed.");
                }
            }

            // Check if an invite already exists with the opposite sender and recipient IDs
            if (inviteMatchRepository.existsByIdSenderAndIdRecipient(inviteMatch.getIdRecipient(), inviteMatch.getIdSender())) {
                return ResponseEntity.badRequest().body("Invite already exists for the given sender and recipient IDs.");
            }


            // Set status to pending
            inviteMatch.setStatus(InviteMatchModel.InviteStatus.PENDING);

            // Set the dateModified to the current date and time
            inviteMatch.setDateCreated(new Date());

            // Set the dateModified to the current date and time
            inviteMatch.setDateModified(new Date());

            InviteMatchModel savedInviteMatch = inviteMatchRepository.save(inviteMatch);

            return ResponseEntity.ok("InviteMatch successfully created with ID: " + savedInviteMatch.getIdInviteMatch());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error creating invite: " + e.getMessage());
        }
    }

    /**
     * Retrieves information about an existing InviteMatch record based on sender and recipient IDs.
     *
     * @param idSender    The ID of the sender.
     * @param idRecipient The ID of the recipient.
     * @return The InviteMatchModel associated with the provided sender and recipient IDs, or null if not found.
     */
    //Retrieve informatie over een lopende invite. Stuur informatie mee naar de frontend.
    @PostMapping(value = "/invite/retrieve")
    public InviteMatchModel getInviteMatch(String idSender, String idRecipient) {


        // Check if an invite already exists with the given sender and recipient IDs
        if (inviteMatchRepository.existsByIdSenderAndIdRecipient(Integer.parseInt(idSender), Integer.parseInt(idRecipient))) {
            return inviteMatchRepository.findByIdSenderAndIdRecipient(Integer.parseInt(idSender), Integer.parseInt(idRecipient));
        }

        // Check if an invite already exists with the opposite sender and recipient IDs
        if (inviteMatchRepository.existsByIdSenderAndIdRecipient(Integer.parseInt(idRecipient), Integer.parseInt(idSender))) {
            return inviteMatchRepository.findByIdSenderAndIdRecipient(Integer.parseInt(idRecipient), Integer.parseInt(idSender));
        }
        return null;
    }

    /**
     * Accepts an existing InviteMatch by updating its status to "MATCH".
     *
     * @param idSender   The ID of the sender.
     * @param idAccepter The ID of the recipient/accepter.
     * @return True if the invite was successfully accepted, false otherwise.
     */
    @PostMapping(value = "/invite/accept")
    public boolean acceptInviteMatch(String idSender, String idAccepter) {

        // Acceptance must come from the recipient.
        if (inviteMatchRepository.existsByIdSenderAndIdRecipient(Integer.parseInt(idSender), Integer.parseInt(idAccepter))) {
            // Retrieve the invite by sender and recipient IDs
            InviteMatchModel inviteMatch = inviteMatchRepository.findByIdSenderAndIdRecipient(Integer.parseInt(idSender), Integer.parseInt(idAccepter));
            inviteMatch.setStatus(InviteMatchModel.InviteStatus.MATCH);

            // Set the dateModified to the current date and time
            inviteMatch.setDateModified(new Date());

            inviteMatchRepository.save(inviteMatch);
            return true;
        }

        return false;
    }

    /**
     * Declines an existing InviteMatch by updating its status to "DECLINED". This disables the invite instead of deleting it.
     *
     * @param idSender   The ID of the sender.
     * @param idAccepter The ID of the recipient/accepter.
     * @return True if the invite was successfully declined, false otherwise.
     */
    @PostMapping(value = "/invite/decline")
    public boolean declineInviteMatch(String idSender, String idAccepter) {

        // Acceptance must come from the recipient.
        if (inviteMatchRepository.existsByIdSenderAndIdRecipient(Integer.parseInt(idSender), Integer.parseInt(idAccepter))) {
            // Retrieve the invite by sender and recipient IDs
            InviteMatchModel inviteMatch = inviteMatchRepository.findByIdSenderAndIdRecipient(Integer.parseInt(idSender), Integer.parseInt(idAccepter));
            inviteMatch.setStatus(InviteMatchModel.InviteStatus.DECLINED);

            // Set the dateModified to the current date and time
            inviteMatch.setDateModified(new Date());

            inviteMatchRepository.save(inviteMatch);
            return true;
        }

        return false;
    }

    @GetMapping(value = "/invites/pending/{recipientId}")
    public List<InviteMatchModel> getAllInvitesByRecipientId(@PathVariable(value="recipientId") String recipientId){
        return inviteMatchRepository.findAllByIdRecipient(Integer.parseInt(recipientId));
    }

    // Helper method to check if a date is more than a week ago
    private boolean isMoreThanAWeekAgo(Date date) {
        long weekInMillis = 7L * 24L * 60L * 60L * 1000L;
        long currentTime = System.currentTimeMillis();
        return currentTime - date.getTime() > weekInMillis;
    }
}
