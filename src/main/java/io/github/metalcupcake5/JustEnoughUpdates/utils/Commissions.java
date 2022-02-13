package io.github.metalcupcake5.JustEnoughUpdates.utils;

public enum Commissions {
    /*
     * DWARVEN MINES
     */

    // slayer
    ICE_WALKER("Ice Walker Slayer", 50, 0),
    GOBLIN_DWARVEN("Goblin Slayer", 100, 0),

    // default
    MITHRIL("Mithril Miner", 500, 0),
    TITANIUM("Titanium Miner", 15, 0),

    // location
    LAVA_SPRINGS_MITHRIL("Lava Springs Mithril", 350, 0),
    LAVA_SPRINGS_TITANIUM("Lava Springs Titanium", 10, 0),
    ROYAL_MINES_MITHRIL("Royal Mines Mithril", 350, 0),
    ROYAL_MINES_TITANIUM("Royal Mines Titanium", 10, 0),
    CLIFFSIDE_VEINS_MITHRIL("Cliffside Veins Mithril", 350, 0),
    CLIFFSIDE_VEINS_TITANIUM("Cliffside Veins Titanium", 10, 0),
    RAMPARTS_QUARRY_MITHRIL("Rampart's Quarry Mithril", 350, 0),
    RAMPARTS_QUARRY_TITANIUM("Rampart's Quarry Titanium", 10, 0),
    UPPER_MINES_MITHRIL("Upper Mines Mithril", 350, 0),
    UPPER_MINES_TITANIUM("Upper Mines Titanium", 10, 0),

    //event
    GOBLIN_RAID("Goblin Raid", 1, 0),
    GOBLIN_RAID_SLAYER("Goblin Raid Slayer", 20, 0),
    RAFFLE("Raffle", 1, 0),
    LUCKY_RAFFLE("Lucky Raffle", 20, 0),
    DOUBLE_POWDER("2x Mithril Powder Collector", 500, 0),

    //special
    GOLDEN_GOBLIN("Golden Goblin Slayer", 1, 0),
    POWDER_GHAST("Powder Ghast Puncher", 5, 0),
    STAR_SENTRY("Star Sentry Puncher", 10, 0),

    /*
     * CRYSTAL HALLOWS
     */

    //crystal
    JADE_CRYSTAL("Jade Crystal Hunter", 1, 1),
    AMBER_CRYSTAL("Amber Crystal Hunter", 1, 1),
    AMETHYST_CRYSTAL("Amethyst Crystal Hunter", 1, 1),
    SAPPHIRE_CRYSTAL("Sapphire Crystal Hunter", 1, 1),
    TOPAZ_CRYSTAL("Topaz Crystal Hunter", 1, 1),

    //gemstone
    JADE_GEMSTONE("Jade Gemstone Collector", 1, 1),
    AMBER_GEMSTONE("Amber Gemstone Collector", 1, 1),
    AMETHYST_GEMSTONE("Amethyst Gemstone Collector", 1, 1),
    SAPPHIRE_GEMSTONE("Sapphire Gemstone Collector", 1, 1),
    TOPAZ_GEMSTONE("Topaz Gemstone Collector", 1, 1),

    //slayer
    AUTOMATON("Automaton Slayer", 13, 1),
    SLUDGE("Sludge Slayer", 25, 1),
    TEAM_TREASURITE("Team Treasurite Member Slayer", 13, 1),
    GOBLIN_HALLOWS("Goblin Slayer", 13, 1),
    YOG("Slay 13 Yogs", 13, 1),
    BOSS_CORLEONE("Slay 1 Boss Corleone", 1, 1),
    THYST("Slay 5 Thysts", 5, 1),

    //other
    CHEST("Open 3 chests", 3, 1),
    HARD_STONE("Mine 1,000 Hard Stone", 1000, 1),

    UNKOWN("Unknown", 1, 1);

    public final String name;
    public final int total;
    public final int location;
    Commissions(String name, int total, int location){
        this.name = name;
        this.total = total;
        this.location = location;
    }

    public static boolean isCommission(String s){
        for(Commissions comm : Commissions.values()){
            if(s.contains(comm.name)) return true;
        }
        return false;
    }

    public static Commissions getCommission(String name, int location){
        for(Commissions comm : Commissions.values()){
            if(comm.name.equals(name) && comm.location == location){
                return comm;
            }
        }
        return UNKOWN;
    }
}
