package io.github.metalcupcake5.NotEnoughEstrogen.commands;

import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import io.github.metalcupcake5.NotEnoughEstrogen.NotEnoughEstrogenClient;
import io.github.metalcupcake5.NotEnoughEstrogen.utils.ChatUtils;
import io.github.metalcupcake5.NotEnoughEstrogen.utils.GardenUtils;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.decoration.ArmorStandEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.Box;

import java.util.List;

public class Foo {

    public static int run(CommandContext context) throws CommandSyntaxException {
        MinecraftClient client = MinecraftClient.getInstance();
        /*NotEnoughEstrogenClient.getScheduler().schedule(() -> {
            ChatUtils.sendClientMessage("task done");
        }, 3, TimeUnit.SECONDS);*/
        /*client.world.getEntities().forEach((entity -> {
            if (entity.getType().equals(EntityType.ARMOR_STAND)) {
                String name = entity.getDisplayName().getString().toLowerCase();
                if (name.contains("compost")) {
                    ChatUtils.sendClientMessage("compost found: " + name);
                }
            }
        }));*/

        List<ArmorStandEntity> list = client.world.getEntitiesByType(EntityType.ARMOR_STAND, new Box(-8, 70, 74, -13, 75, -29), armorStandEntity -> true);
        list.forEach(armorStandEntity -> {
            if (armorStandEntity.hasCustomName()) {
                String name = armorStandEntity.getCustomName().getString();
                if (name.contains("ready")) {
                    String[] split = name.split(" ")[0].split(":");
                    NotEnoughEstrogenClient.LOGGER.info(split[1]);
                    ChatUtils.sendClientMessage("" + client.textRenderer.getWidth(Text.of("Time Until Next: " + Formatting.AQUA + GardenUtils.getVisitorTime())));
                }
            }


        });
        //client.inGameHud.addChatMessage(MessageType.SYSTEM, Text.of("test command"), Util.NIL_UUID);
        return 1;
    }
}