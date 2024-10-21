package edu.curtin.app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import edu.curtin.app.observers.Observer;

public class Simulation {
    private static final Logger logger = Logger.getLogger(Simulation.class.getName());

    private final Map<String, Town> towns = new HashMap<>();
    private final Map<String, Railway> railways = new HashMap<>();
    private int day = 0;
    private final List<String> messagesReceived = new ArrayList<>();
    private final List<Observer> observers = new ArrayList<>();

    public Map<String, Town> getTowns() {
        return towns;
    }

    public Map<String, Railway> getRailways() {
        return railways;
    }

    public List<String> getMessagesReceived() {
        return messagesReceived;
    }

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    public void notifyObservers(String message) {
        for (Observer observer : observers) {
            try {
                observer.update(message);
            } catch (Exception e) {
                logger.log(Level.SEVERE, "Failed to notify observer: " + observer, e);
            }
        }
    }

    public void simulateDay() {
        day++;
        System.out.println("---");
        System.out.println("Day " + day + ":");

        try {
            for (String msg : messagesReceived) {
                System.out.println(msg);
            }

            for (Town town : towns.values()) {
                town.produceGoods();  // Producing goods
            }

            for (Railway railway : railways.values()) {
                try {
                    railway.advanceConstruction();
                    railway.advanceUpgrade();
                    if (railway.isCompleted()) {
                        railway.transportGoods();
                    }
                } catch (Exception e) {
                    logger.log(Level.SEVERE, "Error advancing railway state for " +
                            railway.getTownA().getName() + " - " + railway.getTownB().getName(), e);
                }
            }

            System.out.println();
            for (Town town : towns.values()) {
                try {
                    System.out.printf("%s p:%d rs:%d rd:%d gs:%d gt:%d%n",
                            town.getName(),
                            town.getPopulation(),
                            town.getSingleTrackRailways(),
                            town.getDualTrackRailways(),
                            town.getGoodsStockpile(),
                            town.getGoodsTransportedToday());

                    town.resetGoodsTransportedToday();
                } catch (Exception e) {
                    logger.log(Level.SEVERE, "Error printing town details for " + town.getName(), e);
                }
            }

            messagesReceived.clear();

            DotFileWriter.writeDotFile(towns, railways, "simoutput.dot");

        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error simulating day " + day, e);
        }
    }
}
