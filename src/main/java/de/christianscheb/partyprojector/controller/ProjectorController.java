package de.christianscheb.partyprojector.controller;

import de.christianscheb.partyprojector.model.MessageStorage;
import de.christianscheb.partyprojector.model.PictureStorage;
import de.christianscheb.partyprojector.model.Settings;
import de.christianscheb.partyprojector.model.TickerStyle;
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
    private MessageProviderInterface messageStorage;
    @FXML private MessageTicker messageTicker;
    @FXML private PictureSlideshow pictureSlideshow;

    public ProjectorController(Settings settings, MessageStorage messageStorage, PictureStorage pictureStorage) {
        this.settings = settings;
        this.messageStorage = messageStorage;
        this.pictureStorage = pictureStorage;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setTickerStyle(settings.getTickerStyle());
        messageTicker.setMessageProvider(messageStorage);
        pictureSlideshow.setPictureProvider(pictureStorage);
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
    }
}
