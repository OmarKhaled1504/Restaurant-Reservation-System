package Restaurant.Employees;

import IO.Users.User;

import java.util.ArrayList;
import java.util.List;

public class ManagersList {
    private List<Manager> managers = new ArrayList();

    public ManagersList(List<User> users) {
        for (User user : users) {
            if (user.getRole().equals("Manager"))
                managers.add(new Manager(user.getRole(), user.getName(), user.getUsername(), user.getPassword()));
        }
    }

    public List<Manager> getManagers() {
        return managers;
    }

    public void setManagers(List<Manager> managers) {
        this.managers = managers;
    }
}
