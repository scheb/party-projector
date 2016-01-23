package de.christianscheb.partyprojector.controller;

import de.christianscheb.partyprojector.model.ProjectorSettings;
import de.christianscheb.partyprojector.model.Settings;
import de.christianscheb.partyprojector.model.TickerStyle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.util.StringConverter;

import java.net.URL;
import java.util.ResourceBundle;

public class SettingsController implements Initializable {

    private Settings settings;
    private SettingsEventListener eventListener;
    @FXML private Spinner posX;
    @FXML private Spinner posY;
    @FXML private Spinner sizeWidth;
    @FXML private Spinner sizeHeight;
    @FXML private Button projectorUpdateButton;
    @FXML private TextField messageTickerField;
    @FXML private Button updateMessageButton;
    @FXML private Button messageSendButton;
    @FXML private Button toggleProjectorButton;
    @FXML private Button exitButton;
    @FXML private ColorPicker messageBackgroundColor;
    @FXML private ColorPicker messageTextColor;
    @FXML private ComboBox fontFamily;
    @FXML private Spinner fontSize;

    public SettingsController(Settings settings) {
        this.settings = settings;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        configureView();
        registerEventHandlers();
    }

    public void addEventListener(SettingsEventListener eventListener) {
        this.eventListener = eventListener;
    }

    private void configureView() {
        fontFamily.getItems().addAll(
                "Arial",
                "Tahoma",
                "Verdana",
                "Comic Sans MS",
                "Roboto Condensed",
                "Roboto Medium",
                "Roboto Black",
                "Frutiger LT 57 Cn"
        );
        fontFamily.setValue(settings.getTickerStyle().getFontFamily());
        fontSize.getValueFactory().setValue(settings.getTickerStyle().getFontSize());

        ProjectorSettings projectorSettings = settings.getProjectorSettings();
        posX.getValueFactory().setValue(projectorSettings.getPosX());
        posY.getValueFactory().setValue(projectorSettings.getPosY());
        sizeWidth.getValueFactory().setValue(projectorSettings.getWidth());
        sizeHeight.getValueFactory().setValue(projectorSettings.getHeight());

        configureFocusListener(posX);
        configureFocusListener(posY);
        configureFocusListener(sizeWidth);
        configureFocusListener(sizeHeight);
        configureFocusListener(fontSize);

        messageTickerField.setText(settings.getTickerMessage());
        messageTickerField.focusedProperty().addListener(o -> {
            if (messageTickerField.isFocused()) {
                Platform.runLater(() -> messageTickerField.selectAll());
            }
        });

        messageBackgroundColor.setValue(settings.getTickerStyle().getBackgroundColor());
        messageTextColor.setValue(settings.getTickerStyle().getTextColor());

        updateProjectorButton();
    }

    private void configureFocusListener(Spinner spinner) {
        spinner.focusedProperty().addListener(o -> {
            if (spinner.isFocused()) {
                Platform.runLater(() -> spinner.getEditor().selectAll());
            }
        });

        // Commit values spinner after value changed in the text field
        spinner.getEditor().focusedProperty().addListener(o -> {
            if (!spinner.getEditor().isFocused()) {
                commitEditorText(spinner);
            }
        });
    }

    private <T> void commitEditorText(Spinner<T> spinner) {
        if (!spinner.isEditable()) return;
        String text = spinner.getEditor().getText();
        SpinnerValueFactory<T> valueFactory = spinner.getValueFactory();
        if (valueFactory != null) {
            StringConverter<T> converter = valueFactory.getConverter();
            if (converter != null) {
                T value = converter.fromString(text);
                valueFactory.setValue(value);
            }
        }
    }

    private void updateProjectorButton() {
        boolean projectorStarted = settings.isProjectorWindowStarted();
        toggleProjectorButton.setText(projectorStarted ? "Stop Projector" : "Start Projector");
    }

    private void registerEventHandlers() {
        posX.valueProperty().addListener(o -> updateProjectorSettings());
        posY.valueProperty().addListener(o -> updateProjectorSettings());
        sizeWidth.valueProperty().addListener(o -> updateProjectorSettings());
        sizeHeight.valueProperty().addListener(o -> updateProjectorSettings());
        messageTickerField.setOnKeyPressed(e -> onKeyPressed(e));
        updateMessageButton.setOnMouseClicked(e -> updateTickerMessage());
        messageBackgroundColor.valueProperty().addListener(o -> updateTickerStyle());
        messageTextColor.valueProperty().addListener(o -> updateTickerStyle());
        fontFamily.valueProperty().addListener(o -> updateTickerStyle());
        fontSize.valueProperty().addListener(o -> updateTickerStyle());
    }

    private void onKeyPressed(KeyEvent evt) {
        if (evt.getCode().equals(KeyCode.ENTER)) {
            updateTickerMessage();
        }
    }

    private void updateProjectorSettings() {
        ProjectorSettings projectorSettings = new ProjectorSettings(
                (int) posX.getValue(),
                (int) posY.getValue(),
                (int) sizeWidth.getValue(),
                (int) sizeHeight.getValue()
        );
        settings.setProjectorSettings(projectorSettings);
        if (eventListener != null) {
            eventListener.onProjectorSettingsUpdated(projectorSettings);
        }
    }

    private void updateTickerMessage() {
        String text = messageTickerField.getText();
        settings.setTickerMessage(text);
        if (eventListener != null) {
            eventListener.onTickerMessageUpdated(text);
        }
    }

    private void updateTickerStyle() {
        TickerStyle tickerStyle = new TickerStyle((String) fontFamily.getValue(), (int) fontSize.getValue(), messageBackgroundColor.getValue(), messageTextColor.getValue());
        settings.setTickerStyle(tickerStyle);
        if (eventListener != null) {
            eventListener.onTickerStyleUpdated(tickerStyle);
        }
    }

    public void onToggleProjector(ActionEvent actionEvent) {
        boolean projectorStarted = !settings.isProjectorWindowStarted();
        settings.setProjectorWindowStarted(projectorStarted);
        if (eventListener != null) {
            eventListener.onToggleProjector(projectorStarted);
        }
        toggleProjectorButton.setText(projectorStarted ? "Close Projector" : "Start Projector");
    }

    public void onExit(ActionEvent actionEvent) {
        Platform.exit();
    }
}
