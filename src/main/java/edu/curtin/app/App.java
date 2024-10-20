package edu.curtin.app;

public class App {
    public static void main(String[] args) {
        TownsInput inp = new TownsInput(2024);
        inp.setErrorProbability(0.0);
        Simulation sim = new Simulation();

        try {
            while (System.in.available() == 0) {
                String msg = inp.nextMessage();
                while (msg != null) {
                    processMessage(sim, msg);
                    msg = inp.nextMessage();
                }
                sim.simulateDay();

                Thread.sleep(1000);  
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void processMessage(Simulation sim, String msg) {
        String[] parts = msg.split(" ");
        if (parts.length != 3) return; 

        String type = parts[0];
        String townA = parts[1];
        String thirdPart = parts[2];

        switch (type) {
            case "town-founding", "town-population" -> {
                int population = Integer.parseInt(thirdPart);
                sim.processTownMessage(townA, population);
            }
            case "railway-construction" -> sim.processRailwayConstruction(townA, thirdPart);
            case "railway-duplication" -> sim.processRailwayDuplication(townA, thirdPart);
            default -> System.out.println("Invalid message type: " + type);
        }
    }
}