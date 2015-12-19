package de.christianscheb.partyprojector.model;

import java.awt.*;

public class SettingsModel {

    private Settings settings;

    public SettingsModel() {
        settings = new Settings();

        // Get default size from current monitor
        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        int width = gd.getDisplayMode().getWidth();
        int height = gd.getDisplayMode().getHeight();
        settings.getProjectorSettings().setWidth(width);
        settings.getProjectorSettings().setHeight(height);
    }

    public Settings getSettings() {
        return settings;
    }
}
