package de.christianscheb.partyprojector.model;

import java.io.Serializable;

public class Settings implements Serializable {

    private ProjectorSettings projectorSettings;
    private String tickerMessage;
    private transient boolean projectorWindowStarted = false;
    private TickerStyle tickerStyle;


    public Settings() {
        projectorSettings = new ProjectorSettings(0, 0, 800, 600);
        tickerStyle = new TickerStyle();
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

    public TickerStyle getTickerStyle() {
        return tickerStyle;
    }

    public void setTickerStyle(TickerStyle tickerStyle) {
        this.tickerStyle = tickerStyle;
    }
}
