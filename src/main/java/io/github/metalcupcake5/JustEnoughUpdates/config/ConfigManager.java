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

public class ConfigManager {
    // config variables
    public static boolean enabled = true;
    public static String test = "";

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
        config.addProperty("enabled", enabled);
        config.addProperty("test", "test");
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
            enabled = json.get("enabled").getAsBoolean();
            test = json.get("test").getAsString();
            JustEnoughUpdates.LOGGER.info("enabled: " + enabled);
            JustEnoughUpdates.LOGGER.info("test: " + test);
        } catch (JsonSyntaxException e) {
            JustEnoughUpdates.LOGGER.warn("Malformed Json, Fixing");
            fix = true;
        } catch (IOException e) {
            JustEnoughUpdates.LOGGER.error("Couldn't read the config");
        }
        if (fix)
            saveConfig();
    }
}
