package de.christianscheb.partyprojector.controller;

import de.christianscheb.partyprojector.model.*;
import de.christianscheb.partyprojector.view.ProjectorWindow;

public class MainController implements SettingsEventListener {
    private final Settings settings;
    private final MessageStorage messageStorage;
    private final PictureStorage pictureStorage;
    private final StreamModel streamModel;
    private SettingsController settingsController;
    private ProjectorController projectorController;
    private ProjectorWindow projectorWindow;

    public MainController(SettingsModel settingsModel, MessageStorage messageStorage, PictureStorage pictureStorage, StreamModel streamModel) {
        settings = settingsModel.getSettings();
        this.messageStorage = messageStorage;
        this.pictureStorage = pictureStorage;
        this.streamModel = streamModel;
        settingsController = new SettingsController(settingsModel.getSettings());
        settingsController.addEventListener(this);
    }

    public SettingsController getSettingsController() {
        return settingsController;
    }

    @Override
    public void onToggleProjector(boolean projectorActive) {
        if (projectorWindow == null) {
            showProjectorWindow();
        } else {
            closeProjectorWindow();
        }
    }

    private void showProjectorWindow() {
        projectorController = new ProjectorController(settings, messageStorage, pictureStorage, streamModel);
        projectorWindow = new ProjectorWindow(projectorController);
        projectorWindow.showProjectorWindow();
        updateProjectorSettings(settings.getProjectorSettings());
    }

    private void updateProjectorSettings(ProjectorSettings projectorSettings) {
        projectorWindow.updateProjectorSettings(projectorSettings);
    }

    private void closeProjectorWindow() {
        projectorController.stop();
        projectorWindow.closeProjectorWindow();
        projectorWindow = null;
        projectorController = null;
        settings.setProjectorWindowStarted(false);
    }

    @Override
    public void onProjectorSettingsUpdated(ProjectorSettings projectorSettings) {
        if (projectorWindow != null) {
            updateProjectorSettings(projectorSettings);
        }
    }

    @Override
    public void onTickerMessageUpdated(String text) {
        if (projectorController != null) {
            messageStorage.setStaticMessage(text);
        }
    }

    @Override
    public void onTickerStyleUpdated(TickerStyle tickerStyle) {
        if (projectorController != null) {
            projectorController.setTickerStyle(tickerStyle);
        }
    }
}
