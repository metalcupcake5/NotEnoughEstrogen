package io.github.metalcupcake5.JustEnoughUpdates.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.ChatHudListener;
import net.minecraft.network.MessageType;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.UUID;

@Mixin(ChatHudListener.class)
public class ChatHudListenerMixin {

    @Shadow @Final private MinecraftClient client;

    @Inject(method = "onChatMessage", at = @At("HEAD"))
    public void onMessage(MessageType messageType, Text message, UUID senderUuid, CallbackInfo ci) {

        //Test.LOGGER.info(client.inGameHud.getPlayerListHud());
        //Test.LOGGER.info(msg);
        //Test.LOGGER.info("json ^^^");
    }
}
