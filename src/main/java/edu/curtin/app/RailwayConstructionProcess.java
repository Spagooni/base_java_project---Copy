package edu.curtin.app;

import java.util.Map;

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


    // @Override
    // public void addObserver(Observer observer){
    //     observers.add(observer);
    // }

    // @Override
    // public void removeObserver(Observer observer){
    //     observers.remove(observer);
    // }

    // @Override
    // public void notifyObservers(String message){
    //     for (Observer observer : observers) {
    //         observer.update(message);
    //     }
    // }
}