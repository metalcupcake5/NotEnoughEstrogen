package io.github.metalcupcake5.NotEnoughEstrogen.events;

import io.github.metalcupcake5.NotEnoughEstrogen.config.ConfigManager;
import io.github.metalcupcake5.NotEnoughEstrogen.utils.ItemUtils;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.util.List;

public class ItemTooltipEvent {
    public static void onTooltip(ItemStack stack, TooltipContext context, List<Text> lines) {
        NbtCompound attributes = ItemUtils.getSkyblockData(stack);
        if (attributes == null) {
            return;
        }

        if (ConfigManager.showArmadilloEggBlocks) {
            int armadilloBlocksWalked = attributes.getInt("blocks_walked");
            if (armadilloBlocksWalked > 0) {
                lines.add(Text.of(Formatting.WHITE + "blocks walked: " + Formatting.GRAY + armadilloBlocksWalked));
            }
        }

        if (ConfigManager.showSkyblockIds) {
            String id = attributes.getString("id");
            if (id.length() > 0) {
                lines.add(Text.of(Formatting.DARK_GRAY + "skyblock id: " + id));
            }
        }

        if (ConfigManager.showItemInfo) {
            String timestamp = attributes.getString("timestamp");
            if (timestamp.length() > 0) {
                lines.add(Text.of(Formatting.DARK_GRAY + "timestamp: " + timestamp));
            }
            String origin = attributes.getString("originTag");
            if (origin.length() > 0) {
                lines.add(Text.of(Formatting.DARK_GRAY + "origin: " + origin));
            }
            String uuid = attributes.getString("uuid");
            if (uuid.length() > 0) {
                lines.add(Text.of(Formatting.DARK_GRAY + "uuid: " + uuid));
            }
        }
    }
}
