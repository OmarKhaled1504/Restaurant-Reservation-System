package GUI;

import IO.Dishes.Dish;
import IO.Output;
import IO.Tables.Table;
import Restaurant.Reservations.Reservation;
import Restaurant.Restaurant;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.text.SimpleDateFormat;
import java.util.Map;


public class ManagerScene {
    Stage stage;
    Scene scene;
    LoginScene loginScene;
    StatisticsScene statisticsScene;
    private Restaurant restaurant;

    public ManagerScene(Stage stage) {
        this.stage = stage;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public Scene getScene() {
        return scene;
    }

    public void setStatisticsScene(StatisticsScene statisticsScene) {
        this.statisticsScene = statisticsScene;
    }

    public void setLoginScene(LoginScene loginScene) {
        this.loginScene = loginScene;
    }

    public String orderBuilder() {
        StringBuilder stringBuilder = new StringBuilder();
        int i = 1;

        for (Reservation reservation : restaurant.getReservationsList().getReservationsList()) {
            stringBuilder.append("  \t\t\tOrder (" + i++ + ")\n  \t\t\t---------\n");
            stringBuilder.append("Customer Name: " + reservation.getCustomer().getName() + "\n");
            stringBuilder.append("Table #: " + reservation.getTable().getTableNumber() + "\n");
            stringBuilder.append("Reservation For: " + reservation.getTable().getNumberOfSeats() + "\n");
            if (reservation.getTable().isSmokingArea())
                stringBuilder.append("Table Type: Smoking.\n");
            else
                stringBuilder.append("Table Type: Non-Smoking.\n");
            stringBuilder.append("Date & Time: " + new SimpleDateFormat("h:mm a - EEE, d MMM yyyy").format(reservation.getDate()) + "\n");
            stringBuilder.append("-----------------------------------\n");
            if (reservation.isCooked())
                stringBuilder.append("Cooked\n");
            else
                stringBuilder.append("Not Cooked\n");
            if (reservation.isServed())
                stringBuilder.append("Served\n");
            else
                stringBuilder.append("Not Served\n");
            stringBuilder.append("-----------------------------------\n");
            stringBuilder.append("Order: -\n");

            for (Map.Entry<Dish, Integer> entry : reservation.getOrder().entrySet()) {
                if (entry.getValue() > 0) {
                    stringBuilder.append(entry.getKey().getName() + "    x" + entry.getValue() + "\n");
                }
            }
            stringBuilder.append("Total :" + reservation.getReceiptAmount() + "\n");
        }
        return stringBuilder.toString();
    }

    public void prepareScene() {
        BackgroundImage backgroundImage = new BackgroundImage(new Image("https://i.ibb.co/4sXDSgM/WALLPAPER.png", 500, 400, false, true),
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);

        Image image = new Image("https://i.ibb.co/dDj0Dqv/MANAGER.png");
        ImageView imageView = new ImageView();
        imageView.setImage(image);
        imageView.setFitWidth(100);
        imageView.setFitHeight(50);

        Label noOfReservationsLabel = new Label("Reservations Today: " + restaurant.getReservationsList().getReservationsList().size() + " Reservation(s).");

        Label revenueLabel = new Label("Today's Earnings: " + restaurant.getReservationsList().getTotalRevenue() + " EGP.");

        TextArea orders = new TextArea(orderBuilder());
        orders.setEditable(false);
        orders.setPrefSize(300, 400);

        Button exitBtn = new Button("Exit");
        Button signOutBtn = new Button("Sign Out");
        Button cancelBtn = new Button("Delete Reservation");
        Button stats = new Button("Statistics");

        ComboBox<String> reservtionsComboBox = new ComboBox<>();
        for (Reservation reservation : restaurant.getReservationsList().getReservationsList()) {
            reservtionsComboBox.getItems().add("Name: " + reservation.getCustomer().getName() + "\tTable #: " + reservation.getTable().getTableNumber());
            reservtionsComboBox.setValue("Name: " + reservation.getCustomer().getName() + "\tTable #: " + reservation.getTable().getTableNumber());
        }
        if (reservtionsComboBox.getItems().isEmpty()) {
            reservtionsComboBox.setPromptText("NO RESERVED TABLES");
            reservtionsComboBox.setEditable(false);
        }

        cancelBtn.setOnAction(e ->
        {
            for (Reservation reservation : restaurant.getReservationsList().getReservationsList()) {
                if (reservtionsComboBox.getValue().equals("Name: " + reservation.getCustomer().getName() + "\tTable #: " + reservation.getTable().getTableNumber())) {
                    for (Table table : restaurant.getTablesList().getTablesList()) {
                        if (table.getTableNumber() == reservation.getTable().getTableNumber()) {
                            table.setAvailability(true);
                        }
                    }
                    restaurant.getReservationsList().cancelReservation(reservation);
                    orders.setText(orderBuilder());
                    Output.save(restaurant.getReservationsList());
                    reservtionsComboBox.getItems().clear();
                    for (Reservation reservation1 : restaurant.getReservationsList().getReservationsList()) {
                        reservtionsComboBox.getItems().add("Name: " + reservation1.getCustomer().getName() + "\tTable #: " + reservation1.getTable().getTableNumber());
                        reservtionsComboBox.setValue("Name: " + reservation1.getCustomer().getName() + "\tTable #: " + reservation1.getTable().getTableNumber());
                    }
                    if (reservtionsComboBox.getItems().isEmpty()) {
                        reservtionsComboBox.setPromptText("NO RESERVED TABLES");
                        reservtionsComboBox.setEditable(false);
                    }

                    break;

                }
            }

            revenueLabel.setText("Today's Earnings: " + restaurant.getReservationsList().getTotalRevenue() + " EGP.");
            noOfReservationsLabel.setText("Reservations Today: " + restaurant.getReservationsList().getReservationsList().size() + " Reservation(s).");
        });

        signOutBtn.setOnAction(e ->
        {
            loginScene.prepareScene();
            stage.setScene(loginScene.getScene());
        });

        exitBtn.setOnAction(e ->
                Platform.exit()
        );

        stats.setOnAction(e -> {
                    statisticsScene.setRestaurant(restaurant);
                    statisticsScene.prepareScene();
                    stage.setScene(statisticsScene.getScene());
                }
        );

        HBox hBox1 = new HBox(reservtionsComboBox, cancelBtn);
        HBox hBox2 = new HBox(hBox1, stats);
        hBox2.setSpacing(10);
        VBox vBox1 = new VBox(signOutBtn, exitBtn);
        HBox hBox3 = new HBox(orders, vBox1);
        VBox vBox2 = new VBox(imageView, noOfReservationsLabel, revenueLabel, hBox2, hBox3);
        vBox2.setAlignment(Pos.TOP_CENTER);
        vBox2.setSpacing(10);
        vBox2.setBackground(new Background(backgroundImage));
        scene = new Scene(vBox2, 500, 400);

    }
}
