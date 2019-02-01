package sample.run;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.controlls.TableViewControl;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("../fxml/tableView.fxml"));
        Parent fxmlUtil = fxmlLoader.load();
        TableViewControl tableViewControl = fxmlLoader.getController();
        tableViewControl.setTableStage(primaryStage);
        primaryStage.setTitle("Table view");
        primaryStage.setMinWidth(1200);
        primaryStage.setMinHeight(640);
        primaryStage.setScene(new Scene(fxmlUtil, 1185, 601));
        primaryStage.getScene().getStylesheets().add(getClass().getResource("../css/style.css").toExternalForm());
        primaryStage.show();

    }


    public static void main(String[] args) {


        launch(args);
    }
}
