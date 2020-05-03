package IO.Tables;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "table")
@XmlAccessorType(XmlAccessType.FIELD)
public class Table {
    @XmlElement(name = "number")
    private int tableNumber;

    @XmlElement(name = "number_of_seats")
    private int numberOfSeats;

    @XmlElement(name = "smoking")
    private boolean smokingArea;

    private boolean availability = true;

    public boolean getAvailability() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }

    public int getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(int tableNumber) {
        this.tableNumber = tableNumber;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public boolean isSmokingArea() {
        return smokingArea;
    }

    public void setSmokingArea(boolean smokingArea) {
        this.smokingArea = smokingArea;
    }
}
