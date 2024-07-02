package teamx.musiccollabbackend.domain.Search;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import teamx.musiccollabbackend.domain.MusicSample.MusicSampleModel;
import teamx.musiccollabbackend.domain.MusicSample.MusicSampleRepository;
import teamx.musiccollabbackend.domain.User.IUserRepository;
import teamx.musiccollabbackend.domain.User.UserModel;

import java.util.LinkedList;
import java.util.List;

@RestController
public class SearchController {

    @Autowired
    IUserRepository userRepo;

    @Autowired
    MusicSampleRepository sampleRepo;

    /**
     * Returns a List of UserModels for each user that has info which matches a string in Query.
     *
     * @param query The list of Strings to search all Users for.
     * @return The list of found Users.
     */

    @GetMapping(value = "/search/users")
    public List<UserModel> searchUsers(SearchQueryModel query){

        List<UserModel> results = new LinkedList<>();

        for(String keyword : query.getQuery()) {
            // Search for contact info
            results.addAll(
                    userRepo.findAllByContactInfoIsContainingIgnoreCase(keyword));
            // Search for country
            results.addAll(
                    userRepo.findAllByCountryContainingIgnoreCase(keyword));
            // Search for first and last names
            results.addAll(
                    userRepo.findAllByFirstNameContainingIgnoreCase(keyword));
            results.addAll(
                    userRepo.findAllByLastNameContainingIgnoreCase(keyword));
        }

        return results;
    }

    /**
     * Returns a List of MusicSampleModels for each sample that has info which matches a string in Query.
     *
     * @param query The list of Strings to search all MusicSampleModels for.
     * @return The list of found MusicSampleModels.
     */

    @GetMapping(value = "/search/samples")
    public List<MusicSampleModel> searchSamples(SearchQueryModel query){

        List<MusicSampleModel> results = new LinkedList<>();

        for(String keyword : query.getQuery()) {
            // Search for sample name
            results.addAll(
                sampleRepo.findAllBySampleNameContainingIgnoreCaseAndActive(keyword, 1)
            );
            // Search for sample description
            results.addAll(
                sampleRepo.findAllBySampleDescriptionContainingIgnoreCaseAndActive(keyword, 1)
            );
        }

        return results;
    }
}
