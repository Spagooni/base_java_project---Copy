package edu.curtin.app.factory;

import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import edu.curtin.app.Railway;
import edu.curtin.app.Simulation;

public class RailwayDuplicationProcess implements Process {
    private static final Logger logger = Logger.getLogger(RailwayDuplicationProcess.class.getName());

    @Override
    public void process(Simulation sim, String townA, String townB) {
        Map<String, Railway> railways = sim.getRailways();
        Railway railway = railways.get(townA + "-" + townB);

        if (railway == null) {
            logger.log(Level.WARNING, () -> String.format("No railway found between %s and %s for duplication.", townA, townB));
            return;
        }

        if (railway.isDualTrack()) {
            logger.log(Level.INFO, () -> String.format("Railway between %s and %s is already dual-track.", townA, townB));
            return;
        }

        railway.upgradeToDualTrack();
        String message = "railway-duplication " + townA + " " + townB;
        logger.log(Level.INFO, () -> String.format("Railway duplication initiated between %s and %s.", townA, townB));

        sim.getMessagesReceived().add(message);
        sim.notifyObservers(message);
    }
}
