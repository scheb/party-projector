package de.christianscheb.partyprojector.model;

import de.christianscheb.partyprojector.view.MessageProviderInterface;

import java.util.ArrayDeque;
import java.util.ArrayList;

public class MessageStorage implements MessageProviderInterface {

    public static final int MAX_REPEATABLE_MESSAGES = 5;
    private boolean hasStaticMessage = false;
    private int repeatPosition = -1;
    private ArrayList<String> repeatMessages = new ArrayList<>();
    private ArrayDeque<String> messages = new ArrayDeque<>();

    public void setStaticMessage(String text) {
        if (hasStaticMessage) {
            repeatMessages.remove(0);
        }

        if (text != null && text.length() > 0) {
            hasStaticMessage = true;
            if (repeatMessages.size() == MAX_REPEATABLE_MESSAGES) {
                repeatMessages.remove(MAX_REPEATABLE_MESSAGES -1 );
            }
            repeatMessages.add(0, text);
        } else {
            hasStaticMessage = false;
        }
    }

    public void addMessage(String text) {
        messages.add(text);
    }

    @Override
    public String getMessage() {
        String message = getMessageFromQueue();
        if (message != null) {
            return message;
        }

        return getRepeatingMessage();
    }

    private String getMessageFromQueue() {
        if (messages.size() == 0) {
            return null;
        }

        String first = messages.removeFirst();
        addRepeatingMessage(first);
        return first;
    }

    private void addRepeatingMessage(String text) {
        if (repeatMessages.size() == MAX_REPEATABLE_MESSAGES) {
            repeatMessages.remove(MAX_REPEATABLE_MESSAGES -1 );
        }
        repeatMessages.add(hasStaticMessage ? 1 : 0, text);
        ++repeatPosition;
    }

    private String getRepeatingMessage() {
        if (repeatMessages.size() == 0) {
            return null;
        }
        repeatPosition = (repeatPosition + 1) % Math.min(repeatMessages.size(), MAX_REPEATABLE_MESSAGES);
        return repeatMessages.get(repeatPosition);
    }
}
