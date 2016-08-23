package de.christianscheb.partyprojector.view;

import javafx.stage.Stage;

public class SwitchStage extends Stage {

    private int clients = 0;

    public void enable() {
        if (clients == 0) {
            show();
        }
        ++clients;
    }

    public void disable() {
        --clients;
        if (clients == 0) {
            hide();
        }
    }
}
