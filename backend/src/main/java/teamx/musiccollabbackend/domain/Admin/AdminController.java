package teamx.musiccollabbackend.domain.Admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import teamx.musiccollabbackend.domain.MusicSample.MusicSampleRepository;
import teamx.musiccollabbackend.domain.User.IUserRepository;
import teamx.musiccollabbackend.domain.dto.sample.MusicSampleWithReports;
import teamx.musiccollabbackend.domain.dto.user.UserWithReports;

import java.util.List;

@RestController
public class AdminController{

    @Autowired
    IUserRepository userRepo;

    @Autowired
    MusicSampleRepository sampleRepo;

    @GetMapping("/reported-samples")
    public List<MusicSampleWithReports> getReportedSamples() {
        return sampleRepo.findReported();
    }

    @GetMapping("/reported-users")
    public List<UserWithReports> getReportedUsers(){
        return userRepo.findReported();
    }

    @PutMapping("/disable-user/{userId}")
    public void disableUser(@PathVariable int userId) {
        userRepo.setActiveStatus(userId, 0);
    }

    @PutMapping("/enable-user/{userId}")
    public void enableUser(@PathVariable int userId) {
        userRepo.setActiveStatus(userId, 1);
    }

    @PutMapping("/disable-sample/{sampleId}")
    public void disableSample(@PathVariable int sampleId) {
        sampleRepo.setActiveStatus(sampleId, 0);
    }

    @PutMapping("/enable-sample/{sampleId}")
    public void enableSample(@PathVariable int sampleId) {
        sampleRepo.setActiveStatus(sampleId, 1);
    }

}
