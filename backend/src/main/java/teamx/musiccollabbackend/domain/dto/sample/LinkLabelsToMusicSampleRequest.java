package teamx.musiccollabbackend.domain.dto.sample;

import java.util.List;

public class LinkLabelsToMusicSampleRequest {

    // Fields

    private List<Integer> labelIds;
    private int musicSampleId;

    // Getters

    public List<Integer> getLabelIds() {
        return labelIds;
    }

    public int getMusicSampleId() {
        return musicSampleId;
    }

    // Setters

    public void setLabelIds(List<Integer> labelIds) {
        this.labelIds = labelIds;
    }

    public void setMusicSampleId(int musicSampleId) {
        this.musicSampleId = musicSampleId;
    }

}
