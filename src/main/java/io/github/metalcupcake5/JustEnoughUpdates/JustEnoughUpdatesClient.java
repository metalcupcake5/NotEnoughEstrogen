package io.github.metalcupcake5.JustEnoughUpdates;

import io.github.metalcupcake5.JustEnoughUpdates.config.ConfigManager;
import io.github.metalcupcake5.JustEnoughUpdates.events.ItemTooltipEvent;
import io.github.metalcupcake5.JustEnoughUpdates.events.ScreenEventsEvent;
import io.github.metalcupcake5.JustEnoughUpdates.events.TickEvent;
import io.github.metalcupcake5.JustEnoughUpdates.utils.SkyblockChecker;
import io.github.metalcupcake5.JustEnoughUpdates.utils.SkyblockTime;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.command.v1.ClientCommandManager;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.fabricmc.fabric.api.client.screen.v1.ScreenEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.network.MessageType;
import net.minecraft.text.Text;
import net.minecraft.util.Util;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class JustEnoughUpdatesClient implements ClientModInitializer {
    public static final String MOD_ID = "justenoughupdates";
    // This logger is used to write text to the console and the log file.
    // It is considered best practice to use your mod id as the logger's name.
    // That way, it's clear which mod wrote info, warnings, and errors.
    public static final Logger LOGGER = LogManager.getLogger(MOD_ID);

    @Override
    public void onInitializeClient() {

    // This code runs as soon as Minecraft is in a mod-load-ready state.
        // However, some things (like resources) may still be uninitialized.
        // Proceed with mild caution.

        ClientTickEvents.END_CLIENT_TICK.register(TickEvent::onTick);

        ClientCommandManager.DISPATCHER.register(ClientCommandManager
                .literal("foo")
                .executes(context -> {
                    MinecraftClient client = MinecraftClient.getInstance();
                    LOGGER.info("" + client.world.getEntities());
                    //client.inGameHud.addChatMessage(MessageType.SYSTEM, Text.of("test command"), Util.NIL_UUID);
                    return 1;
                })
        );

        ClientCommandManager.DISPATCHER.register(ClientCommandManager
                .literal("location")
                .executes(context -> {
                    MinecraftClient client = MinecraftClient.getInstance();
                    client.inGameHud.addChatMessage(MessageType.SYSTEM, Text.of(SkyblockChecker.location), Util.NIL_UUID);
                    client.inGameHud.addChatMessage(MessageType.SYSTEM, Text.of("on skyblock: " + SkyblockChecker.inSkyblock), Util.NIL_UUID);
                    client.inGameHud.addChatMessage(MessageType.SYSTEM, Text.of("in dwarven mines: " + SkyblockChecker.inDwarvenMines), Util.NIL_UUID);
                    return 1;

                })
        );

        ItemTooltipCallback.EVENT.register((ItemTooltipEvent::onTooltip));
        ScreenEvents.AFTER_INIT.register((ScreenEventsEvent::after_init));

        ConfigManager.loadConfig();

        SkyblockTime.recalibrateTime();
    }
}
