package de.christianscheb.partyprojector.view;

import javafx.animation.AnimationTimer;
import javafx.geometry.Insets;
import javafx.scene.CacheHint;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class MessageTicker extends Pane {

    public static final int DEFAULT_FONT_SIZE = 48;
    public static final String DEFAULT_FONT_FAMILY = "Sans-Serif";

    private final HBox textFlow;
    private MessageProviderInterface messageProvider;
    private Font font = new Font(DEFAULT_FONT_FAMILY, DEFAULT_FONT_SIZE);
    private Color backgroundColor = Color.WHITE;
    private Color textColor = Color.BLACK;
    private AnimationTimer timer;

    public MessageTicker() {
        setBackgroundColor(this.backgroundColor);

        textFlow = new HBox();
        textFlow.setSpacing(getMessageSpacing());
        textFlow.setPadding(new Insets(5, 0, 5, 0));
        textFlow.setCache(true);
        textFlow.setCacheShape(true);
        textFlow.setCacheHint(CacheHint.SPEED);
        getChildren().add(textFlow);

        timer = new AnimationTimer() {
            public long lastUpdate = 0 ;
            @Override
            public void handle(long time) {
                if (lastUpdate > 0) {
                    long elapsedNanos = time - lastUpdate;
                    double elapsedSeconds = elapsedNanos / 1_000_000_000.0;
                    onAnimationFrame(elapsedSeconds);
                }
                lastUpdate = time;
            }

            @Override
            public void stop() {
                lastUpdate = 0;
                super.stop();
            }
        };
    }

    public void start() {
        timer.start();
    }

    public void stop() {
        timer.stop();
    }

    public void setMessageProvider(MessageProviderInterface messageProvider) {
        this.messageProvider = messageProvider;
    }

    private void addMessages() {
        if (messageProvider == null) {
            return;
        }

        String message = messageProvider.getMessage();
        Label messageLabel = createTextElement(message);
        textFlow.getChildren().add(messageLabel);
    }

    private void onAnimationFrame(double elapsedSeconds) {
        animate(elapsedSeconds);
        if (canRemoveElementOnTheLeft()) {
            removeElementOnTheLeft();
        }
        if (addElementOnTheRight()) {
            addMessages();
        }
    }

    private void animate(double elapsedSeconds) {
        if (getTextWidth() <= getViewWidth()) {
            textFlow.setTranslateX(0);
            return;
        }

        textFlow.setTranslateX(textFlow.getTranslateX() - getScrollSpeed() * elapsedSeconds);
    }

    private boolean canRemoveElementOnTheLeft() {
        if (textFlow.getChildren().size() == 0) {
            return false;
        }

        Node firstElement = textFlow.getChildren().get(0);
        double firstElementWidth = firstElement.getLayoutBounds().getWidth();
        double translateX = Math.abs(textFlow.getTranslateX());
        return firstElementWidth + textFlow.getSpacing() < translateX;
    }

    private void removeElementOnTheLeft() {
        if (textFlow.getChildren().size() == 0) {
            return;
        }

        Node firstElement = textFlow.getChildren().get(0);
        double firstElementWidth = firstElement.getLayoutBounds().getWidth();
        textFlow.setTranslateX(textFlow.getTranslateX() + firstElementWidth + getMessageSpacing());
        textFlow.getChildren().remove(0);
    }

    private boolean addElementOnTheRight() {
        double translateX = textFlow.getTranslateX();
        return getTextWidth() + translateX < getViewWidth();
    }

    private Label createTextElement(String text) {
        Label marquee_text = new Label(text);
        marquee_text.setTextFill(textColor);
        marquee_text.setFont(font);
        return marquee_text;
    }

    public void setFont(Font font) {
        this.font = font;
        textFlow.getChildren().stream().filter(message -> message instanceof Label).forEach(message -> ((Label) message).setFont(font));
        textFlow.setSpacing(getMessageSpacing());
    }

    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
        setStyle("-fx-background-color: #" + backgroundColor.toString().substring(2));
    }

    public void setTextColor(Color textColor) {
        this.textColor = textColor;
        textFlow.getChildren().stream().filter(message -> message instanceof Label).forEach(message -> ((Label) message).setTextFill(textColor));
    }

    private double getScrollSpeed() {
        return font.getSize() * 2;
    }

    private double getMessageSpacing() {
        return font.getSize() * 3;
    }

    public double getTextWidth() {
        return textFlow.getLayoutBounds().getWidth();
    }

    public double getViewWidth() {
        return getLayoutBounds().getWidth();
    }
}
