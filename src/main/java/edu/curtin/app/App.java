package edu.curtin.app;
import edu.curtin.app.Factory.ProcessFactory;
import edu.curtin.app.observers.ConsoleObserver;

public class App {
    public static void main(String[] args) {
        TownsInput inp = new TownsInput(2024);
        inp.setErrorProbability(0.0);
        Simulation sim = new Simulation();

        ConsoleObserver consoleObserver = new ConsoleObserver();
        sim.addObserver(consoleObserver);

        try {
            while (System.in.available() == 0) {
                String msg = inp.nextMessage();
                while (msg != null) {
                    ProcessFactory.processMessage(sim, msg);
                    //processMessage(sim, msg);
                    msg = inp.nextMessage();
                }
                sim.simulateDay();

                Thread.sleep(1000);  
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}