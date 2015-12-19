package de.christianscheb.partyprojector.model;

import javafx.scene.paint.Color;

public class TickerStyle {

    private Color backgroundColor;
    private Color textColor;

    public TickerStyle() {
        backgroundColor = Color.WHITE;
        textColor = Color.BLACK;
    }

    public TickerStyle(Color backgroundColor, Color textColor) {
        this.backgroundColor = backgroundColor;
        this.textColor = textColor;
    }

    public Color getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public Color getTextColor() {
        return textColor;
    }

    public void setTextColor(Color textColor) {
        this.textColor = textColor;
    }
}
