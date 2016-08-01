package de.christianscheb.partyprojector.controller;

import de.christianscheb.partyprojector.model.*;
import de.christianscheb.partyprojector.view.MessageProviderInterface;
import de.christianscheb.partyprojector.view.MessageTicker;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class ProjectorTickerController implements Initializable {

    private final Settings settings;
    private MessageProviderInterface messageStorage;
    @FXML private MessageTicker messageTicker;

    public ProjectorTickerController(Settings settings, MessageStorage messageStorage) {
        this.settings = settings;
        this.messageStorage = messageStorage;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        messageTicker.setMessageProvider(messageStorage);
        messageTicker.heightProperty().addListener((observable, oldValue, newValue) -> updateHeight(newValue.doubleValue()));
        setTickerStyle(settings.getTickerStyle());
    }

    private void updateHeight(double value) {
        Platform.runLater(() -> messageTicker.getScene().getWindow().setHeight(value));
    }

    public void setTickerStyle(TickerStyle tickerStyle) {
        messageTicker.setFont(tickerStyle.getFont());
        messageTicker.setBackgroundColor(settings.getTickerStyle().getBackgroundColor());
        messageTicker.setTextColor(settings.getTickerStyle().getTextColor());
    }

    public void start() {
        messageTicker.start();
    }

    public void stop() {
        messageTicker.stop();
    }
}
