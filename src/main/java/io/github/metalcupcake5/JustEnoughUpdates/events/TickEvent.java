package io.github.metalcupcake5.JustEnoughUpdates.events;

import io.github.metalcupcake5.JustEnoughUpdates.config.ConfigManager;
import io.github.metalcupcake5.JustEnoughUpdates.utils.ChatUtils;
import io.github.metalcupcake5.JustEnoughUpdates.utils.SkyblockChecker;
import io.github.metalcupcake5.JustEnoughUpdates.utils.SkyblockTime;
import net.minecraft.client.MinecraftClient;
import net.minecraft.sound.SoundEvents;

public class TickEvent {
    private static int TICKS = 0;
    private static boolean skymall = false;
    private static boolean cult = false;
    public static void onTick(MinecraftClient client) {
        if (client == null) return;

        TICKS++;
        if (TICKS % 20 == 0) {
            if (client.world != null && !client.isInSingleplayer())
                SkyblockChecker.check();
            TICKS = 0;
            if(SkyblockChecker.inDwarvenMines) {
                if (ConfigManager.skymallNotif) {
                    String time = "0:00";
                    if (SkyblockTime.getCurrentTime().equals(time) && !skymall) {
                        ChatUtils.sendClientMessage("skymall changed");
                        assert client.player != null;
                        client.player.playSound(SoundEvents.BLOCK_NOTE_BLOCK_BELL, 100, 1);
                        skymall = true;
                    }
                    if (!SkyblockTime.getCurrentTime().equals(time) && skymall) {
                        skymall = false;
                    }
                }

                if(ConfigManager.cultReminder) {
                    if (!cult && SkyblockTime.getCurrentTime().equals(ConfigManager.cultReminderTime)) {
                        ChatUtils.sendClientMessage("cult is soon");
                        cult = true;
                    }
                    if (!((SkyblockTime.getCurrentDay() + 1) % 7 == 0) && cult && !SkyblockTime.getCurrentTime().equals(ConfigManager.cultReminderTime)) {
                        cult = false;
                    }
                }
            }
        }
    }
}
