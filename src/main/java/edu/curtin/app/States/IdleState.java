package edu.curtin.app.states;

import edu.curtin.app.Railway;
import edu.curtin.app.Town;

public class IdleState extends RailwayState{

    public IdleState(Railway railway, Town townA, Town townB){
        super(railway, townA, townB);
    }


    @Override
    public void Upgrade(){
        
    }
    
    @Override
    public void Construct(){

    }
}