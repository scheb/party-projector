package de.christianscheb.partywall;

import de.christianscheb.partywall.controller.SettingsController;
import de.christianscheb.partywall.model.SettingsModel;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class PartyWall extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        showSettingsDialog(primaryStage);
    }

    private void showSettingsDialog(Stage primaryStage) throws java.io.IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Settings.fxml"));
        SettingsController controller = new SettingsController(new SettingsModel());
        loader.setController(controller);
        Parent root = loader.load();
        Scene value = new Scene(root);
        primaryStage.setScene(value);
        primaryStage.setTitle("Party Wall");
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/application.png")));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
