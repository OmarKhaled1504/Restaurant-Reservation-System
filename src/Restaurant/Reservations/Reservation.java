package Restaurant.Reservations;

import IO.Dishes.Dish;
import IO.Tables.Table;
import Restaurant.Customers.Customer;

import java.util.Date;
import java.util.HashMap;

public class Reservation {
    private Table table;
    private HashMap<Dish, Integer> order = new HashMap<>();
    private Customer customer = new Customer();
    private double receiptAmount;
    private Date date;
    private boolean isCooked;
    private boolean isServed;

    public Reservation() {
    }

    public Reservation(Table table, HashMap<Dish, Integer> order, Customer customer, double receiptAmount) {
        this.table = table;
        this.table.setAvailability(false);
        this.order = order;
        this.customer = customer;
        this.receiptAmount = receiptAmount;
        date = new Date();
    }


    public double getReceiptAmount() {
        return receiptAmount;
    }

    public void setReceiptAmount(double receiptAmount) {
        this.receiptAmount = receiptAmount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
    }

    public HashMap<Dish, Integer> getOrder() {
        return order;
    }

    public void setOrder(HashMap<Dish, Integer> order) {
        this.order = order;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public boolean isCooked() {
        return isCooked;
    }

    public void setCooked(boolean cooked) {
        isCooked = cooked;
    }

    public boolean isServed() {
        return isServed;
    }

    public void setServed(boolean served) {
        isServed = served;
    }
}
