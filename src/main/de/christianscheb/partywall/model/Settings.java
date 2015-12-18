package de.christianscheb.partywall.model;

import javafx.scene.paint.Color;

public class Settings {

    private ProjectorSettings projectorSettings;
    private String tickerMessage = null;
    private boolean projectorWindowStarted = false;

    public Settings() {
        projectorSettings = new ProjectorSettings(0, 0, 1920, 1080);
    }

    public ProjectorSettings getProjectorSettings() {
        return projectorSettings;
    }

    public void setProjectorSettings(ProjectorSettings projectorSettings) {
        this.projectorSettings = projectorSettings;
    }

    public String getTickerMessage() {
        return tickerMessage;
    }

    public void setTickerMessage(String tickerMessage) {
        this.tickerMessage = tickerMessage;
    }

    public boolean isProjectorWindowStarted() {
        return projectorWindowStarted;
    }

    public void setProjectorWindowStarted(boolean projectorWindowStarted) {
        this.projectorWindowStarted = projectorWindowStarted;
    }

    public Color getTickerBackgroundColor() {
        return Color.color(1, 1, 1);
    }

    public Color getTickerTextColor() {
        return Color.color(0, 0, 0);
    }
}
