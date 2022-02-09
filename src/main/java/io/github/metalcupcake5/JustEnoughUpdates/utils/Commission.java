package io.github.metalcupcake5.JustEnoughUpdates.utils;

public class Commission {
    private String name;
    private String percent;

    public Commission(String name, String percent){
        this.name = name;
        this.percent = percent;
    }

    public String getName(){
        return this.name;
    }

    public String getPercent(){
        return this.percent;
    }

    public String toString(){
        return this.name + " at " + this.percent + " completion";
    }
}
