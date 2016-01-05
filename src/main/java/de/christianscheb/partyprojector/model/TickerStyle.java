package de.christianscheb.partyprojector.model;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.io.Serializable;

public class TickerStyle implements Serializable {

    public static final String DEFAULT_FONT_FAMILY = "Sans-Serif";
    public static final int DEFAULT_FONT_SIZE = 48;

    private String fontFamily = DEFAULT_FONT_FAMILY;
    private int fontSize = DEFAULT_FONT_SIZE;
    private SerializableColor backgroundColor;
    private SerializableColor textColor;

    public TickerStyle() {
        backgroundColor = new SerializableColor(Color.RED);
        textColor = new SerializableColor(Color.BLACK);
    }

    public TickerStyle(String fontFamily, int fontSize, Color backgroundColor, Color textColor) {
        this.fontFamily = fontFamily;
        this.fontSize = fontSize;
        this.backgroundColor = new SerializableColor(backgroundColor);
        this.textColor = new SerializableColor(textColor);
    }

    public String getFontFamily() {
        return fontFamily;
    }

    public void setFontFamily(String fontFamily) {
        this.fontFamily = fontFamily;
    }

    public int getFontSize() {
        return fontSize;
    }

    public void setFontSize(int fontSize) {
        this.fontSize = fontSize;
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

    public Font getFont() {
        return new Font(fontFamily, fontSize);
    }
}
