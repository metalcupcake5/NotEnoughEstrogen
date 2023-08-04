package io.github.metalcupcake5.NotEnoughEstrogen.mixin;

import io.github.metalcupcake5.NotEnoughEstrogen.config.ConfigManager;
import io.github.metalcupcake5.NotEnoughEstrogen.utils.ChatUtils;
import io.github.metalcupcake5.NotEnoughEstrogen.utils.SkyblockChecker;
import net.minecraft.client.network.ClientPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ClientPlayerEntity.class)
public class ClientPlayerEntityMixin {

    @Inject(method = "dropSelectedItem", at = @At("HEAD"), cancellable = true)
    private void dropSelectedItem(boolean entireStack, CallbackInfoReturnable<Boolean> cir) {
        if (SkyblockChecker.inSkyblock && ConfigManager.dropProtection) {
            cir.cancel();
            ChatUtils.sendClientMessage("You've disabled dropping items!");
        }
    }
}
