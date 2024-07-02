package teamx.musiccollabbackend.domain.MusicSample;

import com.jcraft.jsch.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import teamx.musiccollabbackend.domain.Label.ILabelRepository;
import teamx.musiccollabbackend.domain.Label.LabelModel;
import teamx.musiccollabbackend.domain.dto.sample.Base64MusicSample;
import teamx.musiccollabbackend.domain.dto.sample.LinkLabelsToMusicSampleRequest;
import teamx.musiccollabbackend.domain.utils.Date;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

/**
 * Controller class for handling MusicSample HTTP requests.
 * This controller contains endpoints that perform several CRUD queries and thus manage music samples within the database and SFTP server.
 *
 * @author Djangesh Sardjoe
 */
@RestController
public class MusicSampleController {
    private static final List<String> ALLOWED_EXTENSIONS = Arrays.asList("mp3", "wav", "flac");
    private String localFile = "src/main/resources/localSamples/GoDeep.mp3";
    private String incorrectLocalFile = "src/main/resources/localSamples/GoDeep.meow";
    private final String TARGET_DIR = "samples/";


    @Autowired
    MusicSampleRepository musicSampleRepository;

    @Autowired
    ILabelRepository labelRepo;

    private String remoteHost = "theownage.stack.storage";
    private String username = "theownage@theownage.stack.storage";
    private String password = "BIvGSlN3um_XVuZy-95xHuWned8";

    /**
     * Sets up and returns an instance of ChannelSftp using the JSch dependency for the SFTP connection.
     *
     * @return An initialized ChannelSftp instance.
     * @throws JSchException If there is a problem connecting to the SFTP server.
     */
    private ChannelSftp setupJsch() throws JSchException {
        JSch jsch = new JSch();
        jsch.setKnownHosts("/Users/john/.ssh/known_hosts");
        Session jschSession = jsch.getSession(username, remoteHost);
        java.util.Properties config = new java.util.Properties();
        config.put("StrictHostKeyChecking", "no");
        jschSession.setConfig(config);
        jschSession.setPassword(password);
        jschSession.connect();
        return (ChannelSftp) jschSession.openChannel("sftp");
    }

    /**
     * Checks if the given file has an allowed extension based on a predefined list.
     *
     * @param filePath The path of the file to be checked.
     * @return True if the file has a permitted extension, false if not.
     */
    private Boolean checkExtension(String filePath) {
        String fileExtension = getFileExtension(filePath);
        return ALLOWED_EXTENSIONS.contains(fileExtension.toLowerCase());
    }


    /**
     * Gets the file extension of a given file by providing its path.
     *
     * @param filePath The path of the file.
     * @return The file extension (excluding the dot), or null if no valid extension is found.
     */
    private String getFileExtension(String filePath) {
        return filePath.substring(filePath.lastIndexOf('.') + 1);
    }


    /**
     * Retrieve all music samples associated with a specific user.
     *
     * @param userId The ID of the user/uploader.
     * @return An Iterable list of music samples associated with the user.
     */
    @PostMapping(value = "/samples/retrieve/by_user")
    public Iterable<MusicSampleModel> findAllMusicSamplesByUserId(String userId) {
        return musicSampleRepository.findAllByIdUser(Integer.parseInt(userId));
    }

    /**
     * Retrieve a specific music sample based on user ID and the sample name.
     * It's up for debate whether we want to immediately download the corresponding music sample file as well
     *
     * @param sampleId The ID of the sample.
     * @return The requested music sample, or null if not found.
     */
    @PostMapping(value = "/samples/retrieve")
    public Base64MusicSample findMusicSample(String sampleId) throws JSchException, SftpException, IOException {
        // Set up and start SFTP connection
        ChannelSftp channelSftp = setupJsch();
        channelSftp.connect();

        MusicSampleModel sample = musicSampleRepository.findById(Integer.parseInt(sampleId));

        String remoteFile = "samples/" + sample.getIdMusicSample() + "." + sample.getSampleExtension();
        String localDir = "src/main/resources/localSamples/";

        channelSftp.get(remoteFile, localDir + sample.getIdMusicSample() + "." + sample.getSampleExtension());
        channelSftp.exit();

        Path path = Paths.get(localDir + sample.getIdMusicSample() + "." + sample.getSampleExtension());
        byte[] fileContent = Files.readAllBytes(path);
        String base64EncodedSample = Base64.getEncoder().encodeToString(fileContent);

        Files.deleteIfExists(path);

        return new Base64MusicSample(sample, base64EncodedSample);
    }


    /**
     * Inserts a MusicSample record into the database and uploads the associated file to the SFTP server.
     *
     * @param musicSample The MusicSample to be saved.
     * @return boolean if the upload was successful or not.
     * @throws JSchException If there is a problem connecting to the SFTP server.
     * @throws SftpException If there is a problem with uploading the file to the SFTP server.
     */
    @CrossOrigin(origins = "*")
    @PostMapping(value = "/samples/upload", consumes = {"multipart/form-data"})
    public int uploadSample(
            @RequestPart("musicSample") MusicSampleModel musicSample,
            @RequestPart("file") MultipartFile file
    ) throws JSchException, SftpException, IOException {

        musicSample.setActive(1);

        // Insert music sample data into the database.
        MusicSampleModel savedMusicSample = musicSampleRepository.save(musicSample);

        // Print the id of the sample to make sure it is the latest one
        System.out.println(savedMusicSample.getIdMusicSample());

        // Determine location of the local to be uploaded sample,
        // predefine the target directory on server, add a new name for the sample
        // Determine if the file has the allowed extension type, then start uploading procedure
        if (checkExtension(file.getOriginalFilename())) {
            System.out.println("valid extension");

            // Save extension in variable
            String fileExtension = getFileExtension(file.getOriginalFilename());
            System.out.println(fileExtension);

            // Set up and start SFTP connection
            ChannelSftp channelSftp = setupJsch();
            channelSftp.connect();

            // Put the music sample file with the database corresponding new name inside the target directory (samples)
            channelSftp.put(file.getInputStream(), TARGET_DIR + savedMusicSample.getIdMusicSample() + "." + fileExtension);

            // Close SFTP connection
            channelSftp.exit();

            return savedMusicSample.getIdMusicSample();
        } else {
            System.out.println("invalid extension");
            return -1;
        }
    }

