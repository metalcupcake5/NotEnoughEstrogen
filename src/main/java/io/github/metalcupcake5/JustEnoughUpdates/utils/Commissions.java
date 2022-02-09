package io.github.metalcupcake5.JustEnoughUpdates.utils;

public enum Commissions {
    /*
     * DWARVEN MINES
     */
    // slayer
    ICE_WALKER("Ice Walker Slayer"),
    GOBLIN("Goblin Slayer"),

    // default
    MITHRIL("Mithril Miner"),
    TITANIUM("Titanium Miner"),

    // location
    LAVA_SPRINGS_MITHRIL("Lava Springs Mithril"),
    LAVA_SPRINGS_TITANIUM("Lava Springs Titanium"),
    ROYAL_MINES_MITHRIL("Royal Mines Mithril"),
    ROYAL_MINES_TITANIUM("Royal Mines Titanium"),
    CLIFFSIDE_VEINS_MITHRIL("Cliffside Veins Mithril"),
    CLIFFSIDE_VEINS_TITANIUM("Cliffside Veins Titanium"),
    RAMPARTS_QUARRY_MITHRIL("Rampart's Quarry Mithril"),
    RAMPARTS_QUARRY_TITANIUM("Rampart's Quarry Titanium"),
    UPPER_MINES_MITHRIL("Upper Mines Mithril"),
    UPPER_MINES_TITANIUM("Upper Mines Titanium"),

    //event
    GOBLIN_RAID("Goblin Raid"),
    GOBLIN_RAID_SLAYER("Goblin Raid Slayer"),
    RAFFLE("Raffle"),
    LUCKY_RAFFLE("Lucky Raffle"),
    DOUBLE_POWDER("2x Mithril Powder Collector"),

    //special
    GOLDEN_GOBLIN("Golden Goblin Slayer"),
    POWDER_GHAST("Powder Ghast Puncher"),
    STAR_SENTRY("Star Sentry Puncher");

    public final String s;
    Commissions(String s){
        this.s = s;
    }

    public static boolean isCommission(String s){
        for(Commissions comm : Commissions.values()){
            if(s.contains(comm.s)) return true;
        }
        return false;
    }
}
