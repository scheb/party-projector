package de.christianscheb.partywall.controller;

import de.christianscheb.partywall.model.ProjectorSettings;
import de.christianscheb.partywall.model.TickerStyle;

public interface SettingsEventListener {

    void onToggleProjector(boolean projectorActive);

    void onProjectorSettingsUpdated(ProjectorSettings projectorSettings);

    void onTickerMessageUpdated(String text);

    void onTickerStyleUpdated(TickerStyle tickerStyle);
}
