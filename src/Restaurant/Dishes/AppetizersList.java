package Restaurant.Dishes;

import IO.Dishes.Dish;

import java.util.ArrayList;
import java.util.List;

public class AppetizersList {
    private List<Appetizer> appetizers = new ArrayList();


    public AppetizersList(List<Dish> dishes) {
        for (Dish dish : dishes) {
            if (dish.getType().equals("appetizer"))
                appetizers.add(new Appetizer(dish.getName(), dish.getPrice(), dish.getType(), 0.1));
        }
    }

    public List<Appetizer> getAppetizers() {
        return appetizers;
    }

    public void setAppetizers(List<Appetizer> appetizers) {
        this.appetizers = appetizers;
    }
}
