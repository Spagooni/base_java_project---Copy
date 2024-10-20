package edu.curtin.app;

public class Town {
    private final String name;
    private int population;
    private int goodsStockpile;
    private int goodsTransportedToday;
    private int singleTrackRailways;
    private int dualTrackRailways;

    public Town(String name, int population) {
        this.name = name;
        this.population = population;
        this.goodsStockpile = 0;
        this.goodsTransportedToday = 0;
        this.singleTrackRailways = 0;
        this.dualTrackRailways = 0;
    }

    public String getName() {
        return name;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public int getGoodsStockpile() {
        return goodsStockpile;
    }

    public int getGoodsTransportedToday() {
        return goodsTransportedToday;
    }

    public void resetGoodsTransportedToday() {
        goodsTransportedToday = 0;
    }

    public void produceGoods() {
        goodsStockpile += population;
    }

    public void transportGoods(int amount) {
        goodsStockpile = Math.max(0, goodsStockpile - amount);
        goodsTransportedToday += amount;
    }

    public int getSingleTrackRailways() {
        return singleTrackRailways;
    }

    public void incrementSingleTrackRailways() {
        singleTrackRailways++;
    }

    public void decrementSingleTrackRailways() {
        singleTrackRailways--;
    }

    public int getDualTrackRailways() {
        return dualTrackRailways;
    }

    public void incrementDualTrackRailways() {
        dualTrackRailways++;
    }
}