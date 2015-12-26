package de.christianscheb.partyprojector.model;

import de.christianscheb.partyprojector.view.MessageProviderInterface;

public class MessageProvider implements MessageProviderInterface {

    @Override
    public String[] getMessages() {
        return new String[]{
                "Israel plant Gesetz gegen regierungskritische Organisationen",
                "Abzug von IS-Kämpfern aus Damaskus gestoppt",
                "Ein Toter und drei Verletzte nach Gewalttat im Wedding"
        };
    }
}
