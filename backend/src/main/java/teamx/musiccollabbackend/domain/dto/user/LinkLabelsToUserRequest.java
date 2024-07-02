package teamx.musiccollabbackend.domain.dto.user;

import java.util.List;

public class LinkLabelsToUserRequest {

    // Fields

    private List<Integer> labelIds;
    private int userId;

    // Getters

    public List<Integer> getLabelIds() {
        return labelIds;
    }

    public int getUserId() {
        return userId;
    }

    // Setters

    public void setLabelIds(List<Integer> labelIds) {
        this.labelIds = labelIds;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

}
