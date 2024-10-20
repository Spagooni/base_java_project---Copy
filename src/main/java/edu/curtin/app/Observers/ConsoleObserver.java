package edu.curtin.app.observers;

public class ConsoleObserver implements Observer {
    @Override
    public void update(String message) {
        System.out.println("Observer notified: " + message);
    }
}
