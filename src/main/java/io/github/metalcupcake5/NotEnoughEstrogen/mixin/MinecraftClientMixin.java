package io.github.metalcupcake5.NotEnoughEstrogen.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.awt.*;

@Mixin(MinecraftClient.class)
public class MinecraftClientMixin {
    double mouseX = 0;
    double mouseY = 0;
    @Shadow
    @Nullable
    public Screen currentScreen;

    @Inject(method = "setScreen", at = @At("HEAD"))
    private void setScreen(Screen screen, CallbackInfo ci) {
        if (screen instanceof HandledScreen) {
            if (this.currentScreen != null) {
                MinecraftClient client = MinecraftClient.getInstance();
                mouseX = client.mouse.getX();
                mouseY = client.mouse.getY();
                System.out.println("changing to new screen");
            }

        }
    }

    @Inject(method = "setScreen", at = @At("TAIL"))
    private void setScreenEnd(Screen screen, CallbackInfo ci) {
        if (screen instanceof HandledScreen) {
            if (this.currentScreen != null) {
                if (!GraphicsEnvironment.isHeadless()) {
                    
                    MouseInfo.getPointerInfo().getLocation().setLocation(mouseX, mouseY);
                }
            }

        }

    }
}
