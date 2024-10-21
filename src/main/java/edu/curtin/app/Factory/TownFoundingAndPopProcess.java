package edu.curtin.app.Factory;
import java.util.Map;

import edu.curtin.app.Simulation;
import edu.curtin.app.Town;


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
}
