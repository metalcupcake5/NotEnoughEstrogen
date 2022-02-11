package io.github.metalcupcake5.JustEnoughUpdates.events;

import io.github.metalcupcake5.JustEnoughUpdates.config.ConfigManager;
import io.github.metalcupcake5.JustEnoughUpdates.utils.ChatUtils;
import io.github.metalcupcake5.JustEnoughUpdates.utils.SkyblockChecker;
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
            //ChatUtils.sendClientMessage(Formatting.BOLD + "hi");
            if(SkyblockChecker.inDwarvenMines) {
                if (ConfigManager.skymallNotifEnabled) {
                    String time = "12:00am";
                    if (SkyblockChecker.time.equals(time) && !skymall) {
                        ChatUtils.sendClientMessage("skymall changed");
                        assert client.player != null;
                        client.player.playSound(SoundEvents.BLOCK_NOTE_BLOCK_BELL, 100, 1);
                        skymall = true;
                    }
                    if (!SkyblockChecker.time.equals(time) && skymall) {
                        skymall = false;
                    }
                }

                if(ConfigManager.cultReminder) {
                    if (!cult && SkyblockChecker.time.equals(ConfigManager.cultReminderTime) && (SkyblockChecker.day + 1) % 7 == 0) {
                        ChatUtils.sendClientMessage("cult is soon");
                        cult = true;
                    }
                    if (!((SkyblockChecker.day + 1) % 7 == 0) && cult && !SkyblockChecker.time.equals(ConfigManager.cultReminderTime)) {
                        cult = false;
                    }
                }
            }
        }
    }
}
