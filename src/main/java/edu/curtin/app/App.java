package edu.curtin.app;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import edu.curtin.app.factory.ProcessFactory;
import edu.curtin.app.observers.ConsoleObserver;

public class App {
    private static final Logger logger = Logger.getLogger(App.class.getName());

    public static void main(String[] args) {
        TownsInput inp = new TownsInput(2024);
        inp.setErrorProbability(0.0);
        Simulation sim = new Simulation();
        ConsoleObserver consoleObserver = new ConsoleObserver();
        sim.addObserver(consoleObserver);

        try {
            logger.info("Simulation started.");
            while (System.in.available() == 0) {
                String  msg = inp.nextMessage();
                final String logMsg = msg;
                while (msg != null) {
                    logger.log(Level.INFO, () -> "Processing message: " + logMsg);
                    ProcessFactory.processMessage(sim, msg);
                    msg = inp.nextMessage();
                }
                sim.simulateDay();
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            logger.log(Level.SEVERE, "Simulation interrupted", e);
            Thread.currentThread().interrupt(); 
        } catch (IOException e) {
            logger.log(Level.SEVERE, "IO error while checking system input", e);
        } catch (IllegalArgumentException e) {
            logger.log(Level.SEVERE, () -> "Invalid argument encountered: " + e.getMessage());
        }
        finally {
            logger.info("Simulation ended.");
        }
    }
}
