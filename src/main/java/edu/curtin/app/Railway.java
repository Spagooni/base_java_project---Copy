package edu.curtin.app;

public class Railway {
    private final Town townA;
    private final Town townB;
    private boolean isDualTrack = false;
    private boolean isCompleted = false;
    private boolean isBuilding = false;
    private boolean isUpgrading = false;
    private boolean isDirectionAToB; 
    private int daysToComplete; 
    private int daysToUpgrade;

    public Railway(Town townA, Town townB) {
        this.townA = townA;
        this.townB = townB;
        this.daysToComplete = 5;
        this.daysToUpgrade = 5;
    }

    public Town getTownA() {
        return townA;
    }

    public Town getTownB() {
        return townB;
    }

    public boolean isDualTrack() {
        return isDualTrack;
    }

    public void upgradeToDualTrack() {
        isUpgrading = true;
    }
    public boolean isBuilding() {
       return isBuilding;
    }
    public boolean isCompleted() {
        return isCompleted;
    }

    public void advanceUpgrade(){
        if(daysToUpgrade > 0 && isUpgrading){
            // System.out.println("Days Remaining to upgrade: " + daysToUpgrade);
            daysToUpgrade--;
        }
        else if(daysToUpgrade == 0){
            this.isDualTrack = true;
            townA.incrementDualTrackRailways();
            townB.incrementDualTrackRailways();

            townA.decrementSingleTrackRailways();
            townB.decrementSingleTrackRailways();
            daysToUpgrade--;
            isUpgrading = false;
        }
    }

    public void advanceConstruction() {
        if(daysToComplete > 0){
            // System.out.println("Days Remaining to build: " + daysToComplete);
            daysToComplete--;
            isBuilding = true;
        }
        else if(daysToComplete == 0){
            this.isCompleted = true;
            townA.incrementSingleTrackRailways();
            townB.incrementSingleTrackRailways();
            daysToComplete--;
            isBuilding = false;
        }
    }

    public void transportGoods() {
        if (isCompleted()) { 
            int capacity = 100; 

            if (isDirectionAToB) {
                int goodsToTransport = Math.min(capacity, townA.getGoodsStockpile());
                townA.transportGoods(goodsToTransport);
            } else {
                int goodsToTransport = Math.min(capacity, townB.getGoodsStockpile());
                townB.transportGoods(goodsToTransport);
            }

            isDirectionAToB = !isDirectionAToB; 
        }
    }
}
