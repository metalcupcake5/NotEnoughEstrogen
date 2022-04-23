package io.github.metalcupcake5.JustEnoughUpdates.events;

import io.github.metalcupcake5.JustEnoughUpdates.config.ConfigManager;
import io.github.metalcupcake5.JustEnoughUpdates.utils.ChatUtils;
import io.github.metalcupcake5.JustEnoughUpdates.utils.EnchantingHelper;
import io.github.metalcupcake5.JustEnoughUpdates.utils.SkyblockChecker;
import io.github.metalcupcake5.JustEnoughUpdates.utils.SkyblockTime;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ingame.GenericContainerScreen;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.sound.SoundEvents;

public class TickEvent {
    private static int TICKS = 0;
    private static boolean skymall = false;
    private static boolean cult = false;
    public static void onTick(MinecraftClient client) {
        if (client == null) return;

        TICKS++;
        if (TICKS % 5 == 0) {
            if(SkyblockChecker.inSkyblock) {
                Screen screen = client.currentScreen;
                if (screen instanceof GenericContainerScreen && EnchantingHelper.chestInventoryName.matches("Ultrasequencer \\([A-z]*\\)")) {
                    ScreenHandler provider = client.player.currentScreenHandler;
                    EnchantingHelper.inventory = provider.slots.get(0).inventory;
                    EnchantingHelper.ultrasequencer();
                }
            }
        }
        if (TICKS % 20 == 0) {
            SkyblockTime.time += 1000;
            if (client.world != null && !client.isInSingleplayer())
                SkyblockChecker.check();
            if(SkyblockChecker.inDwarvenMines) {
                if (ConfigManager.skymallNotif) {
                    String time = "0:00";
                    if (SkyblockTime.getCurrentSkyblockTime().equals(time) && !skymall) {
                        ChatUtils.sendClientMessage("skymall changed");
                        assert client.player != null;
                        client.player.playSound(SoundEvents.BLOCK_NOTE_BLOCK_BELL, 100, 1);
                        skymall = true;
                    }
                    if (!SkyblockTime.getCurrentSkyblockTime().equals(time) && skymall) {
                        skymall = false;
                    }
                }

                if(ConfigManager.cultReminder) {
                    if (!cult && SkyblockTime.getCurrentSkyblockTime().equals(ConfigManager.cultReminderTime)) {
                        ChatUtils.sendClientMessage("cult is soon");
                        cult = true;
                    }
                    if (!((SkyblockTime.getCurrentDay() + 1) % 7 == 0) && cult && !SkyblockTime.getCurrentSkyblockTime().equals(ConfigManager.cultReminderTime)) {
                        cult = false;
                    }
                }
            }
        }
        if (TICKS % 200 == 0){
            SkyblockTime.recalibrateTime();
            TICKS = 0;
        }
    }
}
