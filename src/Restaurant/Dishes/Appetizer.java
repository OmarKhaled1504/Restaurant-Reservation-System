package Restaurant.Dishes;

import IO.Dishes.Dish;

public class Appetizer extends Dish {
    public Appetizer() {
    }

    public Appetizer(String name, int price, String type, double taxRate) {
        super(name, price, type, taxRate);
    }
}
