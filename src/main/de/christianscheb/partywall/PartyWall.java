package de.christianscheb.partywall;

import de.christianscheb.partywall.controller.PartyWallController;
import de.christianscheb.partywall.controller.SettingsController;
import de.christianscheb.partywall.controller.SettingsEventListener;
import de.christianscheb.partywall.model.SettingsModel;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class PartyWall extends Application {

    public static final String APPLICATION_TITLE = "Party Wall";

    @Override
    public void start(Stage primaryStage) throws Exception{
        PartyWallController controller = new PartyWallController(primaryStage, new SettingsModel());
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Settings.fxml"));
        loader.setController(controller.getSettingsController());
        Parent root = loader.load();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle(APPLICATION_TITLE);
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/application.png")));
        primaryStage.setResizable(false);
        primaryStage.show();
        primaryStage.setOnCloseRequest(e -> Platform.exit());
    }

    public static void main(String[] args) {
        launch(args);
    }
}
