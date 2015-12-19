package de.christianscheb.partyprojector.model;

import javafx.scene.paint.Color;

import java.io.Serializable;

public class SerializableColor implements Serializable {

    private transient Color fxColor;
    private final double red;
    private final double green;
    private final double blue;
    private final double opacity;

    public SerializableColor(Color source) {
        this.fxColor = source;
        this.red = source.getRed();
        this.green = source.getGreen();
        this.blue = source.getBlue();
        this.opacity = source.getOpacity();
    }

    public Color getFxColor() {
        if (fxColor == null) {
            fxColor = new Color(red, green, blue, opacity);
        }

        return fxColor;
    }
}
