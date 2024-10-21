package edu.curtin.app.states;

import edu.curtin.app.Railway;
import edu.curtin.app.Town;

public class UpgradeState extends RailwayState {

    public UpgradeState(Railway railway, Town townA, Town townB) {
        super(railway, townA, townB);
        logStateChange("UpgradeState");
    }

    @Override
    public void Upgrade() {
        try {
            if (railway.getDaysToUpgrade() > 0 && railway.getIsUpgrading()) {
                railway.reduceDaysToUpgrade();
            } else if (railway.getDaysToUpgrade() == 0) {
                railway.setIsDualTrack(true);
                townA.incrementDualTrackRailways();
                townB.incrementDualTrackRailways();
                townA.decrementSingleTrackRailways();
                townB.decrementSingleTrackRailways();
                railway.setIsUpgrading(false);
                railway.setIdleState();
                logStateChange("Upgrade complete, switching to IdleState");
            }
        } catch (Exception e) {
            logError("Error during upgrade process", e);
        }
    }

    @Override
    public void Construct() {
        logStateChange("Construct called in UpgradeState, but no action.");
    }
}
