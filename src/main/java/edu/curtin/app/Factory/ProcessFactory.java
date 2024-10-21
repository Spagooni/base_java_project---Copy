package edu.curtin.app.Factory;
import java.util.logging.Level;
import java.util.logging.Logger;

import edu.curtin.app.Simulation;

public class ProcessFactory {
    private static final Logger logger = Logger.getLogger(ProcessFactory.class.getName());

    public static void processMessage(Simulation sim, String msg) {
        String[] parts = msg.split(" ");
        if (parts.length != 3) {
            logger.log(Level.WARNING, "Invalid message format: {0}", msg);
            return;
        }

        String type = parts[0];
        String townA = parts[1];
        String thirdPart = parts[2];

        switch (type) {
            case "town-founding", "town-population" -> {
                TownFoundingAndPopProcess townFoundingProcess = new TownFoundingAndPopProcess();
                logger.log(Level.INFO, "Processing town population/founding message: {0}", msg);
                townFoundingProcess.process(sim, townA, thirdPart);
            }
            case "railway-construction" -> {
                RailwayConstructionProcess railwayConstructionProcess = new RailwayConstructionProcess();
                logger.log(Level.INFO, "Processing railway construction message: {0}", msg);
                railwayConstructionProcess.process(sim, townA, thirdPart);
            }
            case "railway-duplication" -> {
                RailwayDuplicationProcess railwayDuplicationProcess = new RailwayDuplicationProcess();
                logger.log(Level.INFO, "Processing railway duplication message: {0}", msg);
                railwayDuplicationProcess.process(sim, townA, thirdPart);
            }
            default -> {
                logger.log(Level.WARNING, "Unknown message type: {0}", type);
            }
        }
    }
}
