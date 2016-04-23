package de.christianscheb.partyprojector.model;

import de.christianscheb.partyprojector.view.PictureProviderInterface;
import javafx.scene.image.Image;

import java.io.*;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayDeque;
import java.util.Calendar;

public class PictureStorage implements PictureProviderInterface {

    private final File pictureDir;
    private ArrayDeque<Image> pictures = new ArrayDeque<>();

    public PictureStorage() {
        pictureDir = new File(System.getProperty("user.dir") + "/pictures");
        if (!pictureDir.exists()) {
            pictureDir.mkdirs();
        }
    }

    public void addImage(InputStream stream) {
        // Store image on disk
        String timeStamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(Calendar.getInstance().getTime());
        File pictureFile = new File(pictureDir, timeStamp + ".jpg");
        try {
            Files.copy(stream, pictureFile.toPath());
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        // Push to queue
        Image image;
        try {
            image = new Image(pictureFile.toURI().toURL().toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return;
        }

        if (image.isError()) {
            System.err.println("Could not read image");
            return;
        }
        pictures.push(image);
    }

    @Override
    public Image getPicture() {
        if (pictures.size() > 0) {
            return pictures.removeFirst();
        }

        return null;
    }
}
