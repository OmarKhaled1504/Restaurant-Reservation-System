package GUI;

import IO.Dishes.Dish;
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
import java.util.Map;

public class CookerScene {
    Stage stage;
    Scene scene;
    LoginScene loginScene;
    private Restaurant restaurant;

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public CookerScene(Stage stage) {
        this.stage = stage;
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
            stringBuilder.append("  \t\t\torder\n  \t\t\t---------\n");
            stringBuilder.append("Table #: " + reservation.getTable().getTableNumber() + "\n");
            stringBuilder.append("Date & Time: " + new SimpleDateFormat("h:mm a - EEE, d MMM yyyy").format(reservation.getDate()) + "\n");
            stringBuilder.append("Order: -\n");

            for (Map.Entry<Dish, Integer> entry : reservation.getOrder().entrySet()) {
                if (entry.getValue() > 0) {
                    stringBuilder.append(entry.getKey().getName() + "    x" + entry.getValue() + "\n");
                }
            }
            if (reservation.isCooked())
                stringBuilder.append("Cooked");
            else
                stringBuilder.append("Not Cooked");
        }
        return stringBuilder.toString();
    }

    public void prepareScene() {

        BackgroundImage backgroundImage = new BackgroundImage(new Image("https://i.ibb.co/4sXDSgM/WALLPAPER.png", 500, 400, false, true),
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);

        Image image = new Image("https://i.ibb.co/GtrGJ3b/cheff-hat.png");
        ImageView imageView = new ImageView();
        imageView.setImage(image);
        imageView.setFitWidth(50);
        imageView.setFitHeight(50);


        TextArea ordersArea = new TextArea(orderBuilder());
        ordersArea.setEditable(false);
        ordersArea.setPrefSize(150, 400);

        Button exitBtn = new Button("Exit");
        Button signOutBtn = new Button("Sign Out");
        Button isCookedBtn = new Button("Mark As Done");

        ComboBox<String> ComboBoxServing = new ComboBox<>();

        for (Reservation reservation : restaurant.getReservationsList().getReservationsList()) {
            if (!reservation.isCooked()) {
                ComboBoxServing.getItems().add("Table #: " + reservation.getTable().getTableNumber());
                ComboBoxServing.setValue("Table #: " + reservation.getTable().getTableNumber());
            }
        }
        if (ComboBoxServing.getItems().isEmpty()) {
            ComboBoxServing.setPromptText("NO RESERVED TABLES");
            ComboBoxServing.setEditable(false);
        }

        isCookedBtn.setOnAction(e ->
        {
            for (Reservation reservation : restaurant.getReservationsList().getReservationsList()) {
                if (ComboBoxServing.getValue().equals("Table #: " + reservation.getTable().getTableNumber())) {
                    reservation.setCooked(true);
                    ordersArea.setText(orderBuilder());

                }
            }
            ComboBoxServing.getItems().clear();
            for (Reservation reservation : restaurant.getReservationsList().getReservationsList()) {
                if (!reservation.isCooked()) {
                    ComboBoxServing.getItems().add("Table #: " + reservation.getTable().getTableNumber());
                    ComboBoxServing.setValue("Table #: " + reservation.getTable().getTableNumber());
                }
            }
            Output.save(restaurant.getReservationsList());
            if (ComboBoxServing.getItems().isEmpty()) {
                ComboBoxServing.setPromptText("NO UNCOOKED ORDERS");
                ComboBoxServing.setEditable(false);
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
        HBox hBox2 = new HBox(ComboBoxServing, isCookedBtn);
        VBox vBox = new VBox(imageView, hBox, hBox2, ordersArea);
        vBox.setBackground(new Background(backgroundImage));
        vBox.setAlignment(Pos.TOP_CENTER);
        vBox.setSpacing(10);
        scene = new Scene(vBox, 500, 400);


    }
}
