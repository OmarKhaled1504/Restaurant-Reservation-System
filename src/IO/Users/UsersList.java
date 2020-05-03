package IO.Users;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "users")
@XmlAccessorType(XmlAccessType.FIELD)
public class UsersList {


    @XmlElement(name = "user")
    private List<User> users;

    public void setUsers(List<User> users) {
        this.users = users;
    }


    public List<User> getUsersList() {
        return users;
    }

}
