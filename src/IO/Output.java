package IO;

import Restaurant.Reservations.ReservationsList;

import java.beans.XMLEncoder;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class Output {
    public static void save(ReservationsList reservationsList) {
        try {
            FileOutputStream fos = new FileOutputStream(new File("reservations.xml"));
            FileOutputStream fos2 = new FileOutputStream(new File("reservations_history.xml"));

            XMLEncoder encoder = new XMLEncoder(fos);
            XMLEncoder encoder2 = new XMLEncoder(fos2);

            encoder.writeObject(reservationsList.getReservationsList());
            encoder2.writeObject(reservationsList.getHistoryList());

            encoder.close();
            encoder2.close();

            fos.close();
            fos2.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
