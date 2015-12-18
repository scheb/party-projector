package de.christianscheb.partywall.controller;

import de.christianscheb.partywall.model.ProjectorSettings;
import de.christianscheb.partywall.model.Settings;
import de.christianscheb.partywall.model.SettingsModel;
import de.christianscheb.partywall.view.ProjectorView;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class SettingsController implements Initializable {

    private Settings settings;

    @FXML
    public Spinner posX;

    @FXML
    public Spinner posY;

    @FXML
    public Spinner sizeWidth;

    @FXML
    public Spinner sizeHeight;

    @FXML
    public Button projectorUpdateButton;

    @FXML
    public TextField messageTickerField;

    @FXML
    public Button messageSendButton;

    @FXML
    private Button toggleProjectorButton;

    @FXML
    private Button exitButton;

    @FXML
    public ColorPicker messageBackgroundColor;

    @FXML
    public ColorPicker messageTextColor;

    public SettingsController(SettingsModel settingsModel) {
        settings = settingsModel.getSettings();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        configureFocusListener(posX);
        configureFocusListener(posY);
        configureFocusListener(sizeWidth);
        configureFocusListener(sizeHeight);

        ProjectorSettings projectorSettings = settings.getProjectorSettings();
        posX.getValueFactory().setValue(projectorSettings.getPosX());
        posY.getValueFactory().setValue(projectorSettings.getPosY());
        sizeWidth.getValueFactory().setValue(projectorSettings.getWidth());
        sizeHeight.getValueFactory().setValue(projectorSettings.getHeight());

        messageTickerField.setText(settings.getTickerMessage());
        messageTickerField.focusedProperty().addListener(o -> {
            if (messageTickerField.isFocused()) {
                Platform.runLater(() -> messageTickerField.selectAll());
            }
        });

        messageBackgroundColor.setValue(settings.getTickerBackgroundColor());
        messageTextColor.setValue(settings.getTickerTextColor());

        updateProjectorButtons();
    }

    private void configureFocusListener(Spinner spinner) {
        spinner.focusedProperty().addListener(o -> {
            if (spinner.isFocused()) {
                Platform.runLater(() -> spinner.getEditor().selectAll());
            }
        });
    }

    public void updateProjectorButtons() {
        boolean projectorStarted = settings.isProjectorWindowStarted();
        toggleProjectorButton.setText(projectorStarted ? "Stop Projector" : "Start Projector");
    }
//
//    public ProjectorPosition getProjectorPosition() {
//        return new ProjectorPosition(
//                (int) posX.getValue(),
//                (int) posY.getValue(),
//                (int) windowWidth.getValue(),
//                (int) windowHeight.getValue()
//        );
//    }
//
//    private void createSettingsWindow() {
//        settingsView = new SettingsView(settingsModel);
//        settingsView.setLocationRelativeTo(settingsView);
//
//        settingsView.registerSetTickerMessageListener(new SetTickerMessageListener());
//        settingsView.registerUpdateProjectorWindowListener(new UpdateProjectorListener());
//        settingsView.registerExitListener(new ExitListener());
//        settingsView.registerStartStopProjectorWindowListener(new StartStopProjectorWindowListener());
//
//        settingsView.setVisible(true);
//    }
//
//    private void startProjectorWindow() {
//        projectorView = new ProjectorView();
//        positionProjectorWindow();
//        projectorView.setVisible(true);
//    }
//
//    private void stopProjectorWindow() {
//        projectorView.dispose();
//        projectorView = null;
//    }
//
//    private void positionProjectorWindow() {
//        ProjectorSettings projectorPosition = settingsModel.getProjectorConfig();
//        projectorView.setLocation(projectorPosition.getPosX(), projectorPosition.getPosY());
//        projectorView.setSize(projectorPosition.getWidth(), projectorPosition.getHeight());
//    }
//
//    class UpdateProjectorListener implements ActionListener {
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            settingsModel.setProjectorConfig(settingsView.getProjectorConfig());
//            if (settingsModel.isProjectorWindowStarted()) {
//                positionProjectorWindow();
//            }
//        }
//    }
//
//    class StartStopProjectorWindowListener implements ActionListener {
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            settingsModel.setProjectorConfig(settingsView.getProjectorConfig());
//            settingsModel.setProjectorWindowStarted(!settingsModel.isProjectorWindowStarted());
//            settingsView.updateProjectorButtons();
//            if (settingsModel.isProjectorWindowStarted()) {
//                startProjectorWindow();
//            } else {
//                stopProjectorWindow();
//            }
//        }
//    }
//
//    class SetTickerMessageListener implements ActionListener {
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            settingsModel.setTickerMessage(settingsView.getTickerMessage());
//        }
//    }
//
//    class ExitListener implements ActionListener {
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            System.exit(0);
//        }
//    }
}
