package Restaurant.Employees;

import IO.Users.User;

import java.util.ArrayList;
import java.util.List;

public class WaitersList {
    private List<Waiter> waiters = new ArrayList();

    public WaitersList(List<User> users) {
        for (User user : users) {
            if (user.getRole().equals("Waiter"))
                waiters.add(new Waiter(user.getRole(), user.getName(), user.getUsername(), user.getPassword()));
        }
    }

    public List<Waiter> getWaiters() {
        return waiters;
    }

    public void setWaiters(List<Waiter> waiters) {
        this.waiters = waiters;
    }
}
