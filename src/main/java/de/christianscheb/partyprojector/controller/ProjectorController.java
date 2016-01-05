package de.christianscheb.partyprojector.controller;

import de.christianscheb.partyprojector.model.MessageStorage;
import de.christianscheb.partyprojector.model.Settings;
import de.christianscheb.partyprojector.model.TickerStyle;
import de.christianscheb.partyprojector.view.MessageProviderInterface;
import de.christianscheb.partyprojector.view.MessageTicker;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

public class ProjectorController implements Initializable {

    private final Settings settings;
    private MessageProviderInterface messageStorage;
    @FXML private MessageTicker messageTicker;
    @FXML private Pane picturePane;

    public ProjectorController(Settings settings, MessageStorage messageStorage) {
        this.settings = settings;
        this.messageStorage = messageStorage;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setTickerStyle(settings.getTickerStyle());
        messageTicker.setMessageProvider(messageStorage);
    }

    public void setTickerStyle(TickerStyle tickerStyle) {
        messageTicker.setFont(tickerStyle.getFont());
        messageTicker.setBackgroundColor(settings.getTickerStyle().getBackgroundColor());
        messageTicker.setTextColor(settings.getTickerStyle().getTextColor());
    }

    public void startTicker() {
        messageTicker.start();
    }

    public void stopTicker() {
        messageTicker.stop();
    }
}
