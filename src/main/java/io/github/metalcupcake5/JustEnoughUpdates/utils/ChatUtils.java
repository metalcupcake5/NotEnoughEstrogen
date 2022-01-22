package io.github.metalcupcake5.JustEnoughUpdates.utils;

import net.minecraft.client.MinecraftClient;
import net.minecraft.network.MessageType;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Util;

public class ChatUtils {
    public static String prefix(){
        return "" + Formatting.AQUA + Formatting.BOLD + "[" + Formatting.RESET + Formatting.AQUA + "Dez Nut" + Formatting.AQUA + Formatting.BOLD + "]" + Formatting.RESET;
    }

    public static void sendClientMessage(String message){
        MinecraftClient client = MinecraftClient.getInstance();
        client.inGameHud.addChatMessage(MessageType.SYSTEM, Text.of(prefix() + " " + message), Util.NIL_UUID);
    }
}
