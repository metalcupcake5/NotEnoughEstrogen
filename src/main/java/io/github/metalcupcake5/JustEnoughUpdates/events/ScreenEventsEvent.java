package io.github.metalcupcake5.JustEnoughUpdates.events;

import io.github.metalcupcake5.JustEnoughUpdates.utils.EnchantingHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ingame.GenericContainerScreen;

public class ScreenEventsEvent {
    public static void after_init(MinecraftClient client, Screen screen, int scaledWidth, int scaledHeight){
        if(screen instanceof GenericContainerScreen) {
            EnchantingHelper.chestInventoryName = screen.getTitle().getString();
        }
    }
}
