package GUI;

import IO.Output;
import Restaurant.Reservations.Reservation;
import Restaurant.Restaurant;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.text.SimpleDateFormat;

public class WaiterScene {
    Stage stage;
    Scene scene;
    LoginScene loginScene;
    Restaurant restaurant;

    public WaiterScene(Stage stage) {
        this.stage = stage;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public Scene getScene() {
        return scene;
    }

    public void setLoginScene(LoginScene loginScene) {
        this.loginScene = loginScene;
    }

    public String orderBuilder() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Reservation reservation : restaurant.getReservationsList().getReservationsList()) {
            System.out.println(reservation);
            System.out.println(restaurant);
            stringBuilder.append("  \t\t\torder\n  \t\t\t---------\n");
            stringBuilder.append("Customer Name: " + reservation.getCustomer().getName() + "\n");
            stringBuilder.append("Table #: " + reservation.getTable().getTableNumber() + "\n");
            stringBuilder.append("Date & Time: " + new SimpleDateFormat("h:mm a - EEE, d MMM yyyy").format(reservation.getDate()) + "\n");
            if (reservation.isServed())
                stringBuilder.append("Served");
            else
                stringBuilder.append("Not Served");

        }
        return stringBuilder.toString();
    }

    public void prepareScene() {

        BackgroundImage backgroundImage = new BackgroundImage(new Image("https://i.ibb.co/4sXDSgM/WALLPAPER.png", 500, 400, false, true),
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);

        Image image = new Image("https://i.ibb.co/BnTQNRp/waiter.png");
        ImageView imageView = new ImageView();
        imageView.setImage(image);
        imageView.setFitWidth(50);
        imageView.setFitHeight(50);

        Button exitBtn = new Button("Exit");
        Button signOutBtn = new Button("Sign Out");
        Button isServedBtn = new Button("Mark As Done");

        TextArea orders = new TextArea(orderBuilder());
        orders.setEditable(false);
        orders.setPrefSize(50, 400);

        ComboBox<String> ComboBoxCooking = new ComboBox<>();
        for (Reservation reservation : restaurant.getReservationsList().getReservationsList()) {
            if (!reservation.isServed()) {
                ComboBoxCooking.getItems().add("Name: " + reservation.getCustomer().getName() + "\tTable #: " + reservation.getTable().getTableNumber());
                ComboBoxCooking.setValue("Name: " + reservation.getCustomer().getName() + "\tTable #: " + reservation.getTable().getTableNumber());
            }
        }
        if (ComboBoxCooking.getItems().isEmpty()) {
            ComboBoxCooking.setPromptText("NO RESERVED TABLES");
            ComboBoxCooking.setEditable(false);
        }

        isServedBtn.setOnAction(e ->
        {
            for (Reservation reservation : restaurant.getReservationsList().getReservationsList()) {
                if (ComboBoxCooking.getValue().equals("Name: " + reservation.getCustomer().getName() + "\tTable #: " + reservation.getTable().getTableNumber())) {
                    reservation.setServed(true);
                    orders.setText(orderBuilder());
                }
            }
            ComboBoxCooking.getItems().clear();
            for (Reservation reservation : restaurant.getReservationsList().getReservationsList()) {
                if (!reservation.isServed()) {
                    ComboBoxCooking.getItems().add("Name: " + reservation.getCustomer().getName() + "\tTable #: " + reservation.getTable().getTableNumber());
                    ComboBoxCooking.setValue("Name: " + reservation.getCustomer().getName() + "\tTable #: " + reservation.getTable().getTableNumber());
                }
            }
            Output.save(restaurant.getReservationsList());
            if (ComboBoxCooking.getItems().isEmpty()) {
                ComboBoxCooking.setPromptText("NO UNSERVED ORDERS");
                ComboBoxCooking.setEditable(false);
            }
        });

        signOutBtn.setOnAction(e ->
        {
            loginScene.prepareScene();
            stage.setScene(loginScene.getScene());
        });

        exitBtn.setOnAction(e ->
                Platform.exit()
        );

        HBox hBox = new HBox(signOutBtn, exitBtn);
        HBox hBox2 = new HBox(ComboBoxCooking, isServedBtn);
        VBox vBox = new VBox(imageView, hBox, hBox2, orders);
        vBox.setBackground(new Background(backgroundImage));
        vBox.setAlignment(Pos.TOP_CENTER);
        vBox.setSpacing(10);
        scene = new Scene(vBox, 500, 400);

    }
}
