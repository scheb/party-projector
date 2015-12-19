package de.christianscheb.partyprojector.controller;

import de.christianscheb.partyprojector.model.ProjectorSettings;
import de.christianscheb.partyprojector.model.TickerStyle;

public interface SettingsEventListener {

    void onToggleProjector(boolean projectorActive);

    void onProjectorSettingsUpdated(ProjectorSettings projectorSettings);

    void onTickerMessageUpdated(String text);

    void onTickerStyleUpdated(TickerStyle tickerStyle);
}
