package GUI;

import IO.Dishes.Dish;
import IO.Output;
import IO.Tables.Table;
import Restaurant.Customers.Customer;
import Restaurant.Dishes.Appetizer;
import Restaurant.Dishes.Desert;
import Restaurant.Dishes.MainCourse;
import Restaurant.Reservations.Reservation;
import Restaurant.Restaurant;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class CustomerScene2 {
    Scene scene;
    Stage stage;
    CustomerScene3 customerScene3;

    private Restaurant restaurant;
    private Table table;
    private Customer customer;
    private double totalAmount;
    private double totalTaxes;
    private int aQuantity;
    private int mcQuantity;
    private int dQuantity;

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public Scene getScene() {
        return scene;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setCustomerScene3(CustomerScene3 customerScene3) {
        this.customerScene3 = customerScene3;
    }

    public void setTable(Table table) {
        this.table = table;
    }

    private HashMap<Dish, Integer> order = new HashMap<>();

    public CustomerScene2(Stage stage) {
        this.stage = stage;
    }

    public String receiptBuilder(HashMap<Dish, Integer> order, Customer customer, Table table) {

        StringBuilder stringBuilder = new StringBuilder();
        Date date = new Date();
        stringBuilder.append("  \t\t\tRECEIPT\n  \t\t\t---------\n");
        stringBuilder.append("Customer Name: " + customer.getName() + "\n");
        stringBuilder.append("Table #: " + table.getTableNumber() + "\n");
        stringBuilder.append("Reservation For: " + table.getNumberOfSeats() + "\n");
        if (table.isSmokingArea())
            stringBuilder.append("Table Type: Smoking.\n");
        else
            stringBuilder.append("Table Type: Non-Smoking.\n");
        stringBuilder.append("Date & Time: " + new SimpleDateFormat("h:mm a - EEE, d MMM yyyy").format(date) + "\n");
        stringBuilder.append("--------------------------------------------\nOrder: -\n");

        totalTaxes = 0;
        totalAmount = 0;
        for (Map.Entry<Dish, Integer> entry : order.entrySet()) {
            if (entry.getValue() > 0) {
                totalTaxes += entry.getValue() * (entry.getKey().getPrice() * entry.getKey().getTaxRate());
                totalAmount += entry.getValue() * (entry.getKey().getPrice() + entry.getKey().getPrice() * entry.getKey().getTaxRate());
                stringBuilder.append(entry.getKey().getName() + "    x" + entry.getValue() + "  " + entry.getKey().getPrice() + " EGP each.\n");
            }
        }

        stringBuilder.append("--------------------------------------------\nTaxes: " + totalTaxes + " EGP.\n" + "Total: " + totalAmount + " EGP.");

        return stringBuilder.toString();
    }


    public void prepareScene() {
        aQuantity = 0;
        mcQuantity = 0;
        dQuantity = 0;
        order = new HashMap<>();

        for (Appetizer appetizer : restaurant.getAppetizersList().getAppetizers())
            order.put(appetizer, 0);
        for (MainCourse mainCourse : restaurant.getMainCoursesList().getMainCourses())
            order.put(mainCourse, 0);
        for (Desert desert : restaurant.getDesertsList().getDeserts())
            order.put(desert, 0);

        BackgroundImage backgroundImage = new BackgroundImage(new Image("https://i.ibb.co/x1LFBNq/kk.jpg", 550, 450, false, true),
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);


        Image image = new Image("https://i.ibb.co/hDmqzmm/menu.png");
        ImageView imageView = new ImageView();
        imageView.setImage(image);
        imageView.setFitWidth(50);
        imageView.setFitHeight(50);

        Label chooseDishLabel = new Label("Choose Dishes");
        Label mainCoursesLAbel = new Label("Main Dishes");
        Label appetizersLabel = new Label("Appetizers");
        Label desertLabel = new Label("Desserts");

        Line line1 = new Line(0, 0, 90, 0);
        Line line2 = new Line(0, 0, 80, 0);
        Line line3 = new Line(0, 0, 80, 0);
        Line line4 = new Line(0, 0, 80, 0);

        TextArea receiptArea = new TextArea(receiptBuilder(order, customer, table));
        receiptArea.setEditable(false);
        receiptArea.setPrefSize(300, 400);

        ChoiceBox<String> mainCourses = new ChoiceBox<>();
        for (Dish dish : restaurant.getMainCoursesList().getMainCourses()) {
            mainCourses.getItems().add(dish.getName() + "  " + dish.getPrice() + " EGP");
            mainCourses.setValue(dish.getName() + "  " + dish.getPrice() + " EGP");
        }

        ChoiceBox<String> appetizers = new ChoiceBox<>();
        for (Dish dish : restaurant.getAppetizersList().getAppetizers()) {
            appetizers.getItems().add(dish.getName() + "  " + dish.getPrice() + " EGP");
            appetizers.setValue(dish.getName() + "  " + dish.getPrice() + " EGP");
        }

        ChoiceBox<String> deserts = new ChoiceBox<>();
        for (Dish dish : restaurant.getDesertsList().getDeserts()) {
            deserts.getItems().add(dish.getName() + "  " + dish.getPrice() + " EGP");
            deserts.setValue(dish.getName() + "  " + dish.getPrice() + " EGP");
        }

        Button addMainCourses = new Button("Add");
        Button addAppetizers = new Button("Add");
        Button addDeserts = new Button("Add");
        Button checkoutBtn = new Button("Checkout");

        ChoiceBox<Integer> mainCoursesQuantityCB = new ChoiceBox<>();
        ChoiceBox<Integer> appetizersQuantityCB = new ChoiceBox<>();
        ChoiceBox<Integer> desertsQuantityCB = new ChoiceBox<>();

        mainCoursesQuantityCB.getItems().addAll(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        mainCoursesQuantityCB.setValue(1);

        appetizersQuantityCB.getItems().addAll(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        appetizersQuantityCB.setValue(1);

        desertsQuantityCB.getItems().addAll(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        desertsQuantityCB.setValue(1);




        addMainCourses.setOnAction(e ->
                {
                    mcQuantity += mainCoursesQuantityCB.getValue();
                    for (MainCourse mainCourse : restaurant.getMainCoursesList().getMainCourses()) {
                        if (mainCourses.getSelectionModel().getSelectedItem().contains(mainCourse.getName())) {
                            order.replace(mainCourse, mcQuantity);
                            break;
                        }
                    }
                    receiptArea.setText(receiptBuilder(order, customer, table));
                }
        );

        addAppetizers.setOnAction(e ->
                {
                    aQuantity += appetizersQuantityCB.getValue();
                    for (Appetizer appetizer : restaurant.getAppetizersList().getAppetizers()) {
                        if (appetizers.getSelectionModel().getSelectedItem().contains(appetizer.getName())) {
                            order.replace(appetizer, aQuantity);
                            break;
                        }
                    }
                    receiptArea.setText(receiptBuilder(order, customer, table));
                }
        );

        addDeserts.setOnAction(e ->
                {
                    dQuantity += desertsQuantityCB.getValue();
                    for (Desert desert : restaurant.getDesertsList().getDeserts()) {
                        if (deserts.getSelectionModel().getSelectedItem().contains(desert.getName())) {
                            order.replace(desert, dQuantity);
                            break;
                        }
                    }
                    receiptArea.setText(receiptBuilder(order, customer, table));
                }
        );

        checkoutBtn.setOnAction(e -> {
            restaurant.getReservationsList().addReservation(new Reservation(table, order, customer, totalAmount));
            Output.save(restaurant.getReservationsList());
            customerScene3.prepareScene();
            stage.setScene(customerScene3.getScene());
        });

        HBox mainCoursesHBox = new HBox(mainCourses);
        HBox addingMainCoursesHBox = new HBox(mainCoursesQuantityCB, addMainCourses);
        HBox appetizersHBox = new HBox(appetizers);
        HBox addingAppetizersHBox = new HBox(appetizersQuantityCB, addAppetizers);
        HBox desertsHBox = new HBox(deserts);
        HBox addingDesertsHBox = new HBox(desertsQuantityCB, addDeserts);
        VBox vBox = new VBox(imageView, chooseDishLabel, line1, appetizersLabel, line2, appetizersHBox, addingAppetizersHBox, mainCoursesLAbel, line3, mainCoursesHBox, addingMainCoursesHBox, desertLabel, line4, desertsHBox, addingDesertsHBox);
        vBox.setSpacing(8);
        VBox receiptVBox = new VBox(receiptArea, checkoutBtn);
        HBox mainHBox = new HBox(vBox, receiptVBox);
        mainHBox.setBackground(new Background(backgroundImage));
        mainHBox.setSpacing(10);

        scene = new Scene(mainHBox, 550, 450);


    }
}
