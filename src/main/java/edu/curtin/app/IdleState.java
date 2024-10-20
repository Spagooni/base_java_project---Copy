package edu.curtin.app;

public class IdleState implements RailwayState {
    @Override
    public void advance(Railway railway) {
        // No construction or upgrades in Idle state
    }

    @Override
    public void upgrade(Railway railway) {
        // Start the upgrade process
        railway.setState(new UpgradeState());
    }
}
