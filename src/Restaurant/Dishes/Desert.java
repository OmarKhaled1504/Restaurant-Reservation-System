package Restaurant.Dishes;

import IO.Dishes.Dish;

public class Desert extends Dish {
    public Desert() {
    }

    public Desert(String name, int price, String type, double taxRate) {
        super(name, price, type, taxRate);
    }
}
