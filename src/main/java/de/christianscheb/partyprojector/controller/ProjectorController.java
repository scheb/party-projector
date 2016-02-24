package de.christianscheb.partyprojector.controller;

import de.christianscheb.partyprojector.model.*;
import de.christianscheb.partyprojector.view.VideoStream;
import de.christianscheb.partyprojector.view.MessageProviderInterface;
import de.christianscheb.partyprojector.view.MessageTicker;
import de.christianscheb.partyprojector.view.PictureSlideshow;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class ProjectorController implements Initializable {

    private final Settings settings;
    private final PictureStorage pictureStorage;
    private final StreamModel streamModel;
    private MessageProviderInterface messageStorage;
    @FXML private MessageTicker messageTicker;
    @FXML private PictureSlideshow pictureSlideshow;
    @FXML public VideoStream videoStream;

    public ProjectorController(Settings settings, MessageStorage messageStorage, PictureStorage pictureStorage, StreamModel streamModel) {
        this.settings = settings;
        this.messageStorage = messageStorage;
        this.pictureStorage = pictureStorage;
        this.streamModel = streamModel;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setTickerStyle(settings.getTickerStyle());
        messageTicker.setMessageProvider(messageStorage);
        pictureSlideshow.setPictureProvider(pictureStorage);
        videoStream.setStreamModel(streamModel);
    }

    public void setTickerStyle(TickerStyle tickerStyle) {
        messageTicker.setFont(tickerStyle.getFont());
        messageTicker.setBackgroundColor(settings.getTickerStyle().getBackgroundColor());
        messageTicker.setTextColor(settings.getTickerStyle().getTextColor());
    }

    public void start() {
        messageTicker.start();
        pictureSlideshow.start();
    }

    public void stop() {
        messageTicker.stop();
        pictureSlideshow.stop();
        videoStream.stop();
    }
}
