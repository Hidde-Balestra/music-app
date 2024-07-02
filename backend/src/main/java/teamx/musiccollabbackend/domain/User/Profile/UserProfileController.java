package teamx.musiccollabbackend.domain.User.Profile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import teamx.musiccollabbackend.domain.Label.ILabelRepository;
import teamx.musiccollabbackend.domain.Label.LabelModel;
import teamx.musiccollabbackend.domain.MusicSample.MusicSampleModel;
import teamx.musiccollabbackend.domain.MusicSample.MusicSampleRepository;
import teamx.musiccollabbackend.domain.User.IUserRepository;
import teamx.musiccollabbackend.domain.User.UserModel;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@RestController
public class UserProfileController {

    @Autowired
    IUserRepository userRepo;

    @Autowired
    MusicSampleRepository sampleRepo;

    @Autowired
    ILabelRepository labelRepo;


    @GetMapping(value = "/profile")
    public ResponseEntity getUserProfile(@RequestParam int idUser) {
        // Collect the various datasources and build up the profile
        UserModel user = userRepo.findById(idUser);
        Iterable<MusicSampleModel> samples = sampleRepo.findAllByIdUser(idUser);
        UserProfileModel profile = new UserProfileModel(user, samples);

        // Return it
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(profile);
    }

    @CrossOrigin(origins = "*")
    @PutMapping(value = "/profile/update/{userId}")
    public ResponseEntity updateUserProfile(
            @PathVariable int userId,
            @RequestBody Map<String, String> updateParams) {

        try {
            // Update the user by ID
            Optional<UserModel> optionalUser = Optional.ofNullable(userRepo.findById(userId));
            if (optionalUser.isPresent()) {

                UserModel user = optionalUser.get();

                AtomicReference<String> responseBodyString = new AtomicReference<>("Successfully changed");
                // Update user fields based on parameters
                updateParams.forEach((key, value) -> {
                    System.out.println(key + ": " + value);
                    switch (key) {
                        case "firstName":
                            if (value.trim().equals("")){
                                break;
                            }

                            user.setFirstName(value);
                            responseBodyString.set(responseBodyString + " firstName");

                            break;
                        case "lastName":
                            if (value.trim().equals("")){
                                break;
                            }

                            user.setLastName(value);
                            responseBodyString.set(responseBodyString + " lastName");

                            break;

                        case "labels":
                            if (value.trim().equals("")){
                                break;
                            }
                            responseBodyString.set(responseBodyString + " Labels");

                            String[] tokens = value.replaceAll("[\\[\\]]", "").split(",");
                            List<Integer> intList = Arrays.stream(tokens)
                                    .map(String::trim)
                                    .map(Integer::parseInt)
                                    .collect(Collectors.toList());

                            List<LabelModel> labels = labelRepo.findByIds(intList);

                            user.setLabels(labels);

                            break;
                        // Add more cases for other fields as needed

                        default:
                            // Ignore unknown parameters
                            break;
                    }
                });
                userRepo.save(user);

                // Return a success response
                return ResponseEntity
                        .status(HttpStatus.OK)
                        .body(responseBodyString.get());
            } else {
                // User not found
                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body("User not found");
            }
        } catch (Exception e) {
            // Handle exceptions and return an error response
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error updating user");
        }
    }
}
