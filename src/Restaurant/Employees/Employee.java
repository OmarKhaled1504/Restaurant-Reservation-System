package Restaurant.Employees;

import IO.Users.User;


abstract class Employee extends User {
    public Employee(String role, String name, String username, String password) {
        super(role, name, username, password);
    }
}
