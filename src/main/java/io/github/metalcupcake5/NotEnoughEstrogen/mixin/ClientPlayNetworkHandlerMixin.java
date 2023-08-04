package io.github.metalcupcake5.NotEnoughEstrogen.mixin;

import io.github.metalcupcake5.NotEnoughEstrogen.utils.ItemUtils;
import io.github.metalcupcake5.NotEnoughEstrogen.utils.MouseUtils;
import io.github.metalcupcake5.NotEnoughEstrogen.utils.RendererHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.network.packet.s2c.play.GameJoinS2CPacket;
import net.minecraft.network.packet.s2c.play.InventoryS2CPacket;
import net.minecraft.network.packet.s2c.play.OpenScreenS2CPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.awt.*;

@Mixin(ClientPlayNetworkHandler.class)
public class ClientPlayNetworkHandlerMixin {
    @Inject(method = "onGameJoin", at = @At("HEAD"))
    private void onGameJoin(GameJoinS2CPacket packet, CallbackInfo ci) {
        ItemUtils.pickaxeAbilityCooldown = 60;
        RendererHelper.coords.clear();
    }

    @Inject(method = "onInventory", at = @At("HEAD"))
    private void onInventory(InventoryS2CPacket packet, CallbackInfo ci) {
        System.out.println("opened an inventory");
        if (MinecraftClient.getInstance().currentScreen != null) {
//            System.out.println("width: " + MinecraftClient.getInstance().currentScreen.width);
        }
        if (!GraphicsEnvironment.isHeadless()) {
//            MouseInfo.getPointerInfo().getLocation().setLocation(MouseUtils.getMouseX(), MouseUtils.getMouseY());
        }
    }

    @Inject(method = "onOpenScreen", at = @At("HEAD"))
    private void onOpenScreen(OpenScreenS2CPacket packet, CallbackInfo ci) {
//        System.out.println("Opened a screen");
//        System.out.println(packet.getScreenHandlerType());
        MinecraftClient client = MinecraftClient.getInstance();
        System.out.println("current mouse: " + client.mouse.getX() + ", " + client.mouse.getY());
        System.out.println("mouseUtils: " + MouseUtils.getMouseX() + ", " + MouseUtils.getMouseY());

        if (!GraphicsEnvironment.isHeadless()) {
//            MouseInfo.getPointerInfo().getLocation().setLocation(MouseUtils.getMouseX(), MouseUtils.getMouseY());
        }

    }

}
