package io.github.metalcupcake5.JustEnoughUpdates.utils;

public class Commission {
    private final String name;
    private final double percent;
    private final String progress;
    private final int location;

    public Commission(String name, String percent, int location){
        this.name = name;
        if(percent.equals("DONE")){
            this.percent = 1.0;
            this.progress = "Done!";
        } else {
            this.percent = Double.parseDouble(percent.substring(0, percent.length() - 1)) / 100;
            int total = Commissions.getCommission(name, location).total;
            this.progress = (int) (this.percent * total) + "/" + total;
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

    public String toString(){
        return this.name + " at " + this.percent + " completion";
    }
}
