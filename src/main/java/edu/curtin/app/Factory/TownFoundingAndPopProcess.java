package edu.curtin.app.Factory;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import edu.curtin.app.Simulation;
import edu.curtin.app.Town;

public class TownFoundingAndPopProcess implements Process {
    private static final Logger logger = Logger.getLogger(TownFoundingAndPopProcess.class.getName());

    @Override
    public void process(Simulation sim, String townName, String process) {
        try {
            int population = Integer.parseInt(process);

            Map<String, Town> towns = sim.getTowns();
            Town town = towns.getOrDefault(townName, new Town(townName, population));
            town.setPopulation(population);
            towns.put(townName, town);

            String message = "town-population " + townName + " " + population;
            logger.log(Level.INFO, "Town {0} population updated to {1}.", new Object[]{townName, population});

            sim.getMessagesReceived().add(message);
            sim.notifyObservers(message);
        } catch (NumberFormatException e) {
            logger.log(Level.SEVERE, "Invalid population format for town {0}: {1}", new Object[]{townName, process});
        }
    }
}
