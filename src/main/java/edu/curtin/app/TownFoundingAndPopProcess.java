package edu.curtin.app;


import java.util.Map;

public class TownFoundingAndPopProcess implements Process {
    
    @Override
    public void process(Simulation sim, String townName, String process){
        int population = Integer.parseInt(process);
        
        Map<String, Town> towns = sim.getTowns(); 
        Town town = towns.getOrDefault(townName, new Town(townName, population));
        town.setPopulation(population);
        towns.put(townName, town);  
        
        String message = "town-population " + townName + " " + population;
        System.out.println(message);
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

// public void processTownMessage(String townName, int population) {
//         Town town = towns.getOrDefault(townName, new Town(townName, population));
//         town.setPopulation(population);
//         towns.put(townName, town);
//         String message = "town-population " + townName + " " + population;
//         messagesReceived.add(message);
//         notifyObservers(message); 
//     }