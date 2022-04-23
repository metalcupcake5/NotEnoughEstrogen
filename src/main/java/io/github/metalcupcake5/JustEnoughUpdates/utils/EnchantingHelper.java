package io.github.metalcupcake5.JustEnoughUpdates.utils;

import net.minecraft.inventory.Inventory;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.text.Text;

import java.util.HashMap;
import java.util.Map;

public class EnchantingHelper {
    public static Map<Integer, Integer> pattern = new HashMap<>();
    public static Inventory inventory;
    public static String chestInventoryName;

    public static void ultrasequencer(){
        if(inventory.getStack(49).isOf(Items.GLOWSTONE)) {
            pattern.clear();
            for(int i = 0; i < inventory.size(); i++){
                ItemStack item = inventory.getStack(i);
                if(!(item.getItem() instanceof BlockItem)){
                    pattern.put(item.getCount(), i);
                }
            }
        } else if(inventory.getStack(49).isOf(Items.CLOCK)){
            int max = 0;
            for(int i = 0; i < inventory.size(); i++){
                ItemStack item = inventory.getStack(i);
                if(!(item.getItem() instanceof BlockItem) && !item.isOf(Items.CLOCK)){
                    max = Math.max(max, item.getCount());
                    pattern.remove(item.getCount());
                }
            }
            int slot = pattern.get(max + 1);
            if(!inventory.getStack(slot).isOf(Items.EMERALD_BLOCK)) {
                inventory.setStack(pattern.get(max + 1), new ItemStack(Items.EMERALD_BLOCK).setCustomName(Text.of("")));
            }
        }
    }
}
