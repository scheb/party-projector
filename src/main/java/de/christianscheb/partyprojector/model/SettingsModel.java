package de.christianscheb.partyprojector.model;

import java.awt.*;
import java.io.*;

public class SettingsModel {

    public static final String SETTINGS_FILE = "settings.dat";
    private Settings settings;

    public SettingsModel() {
        settings = loadSettings();
    }

    private Settings loadSettings() {
        try {
            FileInputStream in = new FileInputStream(SETTINGS_FILE);
            ObjectInputStream ins = new ObjectInputStream(in);
            Settings settings = (Settings)ins.readObject();
            in.close();
            return settings;
        } catch (FileNotFoundException e) {
            return getDefaultSettings();
        } catch (IOException e) {
            e.printStackTrace();
            return getDefaultSettings();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return getDefaultSettings();
        }
    }

    public void persistSettings() {
        try {
            FileOutputStream out = new FileOutputStream(SETTINGS_FILE);
            ObjectOutputStream os = new ObjectOutputStream(out);
            os.writeObject(settings);
            out.close();
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
