package de.christianscheb.partyprojector.controller;

import de.christianscheb.partyprojector.PartyProjector;
import de.christianscheb.partyprojector.model.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class MainController implements SettingsEventListener {
    private final Settings settings;
    private final MessageStorage messageStorage;
    private final PictureStorage pictureStorage;
    private SettingsController settingsController;
    private ProjectorController projectorController;
    private Stage projectorStage;

    public MainController(SettingsModel settingsModel, MessageStorage messageStorage, PictureStorage pictureStorage) {
        settings = settingsModel.getSettings();
        this.messageStorage = messageStorage;
        this.pictureStorage = pictureStorage;
        settingsController = new SettingsController(settingsModel.getSettings());
        settingsController.addEventListener(this);
    }

    public SettingsController getSettingsController() {
        return settingsController;
    }

    @Override
    public void onToggleProjector(boolean projectorActive) {
        if (projectorStage != null) {
            closeProjectorWindow();
        }
        if (projectorActive) {
            showProjectorWindow();
        }
    }

    private void showProjectorWindow() {
        Stage projectorStage = new Stage();
        projectorStage.initStyle(StageStyle.TRANSPARENT);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Projector.fxml"));
        projectorController = new ProjectorController(settings, messageStorage, pictureStorage);
        loader.setController(projectorController);
        Parent root;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        Scene scene = new Scene(root, 300, 250);
        scene.setFill(Color.TRANSPARENT);
        this.projectorStage = projectorStage;
        this.projectorStage.setScene(scene);
        this.projectorStage.setResizable(false);
        this.projectorStage.setAlwaysOnTop(true);
        updateProjectorSettings(settings.getProjectorSettings());
        this.projectorStage.setTitle("Projector");
        this.projectorStage.getIcons().add(new Image(getClass().getResourceAsStream(PartyProjector.APPLICATION_ICON)));
        this.projectorStage.show();
        this.projectorStage.setOnCloseRequest(e -> closeProjectorWindow());
        projectorController.start();
    }

    private void updateProjectorSettings(ProjectorSettings projectorSettings) {
        projectorStage.setWidth(projectorSettings.getWidth());
        projectorStage.setHeight(projectorSettings.getHeight());
        projectorStage.setX(projectorSettings.getPosX());
        projectorStage.setY(projectorSettings.getPosY());
    }

    private void closeProjectorWindow() {
        projectorController.stop();
        projectorStage.close();
        projectorStage = null;
        projectorController = null;
        settings.setProjectorWindowStarted(false);
    }

    @Override
    public void onProjectorSettingsUpdated(ProjectorSettings projectorSettings) {
        if (projectorStage == null) {
            return;
        }

        updateProjectorSettings(projectorSettings);
    }

    @Override
    public void onTickerMessageUpdated(String text) {
        if (projectorStage == null) {
            return;
        }

        messageStorage.setStaticMessage(text);
    }

    @Override
    public void onTickerStyleUpdated(TickerStyle tickerStyle) {
        if (projectorStage == null) {
            return;
        }

        projectorController.setTickerStyle(tickerStyle);
    }
}
