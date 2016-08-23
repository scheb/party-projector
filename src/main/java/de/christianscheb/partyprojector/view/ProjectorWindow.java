package de.christianscheb.partyprojector.view;

import de.christianscheb.partyprojector.PartyProjector;
import de.christianscheb.partyprojector.controller.ProjectorController;
import de.christianscheb.partyprojector.model.ProjectorSettings;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class ProjectorWindow {

    private ProjectorController projectorController;
    private Stage tickerStage;
    private Stage sliderStage;
    private ProjectorSettings projectorSettings;

    public ProjectorWindow(ProjectorController projectorController) {
        this.projectorController = projectorController;
    }

    public void showProjectorWindow(ProjectorSettings projectorSettings) {
        showTickerProjectorWindow();
        showSliderProjectorWindow();
        registerStagePositionListeners();
        updateProjectorSettings(projectorSettings);

        tickerStage.show();

        projectorController.start();
    }

    private void showTickerProjectorWindow() {
        tickerStage = new Stage();
        tickerStage.initStyle(StageStyle.UNDECORATED);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ProjectorTicker.fxml"));
        loader.setController(projectorController.getTickerController());
        Parent root;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        Scene tickerScene = new Scene(root, 0, 0);
        tickerStage.setScene(tickerScene);
        tickerStage.setResizable(false);
        tickerStage.setAlwaysOnTop(true);
        tickerStage.setTitle("Projector Ticker");
        tickerStage.getIcons().add(new Image(getClass().getResourceAsStream(PartyProjector.APPLICATION_ICON)));
        tickerStage.setOnCloseRequest(e -> closeProjectorWindow());
    }

    private void showSliderProjectorWindow() {
        sliderStage = new SwitchStage();
        sliderStage.initStyle(StageStyle.UNDECORATED);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ProjectorSlider.fxml"));
        loader.setController(projectorController.getSliderController());
        Parent root;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        Scene tickerScene = new Scene(root, 0, 0);
        tickerScene.setFill(Color.rgb(50, 50, 50));
        sliderStage.setScene(tickerScene);
        sliderStage.setResizable(false);
        sliderStage.setAlwaysOnTop(true);
        sliderStage.setTitle("Projector Slider");
        sliderStage.getIcons().add(new Image(getClass().getResourceAsStream(PartyProjector.APPLICATION_ICON)));
        sliderStage.setOnCloseRequest(e -> closeProjectorWindow());
    }

    private void registerStagePositionListeners() {
        tickerStage.heightProperty().addListener((observable, oldValue, newValue) -> updateSliderStageLayout());
        tickerStage.yProperty().addListener((observable, oldValue, newValue) -> updateSliderStageLayout());
    }

    private void updateSliderStageLayout() {
        double tickerStageHeight = tickerStage.getHeight();
        double sliderStageY = projectorSettings.getPosY() + tickerStageHeight;
        double sliderStageHeight = projectorSettings.getHeight() - tickerStageHeight;
        sliderStage.setY(sliderStageY);
        sliderStage.setHeight(sliderStageHeight);
    }

    public void updateProjectorSettings(ProjectorSettings projectorSettings) {
        this.projectorSettings = projectorSettings;

        tickerStage.setWidth(projectorSettings.getWidth());
        tickerStage.setX(projectorSettings.getPosX());
        tickerStage.setY(projectorSettings.getPosY());

        sliderStage.setWidth(projectorSettings.getWidth());
        sliderStage.setX(projectorSettings.getPosX());

        updateSliderStageLayout();
    }

    public void closeProjectorWindow() {
        tickerStage.close();
        tickerStage = null;
        sliderStage.close();
        sliderStage = null;
    }
}
