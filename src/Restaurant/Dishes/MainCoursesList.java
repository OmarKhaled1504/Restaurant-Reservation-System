package Restaurant.Dishes;

import IO.Dishes.Dish;

import java.util.ArrayList;
import java.util.List;

public class MainCoursesList {
    private List<MainCourse> mainCourses = new ArrayList();

    public MainCoursesList(List<Dish> dishes) {
        for (Dish dish : dishes) {
            if (dish.getType().equals("main_course"))
                mainCourses.add(new MainCourse(dish.getName(), dish.getPrice(), dish.getType(), 0.15));
        }
    }

    public List<MainCourse> getMainCourses() {
        return mainCourses;
    }
}