    //  GetMappings
    @GetMapping(value = "/samples/{id}")
    public MusicSampleModel getSample(@PathVariable int id) {
        System.out.println("getSample " + id);
        return musicSampleRepository.findById(id);
    }

    @GetMapping(value = "/samples")
    public List<MusicSampleModel> getAllSamples() {
        System.out.println("getSamples");
        return musicSampleRepository.findAll();
    }

    @GetMapping(value = "/samples/most_played_this_week")
    public MusicSampleModel getThisWeeksMostPlayedSample() {
        System.out.println("getThisWeeksMostPlayedSample");

        LocalDateTime startOfWeek = Date.startOfWeek();
        LocalDateTime endOfWeek = Date.endOfWeek();

        List<MusicSampleModel> results = musicSampleRepository.findMostPlayedInPeriod(startOfWeek, endOfWeek);

        return (results.size() > 0) ? results.get(0) : null;
    }

    @GetMapping(value = "/samples/recommended")
    public List<MusicSampleModel> getRecommendedSamples() {
        System.out.println("getRecommendedSamples");

        // TODO: if logged in: return recommended for user | else: return popular (?)
        // Authentication authentication = SecurityContextHolder.getContext().getAuthentication();


        LocalDateTime startOfMonth = Date.startOfMonth();
        LocalDateTime endOfMonth = Date.endOfMonth();

        List<MusicSampleModel> results = musicSampleRepository.findMostPlayedInPeriod(startOfMonth, endOfMonth);

        return results.subList(0, Math.min(12, results.size()));
    }

    @GetMapping(value = "/samples/recently_played")
    public List<MusicSampleModel> getRecentlyPlayedSamples() {
        System.out.println("getRecentlyPlayedSamples");

        List<MusicSampleModel> results = musicSampleRepository.findRecentlyPlayed();

        return results.subList(0, Math.min(12, results.size()));
    }

    @PostMapping(value = "/link-labels-to-sample")
    public boolean LinkLabelToSample(@RequestBody LinkLabelsToMusicSampleRequest request) {
        System.out.println("linkLabelsToAccount");
        try {

            MusicSampleModel musicSample = musicSampleRepository.findById(request.getMusicSampleId());
            List<LabelModel> labels = labelRepo.findByIds(request.getLabelIds());

            musicSample.getLabels().addAll(labels);

            musicSampleRepository.save(musicSample);

            return true;

        } catch (Exception e) {
            return false;
        }
    }

    @PostMapping(value = "/sample/like")
    public boolean likeSample(int sampleId, int userId) {

        try {
            // Retrieve the MusicSample entity by ID
            MusicSampleModel sample = musicSampleRepository.findById(sampleId);

            if (sample != null) {
                // Create a new MusicSampleLike entity
                MusicSampleLike like = new MusicSampleLike();
                like.setUserId(userId);
                like.setMusicSample(sample);
                like.setDateLiked(LocalDateTime.now());

                // Add the new like to the list of likes
                List<MusicSampleLike> likes = sample.getLikesObj();

                boolean hasUser = false;
                for (MusicSampleLike l : likes) {
                    if (l.getUserId() == userId) {
                        hasUser = true; // Found a match
                        break;
                    }
                }

                if (!hasUser) {
                    likes.add(like);
                    // Save the MusicSample entity along with the new like
                    musicSampleRepository.save(sample);
                }


                return true;
            } else {
                // Handle the case where the MusicSample with the specified ID is not found
                System.out.println("MusicSample not found for ID: " + sampleId);
                return false;
            }

        } catch (Exception e) {
            // Handle exceptions
            System.out.println(e);
            return false;
        }
    }

    @PostMapping(value = "/sample/dislike")
    public boolean dislikeSample(int sampleId, int userId) {
        try {
            MusicSampleModel sample = musicSampleRepository.findById(sampleId);

            if (sample != null) {
                MusicSampleDislike dislike = new MusicSampleDislike();
                dislike.setUserId(userId);
                dislike.setMusicSample(sample);
                dislike.setDateDisliked(LocalDateTime.now());

                List<MusicSampleDislike> dislikes = sample.getDislikesObj();

                boolean hasUser = false;
                for (MusicSampleDislike l : dislikes) {
                    if (l.getUserId() == userId) {
                        hasUser = true; // Found a match
                        break;
                    }
                }

                if (!hasUser) {
                    dislikes.add(dislike);
                    musicSampleRepository.save(sample);
                }

                return true;
            } else {
                return false;
            }

        } catch (Exception e) {
            // Handle exceptions
            System.out.println(e);
            return false;
        }
    }

}
