package de.christianscheb.partyprojector.view;

import de.christianscheb.partyprojector.model.PictureStorage;
import javafx.scene.layout.Pane;

public class PictureSlideshow extends Pane {

    private PictureStorage pictureProvider;

    public PictureSlideshow() {

    }

    public void start() {

    }

    public void stop() {

    }

    public void setPictureProvider(PictureStorage pictureProvider) {
        this.pictureProvider = pictureProvider;
    }
}
