package Restaurant.Employees;

import IO.Users.User;

import java.util.ArrayList;
import java.util.List;

public class CooksList {
    private List<Cook> cooks = new ArrayList<>();

    public CooksList(List<User> users) {
        for (User user : users) {
            if (user.getRole().equals("Cooker"))
                cooks.add(new Cook(user.getRole(), user.getName(), user.getUsername(), user.getPassword()));
        }
    }

    public List<Cook> getCooks() {
        return cooks;
    }

    public void setCooks(List<Cook> cooks) {
        this.cooks = cooks;
    }
}
