package edu.curtin.app.Factory;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import edu.curtin.app.Railway;
import edu.curtin.app.Simulation;
import edu.curtin.app.Town;

public class RailwayConstructionProcess implements Process {
    private static final Logger logger = Logger.getLogger(RailwayConstructionProcess.class.getName());

    @Override
    public void process(Simulation sim, String townA, String townB) {
        Map<String, Town> towns = sim.getTowns();
        Map<String, Railway> railways = sim.getRailways();

        if (towns.get(townA) == null || towns.get(townB) == null) {
            logger.log(Level.WARNING, "Invalid towns: {0}, {1} for railway construction.", new Object[]{townA, townB});
            return;
        }

        Railway railway = new Railway(towns.get(townA), towns.get(townB));
        railway.initialize();
        railways.put(townA + "-" + townB, railway);

        String message = "railway-construction " + townA + " " + townB;
        logger.log(Level.INFO, "Railway constructed between {0} and {1}.", new Object[]{townA, townB});
        
        sim.getMessagesReceived().add(message);
        sim.notifyObservers(message);
    }
}
