package MessengerService.Model;

import java.util.Objects;

public class User {
    private Long id;
    private String userName;

    public User(Long id, String userName) {
        this.id = id;
        this.userName = userName;
    }

    public User(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id.equals(user.id) &&
                userName.equals(user.userName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userName);
    }


}
