package edu.curtin.app.states;

import edu.curtin.app.Railway;
import edu.curtin.app.Town;

public class ConstructionState extends RailwayState{

    public ConstructionState(Railway railway, Town townA, Town townB){
        super(railway, townA, townB);
    }


    @Override
    public void Upgrade(){

    }
    
    @Override
    public void Construct(){
        if(railway.getDaysToComplete() > 0){
            // System.out.println("Days Remaining to build: " + daysToComplete);
            railway.reduceDaysToComplete();
            railway.setIsBuilding(true);
        }
        else if(railway.getDaysToComplete() == 0){
            railway.setIsCompleted(true);
            townA.incrementSingleTrackRailways();
            townB.incrementSingleTrackRailways();
            railway.reduceDaysToComplete();
            railway.setIsBuilding(false);
            railway.setIdleState();
        }
    }
}