package io.github.metalcupcake5.NotEnoughEstrogen.events;

import net.minecraft.client.MinecraftClient;
import net.minecraft.item.Items;

public class CheckFishingEvent {
    public static void onTick(MinecraftClient client) {
        if (client.world != null && !client.isInSingleplayer()) {
            if (client.player.getMainHandStack().getItem() == Items.FISHING_ROD) {
//                System.out.println("is fishing rod");
            }
        }


    }
}
