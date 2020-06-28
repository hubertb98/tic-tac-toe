package kodilla;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader easyLoader = new FXMLLoader(this.getClass().getResource("/fxml/EasyScreen.fxml"));
        FXMLLoader hardLoader = new FXMLLoader(this.getClass().getResource("/fxml/HardScreen.fxml"));


        Pane easyPane = easyLoader.load();
        Pane hardPane = hardLoader.load();

        Scene scene1 = new Scene(easyPane);
        Scene scene2 = new Scene(hardPane);

        primaryStage.setTitle("Tic Tac Toe");
        primaryStage.setScene(scene1);
        primaryStage.show();
    }
}
