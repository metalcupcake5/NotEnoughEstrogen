package io.github.metalcupcake5.NotEnoughEstrogen.utils;

import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

public class ChatUtils {
    public static String prefix() {
        return "" + Formatting.WHITE + Formatting.BOLD + "[" + Formatting.RESET + Formatting.AQUA + "N" + Formatting.LIGHT_PURPLE + "E" + Formatting.AQUA + "E" + Formatting.WHITE + Formatting.BOLD + "]" + Formatting.RESET;
    }

    public static void sendClientMessage(String message) {
        MinecraftClient client = MinecraftClient.getInstance();
        client.inGameHud.getChatHud().addMessage(Text.of(prefix() + " " + message));
    }
}
