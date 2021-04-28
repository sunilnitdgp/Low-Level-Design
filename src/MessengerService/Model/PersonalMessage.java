package MessengerService.Model;

import java.util.Date;

public class PersonalMessage extends Message{
    private Long receiverId;

    public PersonalMessage(String message, Date date, Long senderId, Long receiverId) {
        super(message, date, senderId);
        this.receiverId = receiverId;
    }

    public Long getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(Long receiverId) {
        this.receiverId = receiverId;
    }
}
