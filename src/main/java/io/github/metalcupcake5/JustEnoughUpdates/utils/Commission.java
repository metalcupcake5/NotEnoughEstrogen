package io.github.metalcupcake5.JustEnoughUpdates.utils;

import net.minecraft.util.Formatting;

public class Commission {
    private final String name;
    private final double percent;
    private final String progress;
    private final int location;

    public Commission(String name, String percent, int location){
        this.name = name;
        if(percent.equals("DONE")){
            this.percent = 1.0;
            this.progress = Formatting.GREEN + "Done!";
        } else {
            this.percent = Double.parseDouble(percent.substring(0, percent.length() - 1)) / 100;
            String prefix = String.valueOf(this.percent >= 0.75 ? Formatting.GREEN : this.percent >= 0.5 ? Formatting.YELLOW : this.percent >= 0.25 ? Formatting.GOLD : Formatting.RED);
            int total = Commissions.getCommission(name, location).total;
            this.progress = prefix + (int) (this.percent * total) + "/" + total;
        }
        this.location = location;
    }

    public String getName(){
        return this.name;
    }

    public double getPercent(){
        return this.percent;
    }

    public String getProgress(){
        return this.progress;
    }

    public int getLocation() {
        return this.location;
    }

    public String toString(){
        return this.name + " at " + this.percent + " completion";
    }
}
