package io.github.metalcupcake5.JustEnoughUpdates.utils;

public class Commission {
    private String name;
    private double percent;
    private String progress;

    public Commission(String name, String percent){
        this.name = name;
        if(percent.equals("DONE")){
            this.percent = 1.0;
            this.progress = "Done!";
        } else {
            this.percent = Double.parseDouble(percent.substring(0, percent.length() - 1)) / 100;
            int total = Commissions.getCommissionFromName(name).total;
            this.progress = (int) (this.percent * total) + "/" + total;
        }
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
