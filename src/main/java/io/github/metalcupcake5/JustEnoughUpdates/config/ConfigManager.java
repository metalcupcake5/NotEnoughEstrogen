package io.github.metalcupcake5.JustEnoughUpdates.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import io.github.metalcupcake5.JustEnoughUpdates.JustEnoughUpdatesClient;
import net.fabricmc.loader.api.FabricLoader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/*
    adapted from https://github.com/Minenash/CustomHUD/blob/1.18/src/main/java/com/minenash/customhud/CustomHud.java
 */

public class ConfigManager {
    // config variables
    public static boolean showSkyblockIds = true;
    public static boolean dropProtection = true;
    public static boolean skymallNotif = true;
    public static boolean showItemInfo = true;
    public static boolean cultReminder = false;
    public static String cultReminderTime = "";
    public static boolean showMiningAbilityCooldown = true;
    public static boolean showArmadilloEggBlocks = true;

    public static final String[] cultReminderTimeList = new String[]{"0:00", "23:00", "23:30"};
    public static boolean showCommissions = true;

    public static final Path CONFIG = FabricLoader.getInstance().getConfigDir().resolve("justenoughupdates.json");

    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static void saveConfig() {
        if(!Files.exists(CONFIG)) {
            try {
                Files.createFile(CONFIG);
            } catch (IOException e) {
                JustEnoughUpdatesClient.LOGGER.error("Couldn't create the config file");
                return;
            }
        }
        JsonObject config = new JsonObject();
        config.addProperty("showSkyblockIds", showSkyblockIds);
        config.addProperty("dropProtectionEnabled", dropProtection);
        config.addProperty("skymallNotifEnabled", skymallNotif);
        config.addProperty("showItemInfo", showItemInfo);
        config.addProperty("showCommissions", showCommissions);
        config.addProperty("cultReminder", cultReminder);
        config.addProperty("cultReminderTime", cultReminderTime);
        config.addProperty("showMiningAbilityCooldown", showMiningAbilityCooldown);
        config.addProperty("showArmadilloEggBlocks", showArmadilloEggBlocks);
        try {
            Files.write(CONFIG, gson.toJson(config).getBytes());
        } catch (IOException e) {
            JustEnoughUpdatesClient.LOGGER.error("Couldn't save the config file");
        }
    }

    public static void loadConfig() {
        if(!Files.exists(CONFIG)) {
            JustEnoughUpdatesClient.LOGGER.info("could not find the config file, creating one");
            saveConfig();
            return;
        }
        boolean fix = false;
        try {
            JsonObject json = gson.fromJson(Files.newBufferedReader(CONFIG), JsonObject.class);
            showSkyblockIds = json.get("showSkyblockIds").getAsBoolean();
            dropProtection = json.get("dropProtectionEnabled").getAsBoolean();
            skymallNotif = json.get("skymallNotifEnabled").getAsBoolean();
            showItemInfo = json.get("showItemInfo").getAsBoolean();
            cultReminder = json.get("cultReminder").getAsBoolean();
            cultReminderTime = json.get("cultReminderTime").getAsString();
            showCommissions = json.get("showCommissions").getAsBoolean();
            showMiningAbilityCooldown = json.get("showMiningAbilityCooldown").getAsBoolean();
            showArmadilloEggBlocks = json.get("showArmadilloEggBlocks").getAsBoolean();
        } catch (JsonSyntaxException | NullPointerException e) {
            JustEnoughUpdatesClient.LOGGER.warn("Malformed Json, Fixing");
            fix = true;
        } catch (IOException e) {
            JustEnoughUpdatesClient.LOGGER.error("Couldn't read the config");
        }
        if (fix)
            saveConfig();
    }
}
