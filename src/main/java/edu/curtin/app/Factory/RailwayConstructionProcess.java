package edu.curtin.app.Factory;
import java.util.Map;

import edu.curtin.app.Railway;
import edu.curtin.app.Simulation;
import edu.curtin.app.Town;


public class RailwayConstructionProcess implements Process {
    @Override
    public void process(Simulation sim, String townA, String townB){
        Map<String, Town> towns = sim.getTowns(); 
        Map<String, Railway> railways = sim.getRailways(); 
        
        Railway railway = new Railway(towns.get(townA), towns.get(townB));
        railways.put(townA + "-" + townB, railway);
        String message = "railway-construction " + townA + " " + townB;
        sim.getMessagesReceived().add(message);
        sim.notifyObservers(message); 
    }
}