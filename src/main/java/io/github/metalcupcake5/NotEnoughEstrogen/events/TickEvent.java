package io.github.metalcupcake5.NotEnoughEstrogen.events;

import io.github.metalcupcake5.NotEnoughEstrogen.config.ConfigManager;
import io.github.metalcupcake5.NotEnoughEstrogen.utils.*;
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
            SkyblockTime.time += 1;
            if (ItemUtils.pickaxeAbilityCooldown > 0) ItemUtils.pickaxeAbilityCooldown -= 1;
            if (client.world != null && !client.isInSingleplayer())
                SkyblockChecker.check();
            if (SkyblockChecker.inSkyblock) {
                if (SkyblockChecker.inDwarvenMines) {
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

                    if (ConfigManager.cultReminder) {
                        if (!cult && SkyblockTime.getCurrentSkyblockTime().equals(ConfigManager.cultReminderTime)) {
                            ChatUtils.sendClientMessage("cult is soon");
                            cult = true;
                        }
                        if (!((SkyblockTime.getCurrentDay() + 1) % 7 == 0) && cult && !SkyblockTime.getCurrentSkyblockTime().equals(ConfigManager.cultReminderTime)) {
                            cult = false;
                        }
                    }
                }
                if (SkyblockChecker.inGarden) GardenUtils.checkArmorStands();
                if (GardenUtils.trackingCompost) GardenUtils.tickCompostTime();
            }
        }
        if (TICKS % 200 == 0) {
            SkyblockTime.recalibrateTime();
            TICKS = 0;
        }
    }
}
