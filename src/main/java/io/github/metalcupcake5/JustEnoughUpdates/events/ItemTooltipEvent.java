package io.github.metalcupcake5.JustEnoughUpdates.events;

import io.github.metalcupcake5.JustEnoughUpdates.config.ConfigManager;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.util.List;

public class ItemTooltipEvent {
    public static void onTooltip(ItemStack stack, TooltipContext context, List<Text> lines){
        NbtCompound itemNBT = stack.getNbt();
        if(itemNBT == null){
            return;
        }
        NbtCompound attributes = itemNBT.getCompound("ExtraAttributes");
        if(attributes == null){
            return;
        }
        if(ConfigManager.showSkyblockIds) {
            String id = attributes.getString("id");
            if (id.length() > 0) {
                lines.add(Text.of(Formatting.DARK_GRAY + "skyblock id: " + attributes.getString("id")));
            }
        }
        if(ConfigManager.showItemInfo) {
            String timestamp = attributes.getString("timestamp");
            if (timestamp.length() > 0) {
                lines.add(Text.of(Formatting.DARK_GRAY + "timestamp: " + attributes.getString("timestamp")));
            }
            String origin = attributes.getString("originTag");
            if (origin.length() > 0) {
                lines.add(Text.of(Formatting.DARK_GRAY + "origin: " + attributes.getString("originTag")));
            }
            String uuid = attributes.getString("uuid");
            if (uuid.length() > 0) {
                lines.add(Text.of(Formatting.DARK_GRAY + "uuid: " + attributes.getString("uuid")));
            }
        }
    }
}
