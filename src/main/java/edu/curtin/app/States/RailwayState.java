package edu.curtin.app.states;

import java.util.logging.Level;
import java.util.logging.Logger;

import edu.curtin.app.Railway;
import edu.curtin.app.Town;

public abstract class RailwayState {
    protected Railway railway;
    protected Town townA;
    protected Town townB;

    private static final Logger logger = Logger.getLogger(RailwayState.class.getName());

    public RailwayState(Railway railway, Town townA, Town townB) {
        this.railway = railway;
        this.townA = townA;
        this.townB = townB;
    }

    public abstract void upgrade(); 

    public abstract void construct();  

    protected void logStateChange(String stateName) {
        logger.log(Level.INFO, () -> String.format("Railway state changed to %s for railway between %s and %s.",
                stateName, townA.getName(), townB.getName()));  
    }

    protected void logError(String message, Exception e) {
        logger.log(Level.SEVERE, message, e);
    }
}
