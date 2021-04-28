package MessengerService.Model;

import java.util.Date;

public class GroupMessage extends Message {
    private Long groupId;

    public GroupMessage(String message, Date date, Long senderId, Long groupId) {
        super(message, date, senderId);
        this.groupId = groupId;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }
}
