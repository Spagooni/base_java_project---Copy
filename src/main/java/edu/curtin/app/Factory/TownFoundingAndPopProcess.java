package edu.curtin.app.factory;
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
            logger.log(Level.INFO, () -> String.format("Town %s population updated to %d.", townName, population));

            sim.getMessagesReceived().add(message);
            sim.notifyObservers(message);
        } catch (NumberFormatException e) {
            logger.log(Level.SEVERE, () -> String.format("Invalid population format for town %s: %s", townName, process));
        }
    }
}