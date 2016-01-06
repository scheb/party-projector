package de.christianscheb.partyprojector.view;

import de.christianscheb.partyprojector.model.PictureStorage;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.geometry.Bounds;
import javafx.scene.CacheHint;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
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
        double preferredHeight = bounds.getHeight() * 0.9;
        double posY = (bounds.getHeight() - preferredHeight) / 2;

        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(preferredHeight);
        imageView.setFitWidth(bounds.getWidth());
        imageView.setPreserveRatio(true);
        imageView.setCache(true);
        imageView.setCacheHint(CacheHint.SPEED);
        imageView.setY(posY);

        DropShadow dropShadow = new DropShadow();
        dropShadow.setRadius(posY / 3 * 2);
        dropShadow.setOffsetX(posY / 3);
        dropShadow.setOffsetY(posY / 3);
        dropShadow.setColor(Color.BLACK);
        imageView.setEffect(dropShadow);

        getChildren().add(imageView);
        double pictureWidth = imageView.getLayoutBounds().getWidth();
        imageView.setTranslateX(-pictureWidth);
        double middle = (bounds.getWidth() - pictureWidth) / 2;

        animation = new Timeline();
        KeyFrame kf1 = createKeyFrame(imageView, 0, imageView.getTranslateX());
        KeyFrame kf2 = createKeyFrame(imageView, 400, middle * 1.3);
        KeyFrame kf3 = createKeyFrame(imageView, 500, middle);
        KeyFrame kf4 = createKeyFrame(imageView, 5000, middle);
        KeyFrame kf5 = createKeyFrame(imageView, 5100, middle * 0.7);
        KeyFrame kf6 = createKeyFrame(imageView, 5500, bounds.getWidth());
        animation.getKeyFrames().addAll(kf1, kf2, kf3, kf4, kf5, kf6);
        animation.setOnFinished(e -> resetAndShowNextPicture());
        animation.play();
    }

    private KeyFrame createKeyFrame(ImageView image, int millis, double position) {
        final KeyValue kv = new KeyValue(image.translateXProperty(), position);
        return new KeyFrame(Duration.millis(millis), kv);
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
