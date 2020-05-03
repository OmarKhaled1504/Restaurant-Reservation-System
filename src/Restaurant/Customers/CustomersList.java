package Restaurant.Customers;

import IO.Users.User;

import java.util.ArrayList;
import java.util.List;

public class CustomersList {
    private List<Customer> customers = new ArrayList();

    public CustomersList(List<User> users) {
        for (User user : users) {
            if (user.getRole().equals("Client"))
                customers.add(new Customer(user.getRole(), user.getName(), user.getUsername(), user.getPassword()));
        }
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }
}
