package io.github.metalcupcake5.NotEnoughEstrogen.utils;

import net.minecraft.client.MinecraftClient;

public class MouseUtils {
    private static double mouseX = 0;
    private static double mouseY = 0;

    public static void setMousePos(double x, double y) {
        if (MinecraftClient.getInstance().currentScreen != null && x != (double) MinecraftClient.getInstance().currentScreen.width) {
            mouseX = x;
        }
        if (MinecraftClient.getInstance().currentScreen != null && x != (double) MinecraftClient.getInstance().currentScreen.height) {
            mouseY = y;
        }

    }

    public static double getMouseX() {
        return mouseX;
    }

    public static double getMouseY() {
        return mouseY;
    }
}
