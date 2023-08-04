package io.github.metalcupcake5.NotEnoughEstrogen.utils;

import java.util.List;

public class Drill {
    public int currentFuel;
    public int maxFuel;

    public Drill(List<String> lore) {
        this.maxFuel = 3000;
        for (String line : lore) {
            if (line.contains("Fuel:")) {
                this.maxFuel = Integer.parseInt(line.split("/")[1].replace("k", "000"));
                this.currentFuel = Integer.parseInt(line.split(":")[1].split("/")[0].trim().replace(",", ""));
                break;
            }
        }
    }
}
