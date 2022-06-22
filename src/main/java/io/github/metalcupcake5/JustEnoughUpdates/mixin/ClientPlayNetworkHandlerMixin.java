package io.github.metalcupcake5.JustEnoughUpdates.mixin;

import io.github.metalcupcake5.JustEnoughUpdates.utils.ItemUtils;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.network.packet.s2c.play.GameJoinS2CPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPlayNetworkHandler.class)
public class ClientPlayNetworkHandlerMixin {
    @Inject(method = "onGameJoin", at = @At("HEAD"))
    private void onGameJoin(GameJoinS2CPacket packet, CallbackInfo ci) {
        ItemUtils.pickaxeAbilityCooldown = 60;
    }
}
