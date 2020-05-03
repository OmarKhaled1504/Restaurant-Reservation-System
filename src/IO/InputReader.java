package IO;

import IO.Tables.Table;
import Restaurant.Customers.CustomersList;
import Restaurant.Dishes.AppetizersList;
import Restaurant.Dishes.DesertsList;
import Restaurant.Dishes.MainCoursesList;
import Restaurant.Employees.CooksList;
import Restaurant.Employees.ManagersList;
import Restaurant.Employees.WaitersList;
import Restaurant.Reservations.Reservation;
import Restaurant.Restaurant;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.beans.XMLDecoder;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class InputReader {

    public static Restaurant readInput() throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(Restaurant.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        Restaurant restaurant = (Restaurant) unmarshaller.unmarshal(new File("input.xml"));
        List<Reservation> reservationsList = new ArrayList<>();
        List<Reservation> historyList = new ArrayList<>();

        try {
            FileInputStream fis = new FileInputStream(new File("reservations.xml"));
            FileInputStream fis2 = new FileInputStream(new File("reservations_history.xml"));
            XMLDecoder decoder = new XMLDecoder(fis);
            XMLDecoder decoder2 = new XMLDecoder(fis2);
            reservationsList = (List<Reservation>) decoder.readObject();

            historyList = (List<Reservation>) decoder2.readObject();
            decoder.close();
            fis.close();
            decoder2.close();
            fis2.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        restaurant.setCustomersList(new CustomersList(restaurant.getUsersList().getUsersList()));
        restaurant.setManagersList(new ManagersList(restaurant.getUsersList().getUsersList()));
        restaurant.setCooksList(new CooksList(restaurant.getUsersList().getUsersList()));
        restaurant.setWaitersList(new WaitersList(restaurant.getUsersList().getUsersList()));
        restaurant.setAppetizersList(new AppetizersList(restaurant.getDishesList().getDishesList()));
        restaurant.setMainCoursesList(new MainCoursesList(restaurant.getDishesList().getDishesList()));
        restaurant.setDesertsList(new DesertsList(restaurant.getDishesList().getDishesList()));
        restaurant.setReservationsList(reservationsList, historyList);

        for (Reservation reservation : restaurant.getReservationsList().getReservationsList()) {
            for (Table table : restaurant.getTablesList().getTablesList()) {
                if (reservation.getTable().getTableNumber() == table.getTableNumber())
                    table.setAvailability(false);
            }
            restaurant.getReservationsList().setTotalRevenue((long) reservation.getReceiptAmount());
        }
        for (Reservation reservation : restaurant.getReservationsList().getReservationsList()) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(reservation.getDate());
            calendar.add(Calendar.DATE, 1);
            if (new Date().after(calendar.getTime())) {
                restaurant.getReservationsList().cancelReservation(reservation);

            }
        }
        return restaurant;

    }
}
