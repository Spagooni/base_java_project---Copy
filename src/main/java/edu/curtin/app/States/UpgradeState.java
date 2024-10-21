package edu.curtin.app.states;
import edu.curtin.app.Railway;
import edu.curtin.app.Town;

public class UpgradeState extends RailwayState{

    public UpgradeState(Railway railway, Town townA, Town townB){
        super(railway, townA, townB);
    }


    @Override
    public void Upgrade(){
        if(railway.getDaysToUpgrade() > 0 && railway.getIsUpgrading()){
            // System.out.println("Days Remaining to upgrade: " + daysToUpgrade);
            railway.reduceDaysToUpgrade();
        }
        else if(railway.getDaysToUpgrade() == 0){
            railway.setIsDualTrack(true);
            townA.incrementDualTrackRailways();
            townB.incrementDualTrackRailways();

            townA.decrementSingleTrackRailways();
            townB.decrementSingleTrackRailways();
            railway.reduceDaysToUpgrade();
            railway.setIsUpgrading(false);
            railway.setIdleState();
        }
    }
    
    @Override
    public void Construct(){

    }
}