package Project_Class;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../Project_UI/deneme.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 675, 375));
        primaryStage.show();
//dsasdadada
        //Deneme
    }
    public static void main(String[] args) {
        launch(args);
    }
}
