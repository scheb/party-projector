package de.christianscheb.partyprojector.model;

import org.ini4j.Wini;

public class Settings implements IniSerializable {

    public static final String INI_SECTION_TICKER = "ticker";
    public static final String INI_SECTION_WEBSERVER = "webserver";
    public static final String INI_VALUE_MESSAGE = "message";
    public static final String INI_VALUE_PORT = "port";

    private static final int DEFAULT_WEBSERVER_PORT = 8080;

    private ProjectorSettings projectorSettings;
    private TickerStyle tickerStyle;

    private String tickerMessage;
    private int webserverPort;
    private boolean projectorWindowStarted = false;

    public Settings() {
        projectorSettings = new ProjectorSettings(0, 0, 800, 600);
        tickerStyle = new TickerStyle();
        webserverPort = DEFAULT_WEBSERVER_PORT;
    }

    @Override
    public void serialize(Wini ini) {
        ini.put(INI_SECTION_TICKER, INI_VALUE_MESSAGE, tickerMessage);
        ini.put(INI_SECTION_WEBSERVER, INI_VALUE_PORT, webserverPort);

        projectorSettings.serialize(ini);
        tickerStyle.serialize(ini);
    }

    @Override
    public void unserialize(Wini ini) {
        String tickerMessage = ini.get(INI_SECTION_TICKER, INI_VALUE_MESSAGE);
        if (tickerMessage != null && tickerMessage.length() > 0) {
            this.tickerMessage = tickerMessage;
        }

        int webserverPort = ini.get(INI_SECTION_WEBSERVER, INI_VALUE_PORT, int.class);
        if (webserverPort > 0) {
            this.webserverPort = webserverPort;
        }

        projectorSettings.unserialize(ini);
        tickerStyle.unserialize(ini);
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

    public int getWebserverPort() {
        return webserverPort;
    }

    public void setWebserverPort(int webserverPort) {
        this.webserverPort = webserverPort;
    }
}
