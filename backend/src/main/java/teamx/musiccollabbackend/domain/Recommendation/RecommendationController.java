package teamx.musiccollabbackend.domain.Recommendation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import teamx.musiccollabbackend.domain.Label.LabelModel;
import teamx.musiccollabbackend.domain.MusicSample.*;
import teamx.musiccollabbackend.domain.User.IUserRepository;
import teamx.musiccollabbackend.domain.User.UserModel;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Controller class for handling music sample recommendations based on user label preferences and like interactions.
 * This controller contains one single endpoint that results in calculating recommendations for a user.
 *
 * @author Djangesh Sardjoe
 */
@RestController
public class RecommendationController {

    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private MusicSampleLikeRepository musicSampleLikeRepository;
    @Autowired
    private MusicSamplePlayRepository musicSamplePlayRepository;
    @Autowired
    private MusicSampleRepository musicSampleRepository;

    /**
     * Get recommended music samples for a given user ID.
     *
     * @param userId The ID of the user for whom recommendations are requested.
     * @return Iterable of recommended music samples.
     */
    @GetMapping(value = "/recommendation/{userId}")
    public Iterable<MusicSampleModel> getRecommendedSamples(@PathVariable int userId) {
        UserModel currentUser = userRepository.findById(userId);

        if (currentUser != null) {
            Iterable<UserModel> topSimilarUsers = getTopLabeledSimilarUsers(currentUser);

            Iterable<UserModel> topLikedSimilarUsers = getTopLikedSimilarUsers(currentUser, topSimilarUsers);

            return getRecommendedSamples(topLikedSimilarUsers);
        } else {
            // Handle user not found
            return Collections.emptyList();
        }
    }


    /**
     * Get top labeled similar users based on Jaccard similarity.
     *
     * @param currentUser The current user for whom similar users are to be determined.
     * @return Iterable of top labeled similar users.
     */
    private Iterable<UserModel> getTopLabeledSimilarUsers(UserModel currentUser) {
        List<UserModel> allUsers = userRepository.findAll();  // Fetch all users from the repository

        // Calculate Jaccard similarity for each user
        Map<UserModel, Double> similarityScores = new HashMap<>();
        for (UserModel otherUser : allUsers) {
            if (otherUser.getIdUser() != currentUser.getIdUser()) {
                double jaccardSimilarity = calculateJaccardSimilarity(currentUser.getLabels(), otherUser.getLabels());
                similarityScores.put(otherUser, jaccardSimilarity);
            }
        }

        // Sort users by similarity scores in descending order and puts them into a list.
        List<UserModel> sortedUsers = similarityScores.entrySet().stream()
                .sorted(Map.Entry.<UserModel, Double>comparingByValue().reversed())
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

        // Return the top 50 similar users or less if there are fewer than 50 users
        return sortedUsers.subList(0, Math.min(50, sortedUsers.size()));
    }

    /**
     * Calculate Jaccard similarity between two lists of labels.
     *
     * @param list1 The first list of labels.
     * @param list2 The second list of labels.
     * @return Jaccard similarity score.
     */
    private double calculateJaccardSimilarity(List<LabelModel> list1, List<LabelModel> list2) {
        Set<LabelModel> set1 = new HashSet<>(list1);
        Set<LabelModel> set2 = new HashSet<>(list2);

        // This retains all users that are contained by both sets.
        Set<LabelModel> intersection = new HashSet<>(set1);
        intersection.retainAll(set2);

        // Calculate union
        Set<LabelModel> union = new HashSet<>(set1);
        union.addAll(set2);

        // Calculate Jaccard similarity based on label presence
        int intersectionSize = intersection.size();
        int unionSize = union.size();

        return (double) intersectionSize / unionSize;
    }

    /**
     * Get top liked similar users based on user interactions.
     *
     * @param currentUser       The current user for whom similar users are to be determined.
     * @param topSimilarUsers   Iterable of top labeled similar users.
     * @return Iterable of top liked similar users.
     */
    private Iterable<UserModel> getTopLikedSimilarUsers(UserModel currentUser, Iterable<UserModel> topSimilarUsers) {
        // Collect user IDs of top similar users
        Set<Integer> topSimilarUserIds = StreamSupport.stream(topSimilarUsers.spliterator(), false)
                .map(UserModel::getIdUser)
                .collect(Collectors.toSet());

        // Retrieve liked samples by the top similar users
        List<MusicSampleLike> topLikedSamples = musicSampleLikeRepository.findByUserIds(new ArrayList<>(topSimilarUserIds));

        // Group liked samples by user ID
        Map<Integer, Long> similarityScores = topLikedSamples.stream()
                .collect(Collectors.groupingBy(MusicSampleLike::getUserId, Collectors.counting()));

        // Sort users by similarity scores in descending order and puts them into a list.
        List<UserModel> sortedUsers = similarityScores.entrySet().stream()
                .sorted(Map.Entry.<Integer, Long>comparingByValue().reversed())
                .map(entry -> userRepository.findById(entry.getKey()).orElse(null))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        // Return the top 10 similar users or less if there are fewer than 10 users
        return sortedUsers.subList(0, Math.min(10, sortedUsers.size()));
    }

    /**
     * Get recommended music samples for a list of top liked similar users.
     *
     * @param topLikedSimilarUsers Iterable of top liked similar users.
     * @return Iterable of recommended music samples.
     */
    private Iterable<MusicSampleModel> getRecommendedSamples(Iterable<UserModel> topLikedSimilarUsers) {
        // Collect user IDs of top similar users
        Set<Integer> topSimilarUserIds = StreamSupport.stream(topLikedSimilarUsers.spliterator(), false)
                .map(UserModel::getIdUser)
                .collect(Collectors.toSet());

        // Retrieve liked and played samples by the top similar users
        List<MusicSampleLike> topLikedSamples = musicSampleLikeRepository.findByUserIds(new ArrayList<>(topSimilarUserIds));
        List<MusicSamplePlay> topPlayedSamples = musicSamplePlayRepository.findByUserIds(new ArrayList<>(topSimilarUserIds));

        // Combine liked and played samples into a single list
        List<MusicSampleModel> combinedSamples = new ArrayList<>();
        topLikedSamples.forEach(like -> combinedSamples.add(like.getMusicSample()));
        topPlayedSamples.forEach(play -> combinedSamples.add(play.getMusicSample()));

        // Group combined samples by ID
        Map<Integer, Long> sampleScores = combinedSamples.stream()
                .collect(Collectors.groupingBy(MusicSampleModel::getIdMusicSample, Collectors.counting()));

        // Sort samples by scores in descending order
        List<MusicSampleModel> sortedSamples = sampleScores.entrySet().stream()
                .sorted(Map.Entry.<Integer, Long>comparingByValue().reversed())
                .map(entry -> musicSampleRepository.findById(entry.getKey()).orElse(null))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        // Return the top 10 recommended samples or less if there are fewer than 10 samples
        return sortedSamples.subList(0, Math.min(10, sortedSamples.size()));
    }

}
