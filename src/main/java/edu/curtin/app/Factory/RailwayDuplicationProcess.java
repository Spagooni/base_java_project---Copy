package edu.curtin.app.Factory;
import java.util.Map;

import edu.curtin.app.Railway;
import edu.curtin.app.Simulation;


public class RailwayDuplicationProcess implements Process {
    
    @Override
    public void process(Simulation sim, String townA, String townB){
        Map<String, Railway> railways = sim.getRailways(); 

        Railway railway = railways.get(townA + "-" + townB);
        if (railway != null && !railway.isDualTrack()) {
            railway.upgradeToDualTrack();
            String message = "railway-duplication " + townA + " " + townB;
            sim.getMessagesReceived().add(message);
            sim.notifyObservers(message);
        }
    }
}