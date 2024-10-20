package edu.curtin.app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.curtin.app.observers.Observer;



public class Simulation {
    private final Map<String, Town> towns = new HashMap<>();
    private final Map<String, Railway> railways = new HashMap<>();
    private int day = 0;
    private final List<String> messagesReceived = new ArrayList<>();
    private final List<Observer> observers = new ArrayList<>(); 

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    private void notifyObservers(String message) {
        for (Observer observer : observers) {
            observer.update(message);
        }
    }

    public void processTownMessage(String townName, int population) {
        Town town = towns.getOrDefault(townName, new Town(townName, population));
        town.setPopulation(population);
        towns.put(townName, town);
        String message = "town-population " + townName + " " + population;
        messagesReceived.add(message);
        notifyObservers(message); 
    }

    public void processRailwayConstruction(String townA, String townB) {
        Railway railway = new Railway(towns.get(townA), towns.get(townB));
        railways.put(townA + "-" + townB, railway);
        String message = "railway-construction " + townA + " " + townB;
        messagesReceived.add(message);
        notifyObservers(message); 
    }

    public void processRailwayDuplication(String townA, String townB) {
        Railway railway = railways.get(townA + "-" + townB);
        if (railway != null && !railway.isDualTrack()) {
            railway.upgradeToDualTrack();
            String message = "railway-duplication " + townA + " " + townB;
            messagesReceived.add(message);
            notifyObservers(message);
        }
    }

    public void simulateDay() {
        day++;
        System.out.println("---");
        System.out.println("Day " + day + ":");

        for (String msg : messagesReceived) {
            System.out.println(msg);
        }

        for (Town town : towns.values()) {
            town.produceGoods();
        }

        for (Railway railway : railways.values()) {
            railway.advanceConstruction();
            railway.advanceUpgrade();
            if (railway.isCompleted()) {
                railway.transportGoods();
            }
        }

        System.out.println();
        for (Town town : towns.values()) {
            System.out.printf("%s p:%d rs:%d rd:%d gs:%d gt:%d%n",
                    town.getName(),
                    town.getPopulation(),
                    town.getSingleTrackRailways(),
                    town.getDualTrackRailways(),
                    town.getGoodsStockpile(),
                    town.getGoodsTransportedToday());
            town.resetGoodsTransportedToday();
        }

        messagesReceived.clear();

        DotFileWriter.writeDotFile(towns, railways, "simoutput.dot");
    }
}