package Restaurant;


import IO.Dishes.DishesList;
import IO.Tables.TablesList;
import IO.Users.UsersList;
import Restaurant.Customers.CustomersList;
import Restaurant.Dishes.AppetizersList;
import Restaurant.Dishes.DesertsList;
import Restaurant.Dishes.MainCoursesList;
import Restaurant.Employees.CooksList;
import Restaurant.Employees.ManagersList;
import Restaurant.Employees.WaitersList;
import Restaurant.Reservations.Reservation;
import Restaurant.Reservations.ReservationsList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "restaurant")
@XmlAccessorType(XmlAccessType.FIELD)
public class Restaurant {
    @XmlElement(name = "users")
    private UsersList usersList = null;
    @XmlElement(name = "tables")
    private TablesList tablesList = null;
    @XmlElement(name = "dishes")
    private DishesList dishesList = null;


    private ReservationsList reservationsList = new ReservationsList();
    private CustomersList customersList;
    private CooksList cooksList;
    private WaitersList waitersList;
    private ManagersList managersList;
    private AppetizersList appetizersList;
    private MainCoursesList mainCoursesList;
    private DesertsList desertsList;

    public void setReservationsList(List<Reservation> rList, List<Reservation> historyList) {
        reservationsList.setReservationsList(rList);
        reservationsList.setHistoryList(historyList);
    }

    public UsersList getUsersList() {
        return usersList;
    }

    public CustomersList getCustomersList() {
        return customersList;
    }

    public void setCustomersList(CustomersList customersList) {
        this.customersList = customersList;
    }

    public CooksList getCooksList() {
        return cooksList;
    }

    public void setCooksList(CooksList cooksList) {
        this.cooksList = cooksList;
    }

    public WaitersList getWaitersList() {
        return waitersList;
    }

    public void setWaitersList(WaitersList waitersList) {
        this.waitersList = waitersList;
    }

    public ManagersList getManagersList() {
        return managersList;
    }

    public void setManagersList(ManagersList managersList) {
        this.managersList = managersList;
    }

    public AppetizersList getAppetizersList() {
        return appetizersList;
    }

    public void setAppetizersList(AppetizersList appetizersList) {
        this.appetizersList = appetizersList;
    }

    public MainCoursesList getMainCoursesList() {
        return mainCoursesList;
    }

    public void setMainCoursesList(MainCoursesList mainCoursesList) {
        this.mainCoursesList = mainCoursesList;
    }

    public DesertsList getDesertsList() {
        return desertsList;
    }

    public void setDesertsList(DesertsList desertsList) {
        this.desertsList = desertsList;
    }

    public ReservationsList getReservationsList() {
        return reservationsList;
    }

    public TablesList getTablesList() {
        return tablesList;
    }


    public DishesList getDishesList() {
        return dishesList;
    }

}
