package edu.curtin.app;

import edu.curtin.app.states.ConstructionState;
import edu.curtin.app.states.IdleState;
import edu.curtin.app.states.RailwayState;
import edu.curtin.app.states.UpgradeState;

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

    private RailwayState state;

    public Railway(Town townA, Town townB) {
        this.townA = townA;
        this.townB = townB;
        this.daysToComplete = 5;
        this.daysToUpgrade = 5;
        
    }

    public void initialize() {
        this.state = new ConstructionState(this, townA, townB);
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
        state = new UpgradeState(this, townA, townB);
    }

    public void setIsDualTrack(Boolean setIsDualTrack){
        isDualTrack = setIsDualTrack;
    }

    public void setIsUpgrading(Boolean isUpgradingUpdate) {
       isUpgrading = isUpgradingUpdate;
    }

    public Boolean getIsUpgrading() {
       return isUpgrading;
    }

    public int getDaysToUpgrade(){
        return daysToUpgrade;
    }

    public void reduceDaysToUpgrade() {
       daysToUpgrade--;
    }

    public boolean isBuilding() {
       return isBuilding;
    }

    public void setIsBuilding(Boolean isBuildingUpdate) {
       isBuilding = isBuildingUpdate;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setIsCompleted(Boolean isCompleteUpdate) {
       isCompleted = isCompleteUpdate;
    }

    public int getDaysToComplete(){
        return daysToComplete;
    }

    public void reduceDaysToComplete(){
        daysToComplete--;
    }

    public void advanceUpgrade(){
        state.Upgrade();
    }

    public void advanceConstruction() {
        state.Construct();
    }

    public void setIdleState() {
        this.state = new IdleState(this, townA, townB);
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
