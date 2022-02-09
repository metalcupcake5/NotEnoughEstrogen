package io.github.metalcupcake5.JustEnoughUpdates.utils;

public enum Commissions {
    /*
     * DWARVEN MINES
     */
    // slayer
    ICE_WALKER("Ice Walker Slayer", 50),
    GOBLIN("Goblin Slayer", 100),

    // default
    MITHRIL("Mithril Miner", 500),
    TITANIUM("Titanium Miner", 15),

    // location
    LAVA_SPRINGS_MITHRIL("Lava Springs Mithril", 350),
    LAVA_SPRINGS_TITANIUM("Lava Springs Titanium", 10),
    ROYAL_MINES_MITHRIL("Royal Mines Mithril", 350),
    ROYAL_MINES_TITANIUM("Royal Mines Titanium", 10),
    CLIFFSIDE_VEINS_MITHRIL("Cliffside Veins Mithril", 350),
    CLIFFSIDE_VEINS_TITANIUM("Cliffside Veins Titanium", 10),
    RAMPARTS_QUARRY_MITHRIL("Rampart's Quarry Mithril", 350),
    RAMPARTS_QUARRY_TITANIUM("Rampart's Quarry Titanium", 10),
    UPPER_MINES_MITHRIL("Upper Mines Mithril", 350),
    UPPER_MINES_TITANIUM("Upper Mines Titanium", 10),

    //event
    GOBLIN_RAID("Goblin Raid", 1),
    GOBLIN_RAID_SLAYER("Goblin Raid Slayer", 20),
    RAFFLE("Raffle", 1),
    LUCKY_RAFFLE("Lucky Raffle", 20),
    DOUBLE_POWDER("2x Mithril Powder Collector", 500),

    //special
    GOLDEN_GOBLIN("Golden Goblin Slayer", 1),
    POWDER_GHAST("Powder Ghast Puncher", 5),
    STAR_SENTRY("Star Sentry Puncher", 10),

    UNKOWN("Unknown", 1);

    public final String s;
    public final int total;
    Commissions(String s, int total){
        this.s = s;
        this.total = total;
    }

    public static boolean isCommission(String s){
        for(Commissions comm : Commissions.values()){
            if(s.contains(comm.s)) return true;
        }
        return false;
    }

    public static Commissions getCommissionFromName(String name){
        for(Commissions comm : Commissions.values()){
            if(comm.s.equals(name)){
                return comm;
            }
        }
        return UNKOWN;
    }
}
