package IO.Dishes;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "dishes")
@XmlAccessorType(XmlAccessType.FIELD)

public class DishesList {

    @XmlElement(name = "dish")
    private List<Dish> dishes;

    public List<Dish> getDishesList() {
        return dishes;
    }

    public void setDishes(List<Dish> dishes) {
        this.dishes = dishes;
    }
}
