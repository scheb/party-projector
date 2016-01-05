package de.christianscheb.partyprojector.model;

import de.christianscheb.partyprojector.view.MessageProviderInterface;
import java.util.ArrayList;

public class MessageStorage implements MessageProviderInterface {

    public static final int MAX_MESSAGES = 5;
    private String staticMessage = null;
    private ArrayList<String> messages = new ArrayList<>();

    public void setStaticMessage(String text) {
        staticMessage = text;
    }

    public void addMessage(String text) {
        if (messages.size() == MAX_MESSAGES) {
            messages.remove(0);
        }

        messages.add(text);
    }

    @Override
    public String[] getMessages() {
        try {
            if (staticMessage != null) {
                messages.add(0, staticMessage); // Add static message at the beginning
            }
            return messages.toArray(new String[messages.size()]);
        } finally {
            if (staticMessage != null) {
                messages.remove(0); // Remove static message
            }
        }
    }
}
