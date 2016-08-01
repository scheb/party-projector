package de.christianscheb.partyprojector.controller;

import de.christianscheb.partyprojector.model.*;
import de.christianscheb.partyprojector.view.PictureSlideshow;
import de.christianscheb.partyprojector.view.VideoStream;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class ProjectorSliderController implements Initializable {

    private final Settings settings;
    private final PictureStorage pictureStorage;
    private final StreamModel streamModel;
    @FXML private PictureSlideshow pictureSlideshow;
    @FXML private VideoStream videoStream;

    public ProjectorSliderController(Settings settings, PictureStorage pictureStorage, StreamModel streamModel) {
        this.settings = settings;
        this.pictureStorage = pictureStorage;
        this.streamModel = streamModel;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        pictureSlideshow.setPictureProvider(pictureStorage);
        videoStream.setStreamModel(streamModel);
    }

    public void start() {
        pictureSlideshow.start();
    }

    public void stop() {
        pictureSlideshow.stop();
        videoStream.stop();
    }
}
