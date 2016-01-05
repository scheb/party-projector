package de.christianscheb.partyprojector;

import de.christianscheb.partyprojector.controller.MainController;
import de.christianscheb.partyprojector.model.MessageStorage;
import de.christianscheb.partyprojector.model.Settings;
import de.christianscheb.partyprojector.model.SettingsModel;
import de.christianscheb.partyprojector.server.WebServer;
import fi.iki.elonen.util.ServerRunner;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class PartyProjector extends Application {

    private static final String APPLICATION_TITLE = "Party Wall";
    public static final String APPLICATION_ICON = "/application.png";
    private SettingsModel settingsModel = new SettingsModel();
    private MessageStorage messageStorage = new MessageStorage();
    private WebServer webserver;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Settings settings = settingsModel.getSettings();

        // Initially set the ticker message
        messageStorage.setStaticMessage(settings.getTickerMessage());

        showSettingsWindow(primaryStage);
        startWebServer(settings.getWebserverPort());

        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() { shutdown(); }
        });
    }

    private void showSettingsWindow(Stage primaryStage) throws IOException {
        MainController controller = new MainController(settingsModel, messageStorage);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Settings.fxml"));
        loader.setController(controller.getSettingsController());
        Parent root = loader.load();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle(APPLICATION_TITLE);
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream(APPLICATION_ICON)));
        primaryStage.setResizable(false);
        primaryStage.show();
        primaryStage.setOnCloseRequest(e -> Platform.exit());
    }

    private void startWebServer(int port) {
        try {
            webserver = new WebServer(port, messageStorage);
        } catch (IOException ioe) {
            System.err.println("Couldn't start webserver:\n" + ioe);
        }
    }

    private void shutdown() {
        settingsModel.persistSettings();
        if (webserver != null) {
            webserver.stop();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
