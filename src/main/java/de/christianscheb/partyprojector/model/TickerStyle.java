package de.christianscheb.partyprojector.model;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import org.ini4j.Wini;

import java.io.Serializable;

public class TickerStyle implements IniSerializable {

    public static final String INI_SECTION_TICKER = "ticker";
    public static final String INI_VALUE_FONT_FAMILY = "fontFamily";
    public static final String INI_VALUE_FONT_SIZE = "fontSize";
    public static final String INI_VALUE_BACKGROUND_COLOR = "backgroundColor";
    public static final String INI_VALUE_TEXT_COLOR = "textColor";

    public static final String DEFAULT_FONT_FAMILY = "Sans-Serif";
    public static final int DEFAULT_FONT_SIZE = 48;

    private String fontFamily = DEFAULT_FONT_FAMILY;
    private int fontSize = DEFAULT_FONT_SIZE;
    private Color backgroundColor;
    private Color textColor;

    public TickerStyle() {
        backgroundColor = Color.RED;
        textColor = Color.BLACK;
    }

    public TickerStyle(String fontFamily, int fontSize, Color backgroundColor, Color textColor) {
        this.fontFamily = fontFamily;
        this.fontSize = fontSize;
        this.backgroundColor = backgroundColor;
        this.textColor = textColor;
    }

    @Override
    public void serialize(Wini ini) {
        ini.put(INI_SECTION_TICKER, INI_VALUE_FONT_FAMILY, this.fontFamily);
        ini.put(INI_SECTION_TICKER, INI_VALUE_FONT_SIZE, fontSize);
        ini.put(INI_SECTION_TICKER, INI_VALUE_BACKGROUND_COLOR, toRGBCode(backgroundColor));
        ini.put(INI_SECTION_TICKER, INI_VALUE_TEXT_COLOR, toRGBCode(textColor));
    }

    @Override
    public void unserialize(Wini ini) {
        String fontFamily = ini.get(INI_SECTION_TICKER, INI_VALUE_FONT_FAMILY);
        if (fontFamily != null && fontFamily.length() > 0) {
            this.fontFamily = fontFamily;
        }

        int fontSize = ini.get(INI_SECTION_TICKER, INI_VALUE_FONT_SIZE, int.class);
        if (fontSize > 0) {
            this.fontSize = fontSize;
        }

        String backgroundColor = ini.get(INI_SECTION_TICKER, INI_VALUE_BACKGROUND_COLOR);
        if (backgroundColor != null && backgroundColor.length() > 0) {
            this.backgroundColor = Color.web(backgroundColor);
        }

        String textColor = ini.get(INI_SECTION_TICKER, INI_VALUE_TEXT_COLOR);
        if (textColor != null && textColor.length() > 0) {
            this.textColor = Color.web(textColor);
        }
    }

    private String toRGBCode(Color color) {
        return String.format("#%02X%02X%02X",
                (int) (color.getRed() * 255),
                (int) (color.getGreen() * 255),
                (int) (color.getBlue() * 255));
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

    public Font getFont() {
        return new Font(fontFamily, fontSize);
    }
}
