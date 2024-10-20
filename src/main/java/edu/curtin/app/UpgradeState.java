package edu.curtin.app;

public class UpgradeState implements RailwayState {
    @Override
    public void advance(Railway railway) {
        railway.advanceUpgrade();
        if (railway.isDualTrack()) {
            railway.setState(new IdleState()); // Move back to Idle state when upgrade is complete
        }
    }

    @Override
    public void upgrade(Railway railway) {
        // Already in upgrade state, no action needed
    }
}
