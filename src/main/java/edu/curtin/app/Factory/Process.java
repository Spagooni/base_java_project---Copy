package edu.curtin.app.factory;
import edu.curtin.app.Simulation;

public interface Process{

    void process(Simulation sim, String town, String process);
}