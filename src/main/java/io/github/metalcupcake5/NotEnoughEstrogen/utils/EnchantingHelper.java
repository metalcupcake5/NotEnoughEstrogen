package io.github.metalcupcake5.NotEnoughEstrogen.utils;

import net.minecraft.inventory.Inventory;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

import java.util.HashMap;
import java.util.Map;

public class EnchantingHelper {

    public static Map<Integer, ItemStack> pattern = new HashMap<>();


    public static void ultrasequencer(Inventory inventory) {
        if (inventory.getStack(49).isOf(Items.GLOWSTONE)) {
            pattern.clear();
            for (int i = 0; i < inventory.size(); i++) {
                ItemStack item = inventory.getStack(i);
                if (!(item.getItem() instanceof BlockItem)) {
                    pattern.put(i, item);
                }
            }
        } else if (inventory.getStack(49).isOf(Items.CLOCK)) {
            pattern.forEach(inventory::setStack);
        }
    }
}
