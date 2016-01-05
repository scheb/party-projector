package de.christianscheb.partyprojector.model;

import org.ini4j.Wini;

public class ProjectorSettings implements IniSerializable {

    public static final String INI_SECTION_PROJECTOR = "projector";
    public static final String INI_VALUE_POSX = "posX";
    public static final String INI_VALUE_POSY = "posY";
    public static final String INI_VALUE_WIDTH = "width";
    public static final String INI_VALUE_HEIGHT = "height";

    private int posX = 0;
    private int posY = 0;
    private int width = 0;
    private int height = 0;

    public ProjectorSettings(int posX, int posY, int width, int height) {
        this.posX = posX;
        this.posY = posY;
        this.width = width;
        this.height = height;
    }

    @Override
    public void serialize(Wini ini) {
        ini.put(INI_SECTION_PROJECTOR, INI_VALUE_POSX, posX);
        ini.put(INI_SECTION_PROJECTOR, INI_VALUE_POSY, posY);
        ini.put(INI_SECTION_PROJECTOR, INI_VALUE_WIDTH, width);
        ini.put(INI_SECTION_PROJECTOR, INI_VALUE_HEIGHT, height);
    }

    @Override
    public void unserialize(Wini ini) {
        posX = ini.get(INI_SECTION_PROJECTOR, INI_VALUE_POSX, int.class);
        posY = ini.get(INI_SECTION_PROJECTOR, INI_VALUE_POSY, int.class);
        width = ini.get(INI_SECTION_PROJECTOR, INI_VALUE_WIDTH, int.class);
        height = ini.get(INI_SECTION_PROJECTOR, INI_VALUE_HEIGHT, int.class);
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
