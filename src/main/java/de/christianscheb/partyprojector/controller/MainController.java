package de.christianscheb.partyprojector.controller;

import de.christianscheb.partyprojector.model.ProjectorSettings;
import de.christianscheb.partyprojector.model.Settings;
import de.christianscheb.partyprojector.model.SettingsModel;
import de.christianscheb.partyprojector.model.TickerStyle;
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
    private SettingsController settingsController;
    private ProjectorController projectorController;
    private Stage projectorStage;

    public MainController(SettingsModel settingsModel) {
        settings = settingsModel.getSettings();
        settingsController = new SettingsController(settingsModel.getSettings());
        settingsController.addEventListener(this);
    }

    public SettingsController getSettingsController() {
        return settingsController;
    }

    @Override
    public void onToggleProjector(boolean projectorActive) {
        System.out.println("onToggleProjector");
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
        projectorController = new ProjectorController(settings);
        loader.setController(projectorController);
        Parent root;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        Scene scene = new Scene(root, 300, 250);
        scene.setFill(new Color(1, 0, 0, 0.2));
        this.projectorStage = projectorStage;
        this.projectorStage.setScene(scene);
        this.projectorStage.setResizable(false);
        this.projectorStage.setAlwaysOnTop(true);
        updateProjectorSettings(settings.getProjectorSettings());
        this.projectorStage.setTitle("Projector");
        this.projectorStage.getIcons().add(new Image(getClass().getResourceAsStream("/application.png")));
        this.projectorStage.show();
        projectorController.startTicker();
    }

    private void updateProjectorSettings(ProjectorSettings projectorSettings) {
        projectorStage.setWidth(projectorSettings.getWidth());
        projectorStage.setHeight(projectorSettings.getHeight());
        projectorStage.setX(projectorSettings.getPosX());
        projectorStage.setY(projectorSettings.getPosY());
    }

    private void closeProjectorWindow() {
        projectorController.stopTicker();
        projectorStage.close();
        projectorStage = null;
        projectorController = null;
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

        projectorController.setTickerMessage(text);
    }

    @Override
    public void onTickerStyleUpdated(TickerStyle tickerStyle) {
        if (projectorStage == null) {
            return;
        }

        projectorController.setTickerStyle(tickerStyle);
    }
}
