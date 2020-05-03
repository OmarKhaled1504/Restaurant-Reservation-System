package Restaurant.Reservations;

import java.util.ArrayList;
import java.util.List;

public class ReservationsList {
    private List<Reservation> reservationsList = new ArrayList<>();
    private List<Reservation> historyList = new ArrayList<>();

    public List<Reservation> getHistoryList() {
        return historyList;
    }


    public void setHistoryList(List<Reservation> historyList) {
        this.historyList = historyList;
    }

    private long totalRevenue;

    public ReservationsList() {
    }

    public void addReservation(Reservation reservation) {
        reservationsList.add(reservation);
        historyList.add(reservation);
        totalRevenue += reservation.getReceiptAmount();
    }

    public void cancelReservation(Reservation reservation) {
       reservationsList.remove(reservation);
        totalRevenue = (long) (totalRevenue - reservation.getReceiptAmount());

    }


    public List<Reservation> getReservationsList() {
        return reservationsList;
    }

    public void setReservationsList(List<Reservation> reservationsList) {
        this.reservationsList = reservationsList;
    }

    public double getTotalRevenue() {
        return totalRevenue;
    }

    public void setTotalRevenue(long totalRevenue) {
        this.totalRevenue += totalRevenue;
    }
}

