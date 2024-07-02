package teamx.musiccollabbackend.domain.User.Profile;

import lombok.Getter;
import teamx.musiccollabbackend.domain.Label.LabelModel;
import teamx.musiccollabbackend.domain.MusicSample.MusicSampleModel;
import teamx.musiccollabbackend.domain.User.UserModel;

@Getter
public class UserProfileModel {

    UserModel user;
    Iterable<MusicSampleModel> samples;

    public UserProfileModel(UserModel user, Iterable<MusicSampleModel> samples) {
        this.user = user;
        this.samples = samples;
    }
}
