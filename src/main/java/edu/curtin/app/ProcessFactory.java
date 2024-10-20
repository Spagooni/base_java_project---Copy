package edu.curtin.app;

public class ProcessFactory{
    public static void processMessage(Simulation sim, String msg) {
        String[] parts = msg.split(" ");
        if (parts.length != 3) return; 

        String type = parts[0];
        String townA = parts[1];
        String thirdPart = parts[2];

        switch (type) {
            case "town-founding", "town-population" -> {
                TownFoundingAndPopProcess townFoundingProcess = new TownFoundingAndPopProcess();
                townFoundingProcess.process(sim, townA, thirdPart);
            }
            case "railway-construction" -> {
                RailwayConstructionProcess railwayConstructionProcess = new RailwayConstructionProcess();
                railwayConstructionProcess.process(sim, townA, thirdPart);
            }
            case "railway-duplication" -> {
                RailwayDuplicationProcess RailwayDuplicationProcess = new RailwayDuplicationProcess();
                RailwayDuplicationProcess.process(sim, townA, thirdPart);
            }
        }
    }
}