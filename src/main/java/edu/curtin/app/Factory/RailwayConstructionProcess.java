package edu.curtin.app.factory;

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
            logger.log(Level.WARNING, () -> String.format("Invalid towns: %s, %s for railway construction.", townA, townB));
            return;
        }

        Railway railway = new Railway(towns.get(townA), towns.get(townB));
        railway.initialize();
        railways.put(townA + "-" + townB, railway);

        logger.log(Level.INFO, () -> String.format("Railway constructed between %s and %s.", townA, townB));

        String message = "railway-construction " + townA + " " + townB;
        sim.getMessagesReceived().add(message);
        sim.notifyObservers(message);
    }
}
