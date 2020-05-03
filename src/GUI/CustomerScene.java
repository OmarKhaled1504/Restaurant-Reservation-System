package GUI;

import IO.Tables.Table;
import Restaurant.Customers.Customer;
import Restaurant.Restaurant;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;


public class CustomerScene {
    Scene scene;
    Stage stage;
    CustomerScene2 customerScene2;
    private Restaurant restaurant;

    private Customer customer;

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public CustomerScene(Stage stage) {
        this.stage = stage;
    }

    public Scene getScene() {
        return scene;
    }


    public void setCustomerScene2(CustomerScene2 customerScene2) {
        this.customerScene2 = customerScene2;
    }

    public void prepareScene() {
        BackgroundImage backgroundImage = new BackgroundImage(new Image("https://i.ibb.co/4sXDSgM/WALLPAPER.png", 450, 300, false, true),
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);


        Image image1 = new Image("https://i.ibb.co/19CvKjt/reserve.png");
        ImageView imageView1 = new ImageView();
        imageView1.setImage(image1);
        imageView1.setFitWidth(150);
        imageView1.setFitHeight(80);

        Image image2 = new Image("https://i.ibb.co/zJqkRp0/non-smoker.png");
        ImageView imageView2 = new ImageView();
        imageView2.setImage(image2);
        imageView2.setFitWidth(25);
        imageView2.setFitHeight(25);

        Image image3 = new Image("https://i.ibb.co/VqqN2JN/SMOKER.png");
        ImageView imageView3 = new ImageView();
        imageView3.setImage(image3);
        imageView3.setFitWidth(25);
        imageView3.setFitHeight(25);

        Label tableType = new Label("   Table Type");
        tableType.setFont(Font.font("Bell MT", 15));
        tableType.setTextFill(Color.web("#000000"));

        Label numberOfSeats = new Label("   Table");
        numberOfSeats.setFont(Font.font("Bell MT", 15));
        numberOfSeats.setTextFill(Color.web("#000000"));

        ToggleGroup answerTGroup = new ToggleGroup();

        RadioButton smokingRButton = new RadioButton("Smoking");
        smokingRButton.setFont(Font.font("Bell MT", 14));
        smokingRButton.setToggleGroup(answerTGroup);

        RadioButton nonSmokingRButton = new RadioButton("Non-Smoking");
        nonSmokingRButton.setFont(Font.font("Bell MT", 14));
        nonSmokingRButton.setToggleGroup(answerTGroup);

        Button goToMenuBtn = new Button("Restaurant Menu");
        goToMenuBtn.setDisable(true);

        ComboBox<String> seatsComboBox = new ComboBox<>();

        smokingRButton.setOnAction(e ->
                {
                    goToMenuBtn.setDisable(false);
                    seatsComboBox.getItems().clear();
                    for (Table table : restaurant.getTablesList().getTablesList()) {
                        if (table.getAvailability() && table.isSmokingArea()) {
                            seatsComboBox.getItems().add("Table: " + table.getTableNumber() + ", " + table.getNumberOfSeats() + " Seats");
                            seatsComboBox.setValue("Table: " + table.getTableNumber() + ", " + table.getNumberOfSeats() + " Seats");
                        }
                    }
                    if (seatsComboBox.getItems().isEmpty()) {
                        seatsComboBox.setPromptText("NO AVAILABLE TABLES");
                        seatsComboBox.setEditable(false);
                    }
                }
        );
        nonSmokingRButton.setOnAction(e ->
                {
                    goToMenuBtn.setDisable(false);
                    seatsComboBox.getItems().clear();
                    for (Table table : restaurant.getTablesList().getTablesList()) {
                        if (table.getAvailability() && !table.isSmokingArea()) {
                            seatsComboBox.getItems().add("Table: " + table.getTableNumber() + ", " + table.getNumberOfSeats() + " Seats");
                            seatsComboBox.setValue("Table: " + table.getTableNumber() + ", " + table.getNumberOfSeats() + " Seats");
                        }
                    }
                    if (seatsComboBox.getItems().isEmpty()) {
                        seatsComboBox.setPromptText("no available tables");
                        seatsComboBox.setEditable(false);
                    }
                }
        );

        goToMenuBtn.setOnAction(e ->
                {
                    customerScene2.setRestaurant(restaurant);
                    customerScene2.setCustomer(customer);
                    for (Table table : restaurant.getTablesList().getTablesList()) {
                        if (seatsComboBox.getSelectionModel().getSelectedItem().contains(("Table: " + table.getTableNumber()))) {
                            customerScene2.setTable(table);
                            break;
                        }
                    }
                    customerScene2.prepareScene();
                    stage.setScene(customerScene2.getScene());
                }
        );


        HBox tableTypeHBox = new HBox(tableType, smokingRButton, imageView3, nonSmokingRButton, imageView2);
        tableTypeHBox.setSpacing(10);

        HBox numberOfSeatsHBox = new HBox(numberOfSeats, seatsComboBox);
        numberOfSeatsHBox.setSpacing(10);


        VBox vBox = new VBox(imageView1, tableTypeHBox, numberOfSeatsHBox, goToMenuBtn);
        vBox.setAlignment(Pos.TOP_CENTER);
        vBox.setSpacing(8);
        vBox.setBackground(new Background(backgroundImage));
        scene = new Scene(vBox, 450, 250);


    }
}
