package de.christianscheb.partywall.model;

public class Settings {

    private ProjectorSettings projectorSettings;
    private String tickerMessage = null;
    private boolean projectorWindowStarted = false;
    private TickerStyle tickerStyle;


    public Settings() {
        projectorSettings = new ProjectorSettings(0, 0, 1920, 1080);
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
