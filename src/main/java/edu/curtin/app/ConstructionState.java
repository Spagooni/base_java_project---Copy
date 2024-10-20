package edu.curtin.app;

public class ConstructionState implements RailwayState {
    @Override
    public void advance(Railway railway) {
        railway.advanceConstruction();
        if (railway.isCompleted()) {
            railway.setState(new IdleState()); 
        }
    }

    @Override
    public void upgrade(Railway railway) {
        System.out.println("Cannot upgrade while under construction.");
    }
}
