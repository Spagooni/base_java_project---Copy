package edu.curtin.app;

import java.util.Map;

public class RailwayDuplicationProcess implements Process {
    
    @Override
    public void process(Simulation sim, String townA, String townB){
        Map<String, Railway> railways = sim.getRailways(); 

        Railway railway = railways.get(townA + "-" + townB);
        if (railway != null && !railway.isDualTrack()) {
            railway.upgradeToDualTrack();
            String message = "railway-duplication " + townA + " " + townB;
            sim.getMessagesReceived().add(message);
            sim.notifyObservers(message);
        }
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