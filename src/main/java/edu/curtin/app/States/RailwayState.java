package edu.curtin.app.states;
import edu.curtin.app.Railway;
import edu.curtin.app.Town;

public abstract class RailwayState{
    protected Railway railway;
    protected Town townA; 
    protected Town townB; 

    public RailwayState(Railway railway, Town townA, Town townB){
        this.railway = railway;
        this.townA = townA;
        this.townB = townB;
    }

    public abstract void Upgrade();
    
    public abstract void Construct();
}