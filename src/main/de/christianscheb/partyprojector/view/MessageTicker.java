package de.christianscheb.partyprojector.view;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.scene.CacheHint;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Duration;

public class MessageTicker extends Pane {

    public static final int DEFAULT_FONT_SIZE = 48;
    public static final String DEFAULT_FONT_FAMILY = "Sans-Serif";

    private final HBox textFlow;
    private MessageProviderInterface messageProvider;
    private Font font = new Font(DEFAULT_FONT_FAMILY, DEFAULT_FONT_SIZE);
    private Color backgroundColor = Color.WHITE;
    private Color textColor = Color.BLACK;
    private Timeline timeline;

    public MessageTicker() {
        setStyle("-fx-background-color: #" + backgroundColor.toString().substring(2));

        textFlow = new HBox();
        textFlow.setSpacing(font.getSize() * 2);
        textFlow.setPadding(new Insets(5, 0, 5, 0));
        textFlow.setCache(true);
        textFlow.setCacheShape(true);
        textFlow.setCacheHint(CacheHint.SPEED);
        textFlow.layoutBoundsProperty().addListener(e -> {
            System.out.println(textFlow.getLayoutBounds());
            animate();
        });
        getChildren().add(textFlow);
    }

    public void setMessageProvider(MessageProviderInterface messageProvider) {
        this.messageProvider = messageProvider;
    }

    public void play() {
        addMessages();
    }

    private void addMessages() {
        if (messageProvider == null) {
            return;
        }

        String[] messages = messageProvider.getMessages();
        for (String message : messages) {
            Label messageLabel = createTextElement(message);
            textFlow.getChildren().add(messageLabel);
        }
    }

    private void animate() {
        if (timeline != null) {
            timeline.stop();
        }

        double currentXPosition = textFlow.getTranslateX();
        KeyValue initkv = new KeyValue(textFlow.translateXProperty(), currentXPosition);
        KeyFrame initkf = new KeyFrame(Duration.ZERO, initkv);

        KeyValue endkv = new KeyValue(textFlow.translateXProperty(), currentXPosition - getScrollSpeed() * 10);
        KeyFrame endkf = new KeyFrame(Duration.seconds(10), endkv);

        timeline = new Timeline(initkf, endkf);
        timeline.play();
        timeline.setOnFinished(e -> animate());
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

    }

    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
        setStyle("-fx-background-color: #" + backgroundColor.toString().substring(2));
    }

    public void setTextColor(Color textColor) {
        this.textColor = textColor;
        textFlow.getChildren().stream().filter(message -> message instanceof Label).forEach(message -> ((Label) message).setTextFill(textColor));
    }

    public double getScrollSpeed() {
        return font.getSize() * 2;
    }
}
