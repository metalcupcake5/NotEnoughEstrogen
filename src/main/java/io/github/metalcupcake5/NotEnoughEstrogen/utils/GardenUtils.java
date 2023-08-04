package io.github.metalcupcake5.NotEnoughEstrogen.utils;

import io.github.metalcupcake5.NotEnoughEstrogen.NotEnoughEstrogenClient;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.PlayerListEntry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.decoration.ArmorStandEntity;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.Box;

import java.util.List;

public class GardenUtils {
    public static boolean trackingCompost = false;
    private static int visitorCount = 0;
    private static String visitorTime = "";
    private static int compostAvailable = 0;
    private static int compostTime = 0;
    private static int queuedCompost = 0;

    public static void setGardenData(List<PlayerListEntry> players) {
        MinecraftClient client = MinecraftClient.getInstance();
        for (PlayerListEntry player : players) {
            Text name = client.inGameHud.getPlayerListHud().getPlayerName(player);
            if (name.getString().contains("Visitors")) {
                List<Text> list = name.getSiblings();
                visitorCount = Integer.parseInt(list.get(1).getString().split("")[1]);
            }
            if (name.getString().contains("Next Visitor")) {
                List<Text> list = name.getSiblings();
                visitorTime = list.get(1).getString();
            }
        }
    }

    public static void checkArmorStands() {
        MinecraftClient client = MinecraftClient.getInstance();
        try {
            List<ArmorStandEntity> list = client.world.getEntitiesByType(EntityType.ARMOR_STAND, new Box(-8, 70, 74, -13, 75, -29), armorStandEntity -> true);
            list.forEach(armorStandEntity -> {
                if (armorStandEntity.hasCustomName()) {
                    String name = armorStandEntity.getCustomName().getString();
                    if (name.contains("ready")) {
                        String time = name.split(" ")[0];
                        String[] split = time.split(":");
                        String mins = split[1];
                        String secs = split[2];
                        queueCompostTracker(mins, secs);
                    }

                    if (name.contains("stored")) {
                        String[] split = name.split(" ");
                        GardenUtils.setCompostAvailable(Integer.parseInt(split[0]));
                    }

                }
            });
        } catch (Exception e) {
            NotEnoughEstrogenClient.LOGGER.error("Disregard if disconnecting from server: ");
            NotEnoughEstrogenClient.LOGGER.error("Could not find armor stands!", e);
        }
    }

    public static void queueCompostTracker() {
        queueCompostTracker("10", "0");
    }

    public static void queueCompostTracker(String mins, String secs) {
        setCompostTime(Integer.parseInt(mins) * 60 + Integer.parseInt(secs));
        if (!GardenUtils.trackingCompost) {
            GardenUtils.trackingCompost = true;
            ChatUtils.sendClientMessage("scheduling compost tracker");
        }
    }

    public static int getVisitorCount() {
        return visitorCount;
    }

    public static String getVisitorTime() {
        return visitorTime;
    }

    public static int getCompostAvailable() {
        return compostAvailable;
    }

    public static void setCompostAvailable(int compost) {
        compostAvailable = compost;
    }

    public static String getCompostTime() {
        return compostTime <= 0 ? Formatting.RED + "None!" : compostTime / 60 + "m " + compostTime % 60 + "s";
    }

    public static void setCompostTime(int time) {
        compostTime = time;
    }

    public static void tickCompostTime() {
        compostTime -= 1;
        if (compostTime <= 0) {
            trackingCompost = false;
            compostAvailable += 1;
            queuedCompost -= 1;
            ChatUtils.sendClientMessage("Compost has finished.");
            MinecraftClient client = MinecraftClient.getInstance();
            client.player.playSound(SoundEvents.BLOCK_NOTE_BLOCK_BELL, 100, 1);
            if (queuedCompost > 0) {
                queueCompostTracker();
            }
        }
    }

    public static int getQueuedCompost() {
        return queuedCompost;
    }

    public static void setQueuedCompost(int compost) {
        queuedCompost = compost;
    }
}
