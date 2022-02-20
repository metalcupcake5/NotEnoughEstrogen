package io.github.metalcupcake5.JustEnoughUpdates.utils;

public enum SkyblockLocations {
    // dwarven mines
    DWARVEN_MINES("Dwarven Mines", "Dwarven Mines"),
    DWARVEN_VILLAGE("Dwarven Village", "Dwarven Mines"),
    THE_LIFT("The Lift", "Dwarven Mines"),
    MINERS_GUILD("Miner's Guild", "Dwarven Mines"),
    PALACE_BRIDGE("Palace Bridge", "Dwarven Mines"),
    FORGE_BASIN("Forge Basin", "Dwarven Mines"),
    THE_FORGE("The Forge", "Dwarven Mines"),
    FAR_RESERVE("Far Reserve", "Dwarven Mines"),
    GOBLIN_BURROWS("Goblin Burrows", "Dwarven Mines"),
    GREAT_ICE_WALL("Great Ice Wall", "Dwarven Mines"),
    ARISTOCRAT_PASSAGE("Aristocrat Passage", "Dwarven Mines"),
    DIVANS_GATEWAY("Divan's Gateway", "Dwarven Mines"),

    // dwarven mines king area
    ROYAL_PALACE("Royal Palace", "Dwarven Mines"),
    GRAND_LIBRARY("Grand Library", "Dwarven Mines"),
    BARRACKS_OF_HEROES("Barracks of Heroes", "Dwarven Mines"),
    HANGING_COURT("Hanging Court", "Dwarven Mines"),

    // commission locations
    LAVA_SPRINGS("Lava Springs", "Dwarven Mines"),
    ROYAL_MINES("Royal Mines", "Dwarven Mines"),
    CLIFFSIDE_VEINS("Cliffside Veins", "Dwarven Mines"),
    RAMPARTS_QUARRY("Rampart's Quarry", "Dwarven Mines"),
    UPPER_MINES("Upper Mines", "Dwarven Mines"),


    // crystal hallows
    CRYSTAL_NUCLEUS("Crystal Nucleus", "Crystal Hallows"),
    MAGMA_FIELDS("Magma Fields", "Crystal Hallows"),
    FAIRY_GROTTO("Fairy Grotto", "Crystal Hallows"),

    JUNGLE("Jungle", "Crystal Hallows"),
    JUNGLE_TEMPLE("Jungle Temple", "Crystal Hallows"),

    PRECURSOR_REMNANTS("Precursor Remnants", "Crystal Hallows"),
    PRECURSOR_CITY("Lost Precursor City", "Crystal Hallows"),

    MITHRIL_DEPOSITS("Mithril Deposits", "Crystal Hallows"),
    MINES_OF_DIVAN("Mines of Divan", "Crystal Hallows"),

    GOBLIN_HOLDOUT("Goblin Holdout", "Crystal Hallows"),
    GOBLIN_QUEENS_DEN("Goblin Queen's Den", "Crystal Hallows");

    public final String name;
    public final String location;

    SkyblockLocations(String name, String location) {
        this.name = name;
        this.location = location;
    }

    public static String getLocationFromName(String location){
        for(SkyblockLocations loc: SkyblockLocations.values()){
            if(loc.name.contains(location)) return loc.location;
        }
        return "Unknown";
    }
}
