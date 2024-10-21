package edu.curtin.app.states;

import edu.curtin.app.Railway;
import edu.curtin.app.Town;

public class ConstructionState extends RailwayState {

    public ConstructionState(Railway railway, Town townA, Town townB) {
        super(railway, townA, townB);
        logStateChange("ConstructionState");
    }

    @Override
    public void Upgrade() {
        logStateChange("Upgrade called in ConstructionState, but no action.");
    }

    @Override
    public void Construct() {
        try {
            if (railway.getDaysToComplete() > 0) {
                railway.reduceDaysToComplete();
                railway.setIsBuilding(true);
            } else if (railway.getDaysToComplete() == 0) {
                railway.setIsCompleted(true);
                townA.incrementSingleTrackRailways();
                townB.incrementSingleTrackRailways();
                railway.reduceDaysToComplete();
                railway.setIsBuilding(false);
                railway.setIdleState();
                logStateChange("Construction complete, switching to IdleState");
            }
        } catch (Exception e) {
            logError("Error during construction process", e);
        }
    }
}
