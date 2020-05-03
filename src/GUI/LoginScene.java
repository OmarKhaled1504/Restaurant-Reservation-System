package GUI;

import IO.Users.User;
import Restaurant.Customers.Customer;
import Restaurant.Restaurant;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.List;


public class LoginScene {
    Stage stage;
    Scene scene;
    ManagerScene managerScene;
    CustomerScene customerScene;
    WaiterScene waiterScene;
    CookerScene cookerScene;

    private Restaurant restaurant;
    private User user;
    private Customer customer;
    private Boolean correctCredentials;

    public LoginScene(Stage stage) {
        this.stage = stage;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public Scene getScene() {
        return scene;
    }

    public void setManagerScene(ManagerScene managerScene) {
        this.managerScene = managerScene;
    }

    public void setCustomerScene(CustomerScene customerScene) {
        this.customerScene = customerScene;
    }

    public void setWaiterScene(WaiterScene waiterScene) {
        this.waiterScene = waiterScene;
    }

    public void setCookerScene(CookerScene cookerScene) {
        this.cookerScene = cookerScene;
    }

    public Boolean checkUserCredentials(List<User> users, String userName, String password) {
        for (User user : users) {
            if (user.getUsername().equals(userName) && user.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    public User checkUserRole(List<User> users, String userName, String password) {
        for (User user : users) {
            if (user.getUsername().equals(userName) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }

    public void prepareScene() {
        BackgroundImage backgroundImage = new BackgroundImage(new Image("https://i.ibb.co/4sXDSgM/WALLPAPER.png", 420, 300, false, true),
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);


        Image image = new Image("https://i.ibb.co/6bQwKF4/logo.png");
        ImageView imageView = new ImageView();
        imageView.setImage(image);
        imageView.setFitWidth(100);
        imageView.setFitHeight(50);

        Label signInLabel = new Label("SIGN IN");
        signInLabel.setFont(Font.font("Eras Bold ITC", 22));
        signInLabel.setTextFill(Color.web("#000000"));
        signInLabel.setAlignment(Pos.CENTER);


        TextField userNameField = new TextField();
        userNameField.setMaxWidth(150);

        Label userNameLabel = new Label("\t\tUsername");
        userNameLabel.setFont(Font.font("Bell MT", 16));
        userNameLabel.setTextFill(Color.web("#000000"));


        PasswordField passwordField = new PasswordField();
        passwordField.setMaxWidth(150);

        Label passwordLabel = new Label("\t\tPassword ");
        passwordLabel.setFont(Font.font("Bell MT", 16));
        passwordLabel.setTextFill(Color.web("#000000"));


        Button loginBtn = new Button("Login");
        loginBtn.setTextFill(Color.web("0x0000FF"));
        loginBtn.setAlignment(Pos.CENTER);

        Label incorrectCredentialsAlert = new Label();

        loginBtn.setOnAction(e ->
                {
                    correctCredentials = checkUserCredentials(restaurant.getUsersList().getUsersList(), userNameField.getText(), passwordField.getText());
                    if (correctCredentials) {
                        user = checkUserRole(restaurant.getUsersList().getUsersList(), userNameField.getText(), passwordField.getText());
                        if (user.getRole().equals("Client")) {
                            customer = new Customer(user.getRole(), user.getName(), user.getUsername(), user.getPassword());
                            customerScene.setRestaurant(restaurant);
                            customerScene.setCustomer(customer);
                            customerScene.prepareScene();
                            stage.setScene(customerScene.getScene());
                        } else if (user.getRole().equals("Manager")) {
                            managerScene.setRestaurant(restaurant);
                            managerScene.prepareScene();
                            stage.setScene(managerScene.getScene());
                        } else if (user.getRole().equals("Cooker")) {
                            cookerScene.setRestaurant(restaurant);
                            cookerScene.prepareScene();
                            stage.setScene(cookerScene.getScene());
                        } else if (user.getRole().equals("Waiter")) {
                            waiterScene.setRestaurant(restaurant);
                            waiterScene.prepareScene();
                            stage.setScene(waiterScene.getScene());
                        }
                    } else {
                        incorrectCredentialsAlert.setText("WRONG USERNAME OR PASSWORD");
                    }


                }
        );


        HBox userNameHBOX = new HBox(userNameLabel, userNameField);
        HBox passwordHBox = new HBox(passwordLabel, passwordField);
        VBox vBox = new VBox(imageView, signInLabel, userNameHBOX, passwordHBox, loginBtn, incorrectCredentialsAlert);

        vBox.setAlignment(Pos.TOP_CENTER);
        vBox.setSpacing(10);
        vBox.setBackground(new Background(backgroundImage));
        passwordHBox.setSpacing(10);
        userNameHBOX.setSpacing(10);

        scene = new Scene(vBox, 400, 300);
    }
}
