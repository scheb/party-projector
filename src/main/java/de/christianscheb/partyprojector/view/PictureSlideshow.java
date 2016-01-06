package de.christianscheb.partyprojector.view;

import de.christianscheb.partyprojector.model.PictureStorage;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.geometry.Bounds;
import javafx.scene.CacheHint;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class PictureSlideshow extends Pane {

    private PictureStorage pictureProvider;
    private Timeline picturePolling;
    private Timeline animation;

    public PictureSlideshow() {
        picturePolling = new Timeline(new KeyFrame(Duration.seconds(1), e -> showPictureWhenNotBusy()));
        picturePolling.setCycleCount(Timeline.INDEFINITE);
    }

    private void showPictureWhenNotBusy() {
        if (animation != null) {
            return;
        }

        Image picture = pictureProvider.getPicture();
        if (picture != null) {
            showPicture(picture);
        }
    }

    private void showPicture(Image image) {
        Bounds bounds = getLayoutBounds();

        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(bounds.getHeight());
        imageView.setFitWidth(bounds.getWidth());
        imageView.setPreserveRatio(true);
        imageView.setCache(true);
        imageView.setCacheHint(CacheHint.SPEED);
        getChildren().add(imageView);
        double pictureWidth = imageView.getLayoutBounds().getWidth();
        imageView.setTranslateX(-pictureWidth);
        double middle = (bounds.getWidth() - pictureWidth) / 2;

        animation = new Timeline();
        KeyFrame kf1 = createKeyFrame(imageView, 0, imageView.getTranslateX());
        KeyFrame kf2 = createKeyFrame(imageView, 1, middle);
        KeyFrame kf3 = createKeyFrame(imageView, 6, middle);
        KeyFrame kf4 = createKeyFrame(imageView, 7, bounds.getWidth());
        animation.getKeyFrames().addAll(kf1, kf2, kf3, kf4);
        animation.setOnFinished(e -> resetAndShowNextPicture());
        animation.play();
    }

    private KeyFrame createKeyFrame(ImageView image, int seconds, double position) {
        final KeyValue kv = new KeyValue(image.translateXProperty(), position);
        return new KeyFrame(Duration.seconds(seconds), kv);
    }

    private void resetAndShowNextPicture() {
        getChildren().remove(0);
        Image picture = pictureProvider.getPicture();
        if (picture != null) {
            showPicture(picture);
        } else {
            animation = null;
        }
    }

    public void start() {
        picturePolling.play();
    }

    public void stop() {
        picturePolling.stop();
        if (animation != null) {
            animation.stop();
            animation = null;
        }
    }

    public void setPictureProvider(PictureStorage pictureProvider) {
        this.pictureProvider = pictureProvider;
    }
}
