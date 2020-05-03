package GUI;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class CustomerScene3 {
    Stage stage;
    Scene scene;
    LoginScene loginScene;
    CustomerScene customerScene;

    public CustomerScene3(Stage stage) {
        this.stage = stage;
    }

    public Scene getScene() {
        return scene;
    }

    public void setLoginScene(LoginScene loginScene) {
        this.loginScene = loginScene;
    }

    public void setCustomerScene(CustomerScene customerScene) {
        this.customerScene = customerScene;
    }

    public void prepareScene() {
        BackgroundImage backgroundImage = new BackgroundImage(new Image("https://i.ibb.co/4sXDSgM/WALLPAPER.png", 400, 280, false, true),
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);

        Label confirmationLabel = new Label("Thank you for your visit! \n Your reservation is Confirmed! ");
        confirmationLabel.setFont(Font.font("Bell MT", 16));

        Button newReservationBtn = new Button("New Reservation");
        Button exitBtn = new Button("Exit");
        Button signOutBtn = new Button("Sign Out");

        signOutBtn.setOnAction(e ->
        {
            loginScene.prepareScene();
            stage.setScene(loginScene.getScene());
        });

        newReservationBtn.setOnAction(e ->
        {
            customerScene.prepareScene();
            stage.setScene(customerScene.getScene());
        });

        exitBtn.setOnAction(e ->
                Platform.exit()
        );

        VBox vBox = new VBox(confirmationLabel, signOutBtn, exitBtn, newReservationBtn);
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(10);
        vBox.setBackground(new Background(backgroundImage));
        scene = new Scene(vBox, 400, 280);

    }
}

