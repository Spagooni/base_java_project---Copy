package edu.curtin.app.Factory;
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
            logger.log(Level.WARNING, "No railway found between {0} and {1} for duplication.", new Object[]{townA, townB});
            return;
        }

        if (!railway.isDualTrack()) {
            railway.upgradeToDualTrack();
            String message = "railway-duplication " + townA + " " + townB;
            logger.log(Level.INFO, "Railway duplication initiated between {0} and {1}.", new Object[]{townA, townB});
            
            sim.getMessagesReceived().add(message);
            sim.notifyObservers(message);
        } else {
            logger.log(Level.INFO, "Railway between {0} and {1} is already dual-track.", new Object[]{townA, townB});
        }
    }
}
