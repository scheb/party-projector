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

    public ProjectorWindow(ProjectorController projectorController) {
        this.projectorController = projectorController;
    }

    public void showProjectorWindow() {
        showTickerProjectorWindow();
        showSliderProjectorWindow();

        tickerStage.show();
        sliderStage.show();

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
        sliderStage = new Stage();
        sliderStage.initStyle(StageStyle.TRANSPARENT);

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
        tickerScene.setFill(Color.TRANSPARENT);
        sliderStage.setScene(tickerScene);
        sliderStage.setResizable(false);
        sliderStage.setAlwaysOnTop(true);
        sliderStage.setTitle("Projector Slider");
        sliderStage.getIcons().add(new Image(getClass().getResourceAsStream(PartyProjector.APPLICATION_ICON)));
        sliderStage.setOnCloseRequest(e -> closeProjectorWindow());
    }

    public void updateProjectorSettings(ProjectorSettings projectorSettings) {
        tickerStage.setWidth(projectorSettings.getWidth());
        tickerStage.setHeight(-1);
        tickerStage.setX(projectorSettings.getPosX());
        tickerStage.setY(projectorSettings.getPosY());

        sliderStage.setWidth(projectorSettings.getWidth());
        sliderStage.setHeight(-1);
        sliderStage.setX(projectorSettings.getPosX());
        sliderStage.setY(projectorSettings.getPosY());
    }

    public void closeProjectorWindow() {
        tickerStage.close();
        tickerStage = null;
        sliderStage.close();
        sliderStage = null;
    }
}
