package io.github.metalcupcake5.JustEnoughUpdates.mixin;

import net.minecraft.client.gui.screen.multiplayer.SocialInteractionsPlayerListWidget;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Collection;
import java.util.UUID;

@Mixin(SocialInteractionsPlayerListWidget.class)
public class SocialInteractionsPlayerListWidgetMixin {
    @Inject(method = "update", at = @At("HEAD"))
    private void update(Collection<UUID> uuids, double scrollAmount, CallbackInfo ci){
        //Test.LOGGER.info(uuids);
    }
}
