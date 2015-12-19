package de.christianscheb.partyprojector.model;

import javafx.scene.paint.Color;

import java.io.Serializable;

public class TickerStyle implements Serializable {

    private SerializableColor backgroundColor;
    private SerializableColor textColor;

    public TickerStyle() {
        backgroundColor = new SerializableColor(Color.RED);
        textColor = new SerializableColor(Color.BLACK);
    }

    public TickerStyle(Color backgroundColor, Color textColor) {
        this.backgroundColor = new SerializableColor(backgroundColor);
        this.textColor = new SerializableColor(textColor);
    }

    public Color getBackgroundColor() {
        return backgroundColor.getFxColor();
    }

    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = new SerializableColor(backgroundColor);
    }

    public Color getTextColor() {
        return textColor.getFxColor();
    }

    public void setTextColor(Color textColor) {
        this.textColor = new SerializableColor(textColor);
    }
}
