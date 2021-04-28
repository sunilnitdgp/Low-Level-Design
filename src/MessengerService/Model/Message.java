package MessengerService.Model;

import java.util.Date;
import java.util.Objects;

public class Message {
    private String message;

    @Override
    public String toString() {
        return "Message{" +
                "message='" + message + '\'' +
                ", date=" + date +
                ", senderId=" + senderId +
                '}';
    }

    private Date date;
    private Long senderId;

    public Message(String message, Date date, Long senderId) {
        this.message = message;
        this.date = date;
        this.senderId = senderId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message1 = (Message) o;
        return message.equals(message1.message) &&
                date.equals(message1.date) &&
                senderId.equals(message1.senderId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(message, date, senderId);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Long getSenderId() {
        return senderId;
    }

    public void setSenderId(Long senderId) {
        this.senderId = senderId;
    }
}
