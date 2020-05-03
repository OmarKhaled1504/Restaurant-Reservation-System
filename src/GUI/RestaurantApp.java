package GUI;

import IO.InputReader;
import Restaurant.Restaurant;
import javafx.application.Application;
import javafx.stage.Stage;


public class RestaurantApp extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Restaurant restaurant = InputReader.readInput();
        primaryStage.setTitle("Alejandro's Restaurant");
        LoginScene loginScene = new LoginScene(primaryStage);
        CustomerScene customerScene = new CustomerScene(primaryStage);
        CustomerScene2 customerScene2 = new CustomerScene2(primaryStage);
        CustomerScene3 customerScene3 = new CustomerScene3(primaryStage);
        ManagerScene managerScene = new ManagerScene(primaryStage);
        StatisticsScene statisticsScene = new StatisticsScene(primaryStage);
        CookerScene cookerScene = new CookerScene(primaryStage);
        WaiterScene waiterScene = new WaiterScene(primaryStage);

        loginScene.prepareScene();
        loginScene.setRestaurant(restaurant);
        loginScene.setManagerScene(managerScene);
        loginScene.setWaiterScene(waiterScene);
        loginScene.setCookerScene(cookerScene);
        loginScene.setCustomerScene(customerScene);
        customerScene.setRestaurant(restaurant);
        customerScene.setCustomerScene2(customerScene2);
        customerScene2.setRestaurant(restaurant);
        customerScene2.setCustomerScene3(customerScene3);
        customerScene3.setLoginScene(loginScene);
        customerScene3.setCustomerScene(customerScene);
        managerScene.setLoginScene(loginScene);
        managerScene.setStatisticsScene(statisticsScene);
        statisticsScene.setManagerScene(managerScene);
        cookerScene.setLoginScene(loginScene);
        waiterScene.setLoginScene(loginScene);
        primaryStage.setScene(loginScene.getScene());
        primaryStage.show();

    }
}




