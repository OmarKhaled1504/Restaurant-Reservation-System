package Restaurant.Dishes;

import IO.Dishes.Dish;

import java.util.ArrayList;
import java.util.List;

public class DesertsList {
    private List<Desert> deserts = new ArrayList();

    public DesertsList(List<Dish> dishes) {
        for (Dish dish : dishes) {
            if (dish.getType().equals("desert"))
                deserts.add(new Desert(dish.getName(), dish.getPrice(), dish.getType(), 0.2));
        }
    }

    public List<Desert> getDeserts() {
        return deserts;
    }

    public void setDeserts(List<Desert> deserts) {
        this.deserts = deserts;
    }
}
