package io.github.metalcupcake5.NotEnoughEstrogen.commands;

import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import io.github.metalcupcake5.NotEnoughEstrogen.utils.SkyblockChecker;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;

public class Location {
    public static int run(CommandContext context) throws CommandSyntaxException {
        MinecraftClient client = MinecraftClient.getInstance();
        client.inGameHud.getChatHud().addMessage(Text.of(SkyblockChecker.location));
        client.inGameHud.getChatHud().addMessage(Text.of("on skyblock: " + SkyblockChecker.inSkyblock));
        client.inGameHud.getChatHud().addMessage(Text.of("in dwarven mines: " + SkyblockChecker.inDwarvenMines));
        client.inGameHud.getChatHud().addMessage(Text.of("in garden: " + SkyblockChecker.inGarden));
        return 1;
    }
}
