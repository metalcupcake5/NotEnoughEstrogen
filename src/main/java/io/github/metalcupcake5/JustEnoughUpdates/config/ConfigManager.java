package io.github.metalcupcake5.JustEnoughUpdates.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import io.github.metalcupcake5.JustEnoughUpdates.JustEnoughUpdates;
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
    public static boolean dropProtectionEnabled = true;
    public static boolean skymallNotifEnabled = true;
    public static boolean showItemInfo = true;
    public static boolean showFps = false;
    public static boolean cultReminder = false;
    public static int cultReminderTime = 0;

    public static final Path CONFIG = FabricLoader.getInstance().getConfigDir().resolve("justenoughupdates.json");

    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static void saveConfig() {
        if(!Files.exists(CONFIG)) {
            try {
                Files.createFile(CONFIG);
            } catch (IOException e) {
                JustEnoughUpdates.LOGGER.error("Couldn't create the config file");
                return;
            }
        }
        JsonObject config = new JsonObject();
        config.addProperty("showSkyblockIds", showSkyblockIds);
        config.addProperty("dropProtectionEnabled", dropProtectionEnabled);
        config.addProperty("skymallNotifEnabled", skymallNotifEnabled);
        config.addProperty("showItemInfo", showItemInfo);
        config.addProperty("showFps", showFps);
        config.addProperty("cultReminder", cultReminder);
        config.addProperty("cultReminderTime", cultReminderTime);
        try {
            Files.write(CONFIG, gson.toJson(config).getBytes());
        } catch (IOException e) {
            JustEnoughUpdates.LOGGER.error("Couldn't save the config file");
        }
    }

    public static void loadConfig() {
        if(!Files.exists(CONFIG)) {
            JustEnoughUpdates.LOGGER.info("could not find the config file, creating one");
            saveConfig();
            return;
        }
        boolean fix = false;
        try {
            JsonObject json = gson.fromJson(Files.newBufferedReader(CONFIG), JsonObject.class);
            showSkyblockIds = json.get("showSkyblockIds").getAsBoolean();
            dropProtectionEnabled = json.get("dropProtectionEnabled").getAsBoolean();
            skymallNotifEnabled = json.get("skymallNotifEnabled").getAsBoolean();
            showItemInfo = json.get("showItemInfo").getAsBoolean();
            showFps = json.get("showFps").getAsBoolean();
            cultReminder = json.get("cultReminder").getAsBoolean();
            cultReminderTime = json.get("cultReminderTime").getAsInt();
        } catch (JsonSyntaxException | NullPointerException e) {
            JustEnoughUpdates.LOGGER.warn("Malformed Json, Fixing");
            fix = true;
        } catch (IOException e) {
            JustEnoughUpdates.LOGGER.error("Couldn't read the config");
        }
        if (fix)
            saveConfig();
    }
}
