package de.christianscheb.partyprojector.controller;

import de.christianscheb.partyprojector.model.*;

public class ProjectorController {

    private final ProjectorTickerController tickerController;
    private final ProjectorSliderController sliderController;

    public ProjectorController(Settings settings, MessageStorage messageStorage, PictureStorage pictureStorage, StreamModel streamModel) {
        this.tickerController = new ProjectorTickerController(settings, messageStorage);
        this.sliderController = new ProjectorSliderController(settings, pictureStorage, streamModel);
    }

    public ProjectorTickerController getTickerController() {
        return tickerController;
    }

    public ProjectorSliderController getSliderController() {
        return sliderController;
    }

    public void setTickerStyle(TickerStyle tickerStyle) {
        tickerController.setTickerStyle(tickerStyle);
    }

    public void start() {
        tickerController.start();
        sliderController.start();
    }

    public void stop() {
        tickerController.stop();
        sliderController.stop();
    }
}
