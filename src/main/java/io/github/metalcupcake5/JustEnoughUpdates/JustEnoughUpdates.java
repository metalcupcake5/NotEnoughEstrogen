package io.github.metalcupcake5.JustEnoughUpdates;

import io.github.metalcupcake5.JustEnoughUpdates.events.ItemTooltipEvent;
import io.github.metalcupcake5.JustEnoughUpdates.utils.ChatUtils;
import io.github.metalcupcake5.JustEnoughUpdates.utils.SkyblockChecker;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.command.v1.ClientCommandManager;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.network.MessageType;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Util;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class JustEnoughUpdates implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LogManager.getLogger("test");
	private static int TICKS = 0;
	private static boolean skymall = false;

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		ClientTickEvents.END_CLIENT_TICK.register(JustEnoughUpdates::onTick);

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
	}


	public static void onTick(MinecraftClient client) {
		if (client == null) return;

		TICKS++;
		if (TICKS % 20 == 0) {
			if (client.world != null && !client.isInSingleplayer())
				SkyblockChecker.check();
			TICKS = 0;
			//ChatUtils.sendClientMessage(Formatting.BOLD + "hi");
			if(SkyblockChecker.inDwarvenMines) {
				String time = "12:00am";
				if (SkyblockChecker.time.equals(time) && !skymall) {
					ChatUtils.sendClientMessage("skymall changed");
					client.player.playSound(SoundEvents.BLOCK_NOTE_BLOCK_BELL, 100, 1);
					skymall = true;
				}
				if (!SkyblockChecker.time.equals(time) && skymall) {
					skymall = false;
				}
			}
		}
	}

}
