package io.github.metalcupcake5.JustEnoughUpdates.utils;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.Text;

import java.util.ArrayList;
import java.util.List;

public class ItemUtils {
    public static int pickaxeAbilityCooldown = 120;

    public static NbtCompound getSkyblockData(ItemStack stack) {
        NbtCompound itemNBT = stack.getNbt();
        if (itemNBT == null) return null;
        return itemNBT.getCompound("ExtraAttributes");
    }

    public static List<String> getLore(ItemStack stack) {
        MinecraftClient client = MinecraftClient.getInstance();
        List<String> lore = new ArrayList<>();
        for (Text t : stack.getTooltip(client.player, TooltipContext.Default.NORMAL)) {
            lore.add(t.getString());
        }
        return lore;
    }

    public static int getDrillFuel(List<String> lore) {
        int fuel = 0;
        for (String line : lore) {
            if (line.contains("Fuel:")) {
                fuel = Integer.parseInt(line.split(":")[1].split("/")[0].trim().replace(",", ""));
                break;
            }
        }
        return fuel;
    }

    public static int getDrillMaxFuel(List<String> lore) {
        int maxFuel = 3000;
        for (String line : lore) {
            if (line.contains("Fuel:")) {
                maxFuel = Integer.parseInt(line.split("/")[1].replace("k", "000"));
                break;
            }
        }
        return maxFuel;
    }

    public static boolean isMiningTool(ItemStack stack) {
        List<String> lore = getLore(stack);
        for (int i = lore.size() - 1; i >= 0; i--) {
            String line = lore.get(i);
            if (line.contains("DRILL")) return true;
            if (line.contains("PICKAXE")) return true;
            if (line.contains("GAUNTLET")) return true;
        }
        return false;
    }
}
