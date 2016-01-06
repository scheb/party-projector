package de.christianscheb.partyprojector.model;

import de.christianscheb.partyprojector.view.PictureProviderInterface;
import javafx.scene.image.Image;

import java.io.InputStream;
import java.util.ArrayDeque;

public class PictureStorage implements PictureProviderInterface {

    private ArrayDeque<Image> pictures = new ArrayDeque<>();

    public void addImage(InputStream stream) {
        pictures.push(new Image(stream));
    }

    @Override
    public Image getPicture() {
        if (pictures.size() > 0) {
            return pictures.removeFirst();
        }

        return null;
    }
}
