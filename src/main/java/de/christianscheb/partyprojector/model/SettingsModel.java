package de.christianscheb.partyprojector.model;

import org.ini4j.Wini;

import java.awt.*;
import java.io.*;

public class SettingsModel {

    public static final String SETTINGS_FILE = "settings.ini";
    private Settings settings;

    public SettingsModel() {
        settings = loadSettings();
    }

    private Settings loadSettings() {
        Wini ini;
        try {
            ini = new Wini(new File(SETTINGS_FILE));
            Settings settings = new Settings();
            settings.unserialize(ini);
            return settings;
        } catch (Exception e) {
            e.printStackTrace();
            return getDefaultSettings();
        }
    }

    public void persistSettings() {
        Wini ini;
        try {
            ini = new Wini(new File(SETTINGS_FILE));
            settings.serialize(ini);
            ini.store();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Settings getDefaultSettings() {
        Settings settings = new Settings();

        // Get default size from current monitor
        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        int width = gd.getDisplayMode().getWidth();
        int height = gd.getDisplayMode().getHeight();
        settings.getProjectorSettings().setWidth(width);
        settings.getProjectorSettings().setHeight(height);

        return settings;
    }

    public Settings getSettings() {
        return settings;
    }
}
