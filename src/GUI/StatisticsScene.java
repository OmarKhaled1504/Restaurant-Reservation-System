package GUI;

import IO.Dishes.Dish;
import Restaurant.Dishes.Appetizer;
import Restaurant.Dishes.Desert;
import Restaurant.Dishes.MainCourse;
import Restaurant.Reservations.Reservation;
import Restaurant.Restaurant;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.List;
import java.util.Map;

public class StatisticsScene {
    private Restaurant restaurant;
    private int appetizersCount;
    private int mainCoursesCount;
    private int desertsCount;
    Scene scene;
    Stage stage;
    ManagerScene managerScene;


    public StatisticsScene(Stage stage) {
        this.stage = stage;
    }

    public void setManagerScene(ManagerScene managerScene) {
        this.managerScene = managerScene;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public Scene getScene() {
        return scene;
    }

    public String dishStatistics(List<Reservation> reservations) {
        appetizersCount = 0;
        mainCoursesCount = 0;
        desertsCount = 0;
        StringBuilder stringBuilder = new StringBuilder();
        for (Reservation reservation : reservations) {
            for (Map.Entry<Dish, Integer> entry : reservation.getOrder().entrySet()) {
                for (Appetizer appetizer : restaurant.getAppetizersList().getAppetizers()) {
                    if (entry.getKey().getName().equals(appetizer.getName())) {
                        appetizersCount += entry.getValue();
                    }
                }
                for (MainCourse mainCourse : restaurant.getMainCoursesList().getMainCourses()) {
                    if (entry.getKey().getName().equals(mainCourse.getName())) {
                        mainCoursesCount += entry.getValue();
                    }
                }
                for (Desert desert : restaurant.getDesertsList().getDeserts()) {
                    if (entry.getKey().getName().equals(desert.getName())) {
                        desertsCount += entry.getValue();
                    }
                }
            }
        }

        stringBuilder.append("Appetizers Ordered: " + appetizersCount + "\n");


        stringBuilder.append("Main Courses Ordered: " + mainCoursesCount + "\n");

        stringBuilder.append("Desserts Ordered: " + desertsCount + "\n");

        return stringBuilder.toString();
    }

    public void prepareScene() {
        BackgroundImage backgroundImage = new BackgroundImage(new Image("https://i.ibb.co/yQqm9b7/12.jpg", 500, 500, false, true),
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);


        Label todaysStatistics = new Label("Today's Statistics: -\n" + dishStatistics(restaurant.getReservationsList().getReservationsList()));

        Label allStatistics = new Label("All Statistics: -\n" + dishStatistics(restaurant.getReservationsList().getHistoryList()));

        Button backBtn = new Button("Back");
        backBtn.setOnAction(e -> {
            stage.setScene(managerScene.getScene());
        });

        VBox vBox = new VBox(todaysStatistics, allStatistics, backBtn);
        vBox.setAlignment(Pos.TOP_CENTER);
        vBox.setSpacing(8);
        vBox.setBackground(new Background(backgroundImage));
        scene = new Scene(vBox, 400, 300);
    }
}
