package io.github.metalcupcake5.NotEnoughEstrogen;

import io.github.metalcupcake5.NotEnoughEstrogen.commands.Commands;
import io.github.metalcupcake5.NotEnoughEstrogen.config.ConfigManager;
import io.github.metalcupcake5.NotEnoughEstrogen.events.ItemTooltipEvent;
import io.github.metalcupcake5.NotEnoughEstrogen.events.RenderEvents;
import io.github.metalcupcake5.NotEnoughEstrogen.events.TickEvent;
import io.github.metalcupcake5.NotEnoughEstrogen.utils.SkyblockTime;
import me.x150.renderer.event.Events;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class NotEnoughEstrogenClient implements ClientModInitializer {
    public static final String MOD_ID = "notenoughestrogen";
    // This logger is used to write text to the console and the log file.
    // It is considered best practice to use your mod id as the logger's name.
    // That way, it's clear which mod wrote info, warnings, and errors.
    public static final Logger LOGGER = LogManager.getLogger(MOD_ID);

    private static final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    @Override
    public void onInitializeClient() {

        // This code runs as soon as Minecraft is in a mod-load-ready state.
        // However, some things (like resources) may still be uninitialized.
        // Proceed with mild caution.

        Commands.registerCommands();

        ClientTickEvents.END_CLIENT_TICK.register(TickEvent::onTick);
//        ClientTickEvents.END_CLIENT_TICK.register(CheckFishingEvent::onTick);
        ItemTooltipCallback.EVENT.register((ItemTooltipEvent::onTooltip));

        ConfigManager.loadConfig();

        SkyblockTime.recalibrateTime();
        Events.registerEventHandlerClass(new RenderEvents());
    }

    public static ScheduledExecutorService getScheduler() {
        return scheduler;
    }
}
