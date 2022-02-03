package io.github.metalcupcake5.JustEnoughUpdates.utils;

import java.util.List;

public class Drill {
    public int currentFuel;
    public int maxFuel;
    public String fuelTank;
    public String drillEngine;
    public String upgradeModule;

    public Drill(int currentFuel, int maxFuel) {
        this.currentFuel = currentFuel;
        this.maxFuel = maxFuel;
    }

    public Drill(int currentFuel, int maxFuel, String fuelTank, String drillEngine, String upgradeModule) {
        this.currentFuel = currentFuel;
        this.maxFuel = maxFuel;
        this.fuelTank = fuelTank;
        this.drillEngine = drillEngine;
        this.upgradeModule = upgradeModule;
    }

    public Drill(List<String> lore) {
        this.maxFuel = 3000;
        for(String line : lore){
            if(line.contains("Fuel:")){
                this.maxFuel = Integer.parseInt(line.split("/")[1].replace("k", "000"));
                this.currentFuel = Integer.parseInt(line.split(":")[1].split("/")[0].trim().replace(",", ""));
                break;
            }
        }
    }

    public double getFuelPercentage(){
        return currentFuel/(double) maxFuel;
    }
}
