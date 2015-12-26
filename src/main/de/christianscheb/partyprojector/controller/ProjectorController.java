package de.christianscheb.partyprojector.controller;

import de.christianscheb.partyprojector.model.MessageProvider;
import de.christianscheb.partyprojector.model.Settings;
import de.christianscheb.partyprojector.model.TickerStyle;
import de.christianscheb.partyprojector.view.MessageTicker;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

public class ProjectorController implements Initializable {

    private final Settings settings;
    @FXML private MessageTicker messageTicker;
    @FXML private Pane picturePane;

    public ProjectorController(Settings settings) {
        this.settings = settings;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setTickerStyle(settings.getTickerStyle());
        messageTicker.setMessageProvider(new MessageProvider());
    }

    public void play() {
        messageTicker.play();
    }

    public void setTickerStyle(TickerStyle tickerStyle) {
        messageTicker.setBackgroundColor(settings.getTickerStyle().getBackgroundColor());
        messageTicker.setTextColor(settings.getTickerStyle().getTextColor());
    }

    public void setTickerMessage(String message) {

    }
}
